package com.doclerholding.pingapplication.data;

import com.doclerholding.pingapplication.domain.HostStatus;
import com.doclerholding.pingapplication.service.ResourceFileReader;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class HostData {

    private static Map<String, HostStatus> hostsStatuses = new HashMap<>();

    static {
        try {
            hostsStatuses = new ResourceFileReader().readFile("hosts.txt")
                .stream().map(host -> {
                    HostStatus commandExecutionResult =  new HostStatus();
                    commandExecutionResult.setHost(host);
                    return commandExecutionResult;
                }).collect(Collectors.toMap(HostStatus::getHost, commandExecutionResult -> commandExecutionResult));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HostStatus getHostStatusData(String host) {
        return hostsStatuses.get(host);
    }

    public static Collection<String> getHosts() {
        return hostsStatuses.keySet();
    }

    public static Collection<HostStatus> getHostsStatuses() {
        return hostsStatuses.values();
    }
}
