package com.doclerholding.pingapplication.service;


import com.doclerholding.pingapplication.domain.HostStatus;

public enum ReportService  {

    INSTANCE;

    public void reportError(HostStatus hostStatus) {
        System.out.println(hostStatus);
    }
}
