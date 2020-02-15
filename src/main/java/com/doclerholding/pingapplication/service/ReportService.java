package com.doclerholding.pingapplication.service;

import com.doclerholding.pingapplication.config.ApplicationConfig;
import com.doclerholding.pingapplication.domain.HostStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public enum ReportService  {

    INSTANCE;

    private static final Logger LOG = LoggerFactory.getLogger(ReportService.class);

    public void reportError(HostStatus hostStatus) {
        try {
            String hostStatusJson = new ObjectMapper().writeValueAsString(hostStatus);

            LOG.warn("Report error status: {}", hostStatusJson);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ApplicationConfig.getValue("REPORT_URL")))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(hostStatusJson))
                .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            LOG.error("Error during reporting error status", e);
        }
    }
}
