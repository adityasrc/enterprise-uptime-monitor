package com.example;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Monitor implements Runnable {
    private final ConcurrentHashMap<String, String> statusMap;
    private final List<String> urls;

    public Monitor(ConcurrentHashMap<String, String> statusMap, List<String> urls) {
        this.statusMap = statusMap;
        this.urls = urls;
    }

    @Override
    public void run() {
        while (true) {
            for (String site : urls) {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(site).openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(3000);
                    int code = conn.getResponseCode();
                    statusMap.put(site, "UP (Code: " + code + ")");
                    System.out.println("[MONITOR] " + site + " is UP");
                } catch (Exception e) {
                    statusMap.put(site, "DOWN");
                    System.out.println("[MONITOR] " + site + " is DOWN");
                }
            }
            try { Thread.sleep(10000); } catch (Exception ignored) {}
        }
    }
}