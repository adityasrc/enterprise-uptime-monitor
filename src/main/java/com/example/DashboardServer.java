package com.example;

import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

public class DashboardServer {
    private final ConcurrentHashMap<String, String> statusMap;

    public DashboardServer(ConcurrentHashMap<String, String> statusMap) {
        this.statusMap = statusMap;
    }

    public void start() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(3001), 0);
        
        // Serve JSON Data
        server.createContext("/status", exchange -> {
            StringBuilder json = new StringBuilder("{\n");
            statusMap.forEach((k, v) -> json.append("  \"").append(k).append("\": \"").append(v).append("\",\n"));
            json.append("  \"system\": \"operational\"\n}");
            
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, json.length());
            try (OutputStream os = exchange.getResponseBody()) { os.write(json.toString().getBytes()); }
        });

        // Serve Dashboard UI
        server.createContext("/", exchange -> {
            try {
                byte[] html = Files.readAllBytes(Paths.get("index.html"));
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, html.length);
                try (OutputStream os = exchange.getResponseBody()) { os.write(html); }
            } catch (Exception e) {
                String err = "UI not found";
                exchange.sendResponseHeaders(404, err.length());
                try (OutputStream os = exchange.getResponseBody()) { os.write(err.getBytes()); }
            }
        });
        
        server.setExecutor(null);
        server.start();
        System.out.println("Live Dashboard running on http://localhost:3001");
    }
}