package com.doclerholding.pingapplication.command;

import com.doclerholding.pingapplication.data.HostData;
import org.junit.After;
import org.junit.Before;

public class ConsoleCommandTest {

    String validHost = "example.com";
    String unValidHost = "192.168.1.333";

    @Before
    public void setUp() {
        HostData.addHostStatusData(validHost);
        HostData.addHostStatusData(unValidHost);
    }

    @After
    public void cleanUp() {
        HostData.removeHostStatusData(validHost);
        HostData.removeHostStatusData(unValidHost);
    }
}
