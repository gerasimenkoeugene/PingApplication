package com.doclerholding.pingapplication.command;

import com.doclerholding.pingapplication.config.ApplicationConfig;
import com.doclerholding.pingapplication.data.HostData;
import com.doclerholding.pingapplication.domain.HostStatus;
import com.doclerholding.pingapplication.service.ConsoleCommandExecutor;
import com.doclerholding.pingapplication.service.ReportService;

import java.io.IOException;
import java.util.Date;

public class IcmpPingCommand implements Command {

    private final static String COMMAND = ApplicationConfig.getValue("ICMP_PING_COMMAND");

    @Override
    public void execute(final String host) {
        try {
            String result = ConsoleCommandExecutor.INSTANCE.executeCommand(host, COMMAND);

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
