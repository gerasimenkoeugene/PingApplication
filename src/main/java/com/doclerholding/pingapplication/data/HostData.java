package com.doclerholding.pingapplication.data;

import com.doclerholding.pingapplication.domain.HostStatus;
import com.doclerholding.pingapplication.service.ResourceFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class HostData {

    private static final Logger LOG = LoggerFactory.getLogger(HostData.class);

    private static Map<String, HostStatus> hostsStatuses;

    static {
        try {
            hostsStatuses = ResourceFileReader.INSTANCE.readFile("hosts.txt")
                .stream().map(host -> {
                    HostStatus hostStatus =  new HostStatus();
                    hostStatus.setHost(host);
                    return hostStatus;
                }).collect(Collectors.toMap(HostStatus::getHost, status -> status));
        } catch (IOException e) {
            LOG.error("Error while loading data ", e);
            System.exit(0);
        }
    }

    public static HostStatus getHostStatusData(String host) {
        return hostsStatuses.get(host);
    }

    public static Collection<String> getHosts() {
        return hostsStatuses.keySet();
    }

    public static void addHostStatusData(String host) {
        HostStatus hostStatus = new HostStatus();
        hostStatus.setHost(host);
        hostsStatuses.put(host, hostStatus);
    }

    public static void removeHostStatusData(String host) {
        hostsStatuses.remove(host);
    }
}
