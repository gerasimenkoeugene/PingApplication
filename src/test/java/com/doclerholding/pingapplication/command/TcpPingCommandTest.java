package com.doclerholding.pingapplication.command;

import com.doclerholding.pingapplication.data.HostData;
import com.doclerholding.pingapplication.domain.HostStatus;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TcpPingCommandTest extends ConsoleCommandTest {

    TcpPingCommand tcpPingCommand = new TcpPingCommand();

    String validHost = "mail.google.com";
    String unValidHost = "mail.google.co";

    @Before
    public void setUp() {
        HostData.addHostStatusData(validHost);
        HostData.addHostStatusData(unValidHost);
    }

    @Test
    public void shoulExecuteTcpPing() {
        tcpPingCommand.execute(validHost);

        HostStatus hostStatus = HostData.getHostStatusData(validHost);
        assertFalse(hostStatus.getTcpPing().contains("Error"));
    }

    @Test
    public void shoulFailTcpPing() {
        tcpPingCommand.execute(unValidHost);

        HostStatus hostStatus = HostData.getHostStatusData(unValidHost);
        assertTrue(hostStatus.getTcpPing().contains("Error"));
    }
}