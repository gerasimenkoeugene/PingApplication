package com.doclerholding.pingapplication.command;

import com.doclerholding.pingapplication.config.ApplicationConfig;
import com.doclerholding.pingapplication.data.HostData;
import com.doclerholding.pingapplication.domain.HostStatus;
import com.doclerholding.pingapplication.service.ConsoleCommandExecutor;
import com.doclerholding.pingapplication.service.ReportService;

import java.io.IOException;
import java.util.Date;

public class TracerouteCommand implements Command {

    private final static String COMMAND = ApplicationConfig.getValue("TRACEROUTE_COMMAND");

    @Override
    public void execute(final String host) {
        try {
            String result = ConsoleCommandExecutor.INSTANCE.executeCommand(host, COMMAND);

            if (result.contains("* * *")) {
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
