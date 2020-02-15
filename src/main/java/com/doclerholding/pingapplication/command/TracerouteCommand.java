package com.doclerholding.pingapplication.command;

import com.doclerholding.pingapplication.config.ApplicationConfig;
import com.doclerholding.pingapplication.data.HostData;
import com.doclerholding.pingapplication.domain.HostStatus;
import com.doclerholding.pingapplication.service.ReportService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.stream.Collectors;

public class TracerouteCommand implements Command {

    private final static String COMMAND = ApplicationConfig.getValue("TRACEROUTE_COMMAND");

    @Override
    public void execute(final String host) {
        try {
            final Process process = Runtime.getRuntime().exec(String.join(" ", COMMAND, host));
            BufferedReader executionCommandInputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String result = executionCommandInputStream.lines().collect(Collectors.joining("\n"));

            if (result.contains("Destination net unreachable")) {
                updateHostTraceStatus(host, result, true);
            } else {
                updateHostTraceStatus(host, result, false);
            }
        } catch (IOException e) {
            updateHostTraceStatus(host, e.getMessage(), true);
        }
    }


    private void updateHostTraceStatus(String host, String status, Boolean isError) {
        HostStatus hostStatus = HostData.getHostStatusData(host);
        synchronized (hostStatus) {
            hostStatus.setTrace(String.join(" ", new Date().toString(), status));
            if (isError) {
                ReportService.INSTANCE.reportError(hostStatus);
            }
        }
    }
}
