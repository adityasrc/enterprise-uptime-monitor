package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class App {
    private static final ConcurrentHashMap<String, String> statusMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Enterprise Uptime Monitor...");
        List<String> urls = readUrls("urls.txt");

        if (urls.isEmpty()) {
            System.out.println("No URLs found in urls.txt");
            return;
        }

        // Start background checking
        new Thread(new Monitor(statusMap, urls)).start();

        // Start Web Server
        new DashboardServer(statusMap).start();
    }

    private static List<String> readUrls(String filename) {
        List<String> urls = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) urls.add(line.trim());
            }
        } catch (Exception e) {
            System.out.println("Could not read " + filename);
        }
        return urls;
    }
}