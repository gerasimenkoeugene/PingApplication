package com.doclerholding.pingapplication.command;

import com.doclerholding.pingapplication.config.ApplicationConfig;
import com.doclerholding.pingapplication.data.HostData;
import com.doclerholding.pingapplication.domain.HostStatus;
import com.doclerholding.pingapplication.service.ReportService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Date;

public class TcpPingCommand implements Command {

    private final static Integer REQUEST_SECONDS_TIMEOUT = Integer.valueOf(ApplicationConfig.getValue("TCP_COMMAND_REQUEST_SECONDS_TIMEOUT"));

    @Override
    public void execute(final String host) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(String.join("", "https://", host)))
            .timeout(Duration.ofSeconds(REQUEST_SECONDS_TIMEOUT))
            .header("Content-Type", "application/json")
            .GET()
            .build();

        try {
            long startTime = System.currentTimeMillis();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            long elapsedTime = System.currentTimeMillis() - startTime;

            int statusCode = response.statusCode();
            Boolean isError = statusCode / 100 == 4 || statusCode / 100 == 5;
            StringBuilder status = new StringBuilder();
            status.append("Request time: ")
                .append(elapsedTime)
                .append("ms, ")
                .append("HTTP status: ")
                .append(statusCode);

            updateHostTcpStatus(host, status.toString(), isError);
        } catch (Exception e) {
            updateHostTcpStatus(host, String.join(" ", "Error", e.getMessage()), true);
        }
    }


    private void updateHostTcpStatus(String host, String status, Boolean isError) {
        HostStatus hostStatus = HostData.getHostStatusData(host);
        synchronized (hostStatus) {
            hostStatus.setTcpPing(String.join(" ", new Date().toString(), status));
            if (isError) {
                ReportService.INSTANCE.reportError(hostStatus);
            }
        }
    }

}
