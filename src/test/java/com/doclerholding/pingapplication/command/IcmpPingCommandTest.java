package com.doclerholding.pingapplication.command;

import com.doclerholding.pingapplication.data.HostData;
import com.doclerholding.pingapplication.domain.HostStatus;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class IcmpPingCommandTest extends ConsoleCommandTest {

    IcmpPingCommand icmpPingCommand = new IcmpPingCommand();

    @Test
    public void shoulExecuteIcmpPing() {
        icmpPingCommand.execute(validHost);

        HostStatus hostStatus = HostData.getHostStatusData(validHost);
        assertTrue(hostStatus.getIcmpPing().contains("0% packet loss"));
    }

    @Test
    public void shoulFailIcmpPing() {
        icmpPingCommand.execute(unValidHost);

        HostStatus hostStatus = HostData.getHostStatusData(unValidHost);
        assertFalse(hostStatus.getIcmpPing().contains("0% packet loss"));
    }
}