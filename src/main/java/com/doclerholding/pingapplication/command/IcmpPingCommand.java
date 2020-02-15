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

public class IcmpPingCommand implements Command {

    private final static String COMMAND = ApplicationConfig.getValue("ICMP_PING_COMMAND");

    @Override
    public void execute(final String host) {
        try {
            final Process process = Runtime.getRuntime().exec(String.join(" ", COMMAND, host));
            BufferedReader executionCommandInputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String result = executionCommandInputStream.lines().collect(Collectors.joining("\n"));

            if (!result.contains("0% packet loss")) {
                updateHostIcmpStatus(host, result, true);
            } else {
                updateHostIcmpStatus(host, result, false);
            }
        } catch (IOException e) {
            updateHostIcmpStatus(host, e.getMessage(), true);
        }
    }


    private void updateHostIcmpStatus(String host, String status, Boolean isError) {
        HostStatus hostStatus = HostData.getHostStatusData(host);
        synchronized (hostStatus) {
            hostStatus.setIcmpPing(String.join(" ", new Date().toString(), status));
            if (isError) {
                ReportService.INSTANCE.reportError(hostStatus);
            }
        }
    }

}
