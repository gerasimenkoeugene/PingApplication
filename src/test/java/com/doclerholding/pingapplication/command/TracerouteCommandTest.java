package com.doclerholding.pingapplication.command;

import com.doclerholding.pingapplication.data.HostData;
import com.doclerholding.pingapplication.domain.HostStatus;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TracerouteCommandTest extends ConsoleCommandTest {

    TracerouteCommand tracerouteCommand = new TracerouteCommand();

    @Test
    public void shoulExecuteTraceroute() {
        tracerouteCommand.execute(validHost);

        HostStatus hostStatus = HostData.getHostStatusData(validHost);
        assertTrue(hostStatus.getTrace().contains("* * *"));
    }

    @Test
    public void shoulFailTraceroute() {
        tracerouteCommand.execute(unValidHost);

        HostStatus hostStatus = HostData.getHostStatusData(unValidHost);
        assertFalse(hostStatus.getTrace().contains("* * *"));
    }
}