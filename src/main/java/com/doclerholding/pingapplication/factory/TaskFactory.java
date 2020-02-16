package com.doclerholding.pingapplication.factory;

import com.doclerholding.pingapplication.command.IcmpPingCommand;
import com.doclerholding.pingapplication.command.TcpPingCommand;
import com.doclerholding.pingapplication.command.TracerouteCommand;
import com.doclerholding.pingapplication.config.ApplicationConfig;
import com.doclerholding.pingapplication.data.HostData;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskFactory {

    private final static ExecutorService executor = Executors.newFixedThreadPool(Integer.valueOf(ApplicationConfig.getValue("THREADS_AMOUNT")));

    public static TimerTask getIcmpPingTask() {
        return new TimerTask() {
            public void run() {
                for (String host : HostData.getHosts()) {
                    executor.submit(() -> new IcmpPingCommand().execute(host));
                }
            }
        };
    }

    public static TimerTask getTcpTask() {
        return new TimerTask() {
            public void run() {
                for (String host : HostData.getHosts()) {
                    executor.submit(() -> new TcpPingCommand().execute(host));
                }
            }
        };
    }

    public static TimerTask getTracerouteTask() {
        return new TimerTask() {
            public void run() {
                for (String host : HostData.getHosts()) {
                    executor.submit(() -> new TracerouteCommand().execute(host));
                }
            }
        };
    }
}
