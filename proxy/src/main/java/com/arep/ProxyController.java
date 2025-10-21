package com.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    private final List<String> instances;
    private int lastUsedIndex = 0;

    public ProxyController(@Value("${app.lucasseq1}") String lucasseq1, @Value("${app.lucasseq2}") String lucasseq2) {
        this.instances = Arrays.asList(lucasseq1, lucasseq2);
    }

    @GetMapping("/lucasseq")
    public ResponseEntity<String> fetchLucasSequence(@RequestParam("value") int value) {
        String requestUrl = selectInstance() + "/lucasseq?value=" + value;
        System.out.println("Forwarding request to: " + requestUrl);
        try {
            URL url = URI.create(requestUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line);
                    }
                    return ResponseEntity.ok(content.toString());
                }
            } else {
                return ResponseEntity.status(statusCode).build();
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private String selectInstance() {
        if (lastUsedIndex == instances.size() - 1) {
            lastUsedIndex = 0;
        } else {
            lastUsedIndex++;
        }

        return instances.get(lastUsedIndex);
    }
}
