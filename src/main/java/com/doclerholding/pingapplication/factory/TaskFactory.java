package com.doclerholding.pingapplication.factory;

import com.doclerholding.pingapplication.command.IcmpPingCommand;
import com.doclerholding.pingapplication.command.TcpPingCommand;
import com.doclerholding.pingapplication.command.TracerouteCommand;
import com.doclerholding.pingapplication.config.ApplicationConfig;
import com.doclerholding.pingapplication.data.HostData;
import com.doclerholding.pingapplication.service.CommandExecutor;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskFactory {

    private static ExecutorService executor = Executors.newFixedThreadPool(Integer.valueOf(ApplicationConfig.getValue("THREADS_AMOUNT")));

    public static TimerTask getIcmpPingTask() {
        return new TimerTask() {
            public void run() {
                for (String host : HostData.getHosts()) {
                    executor.submit(() -> CommandExecutor.INSTANCE.executeCommand(host, new IcmpPingCommand()));
                }
            }
        };
    }

    public static TimerTask getTcpTask() {
        return new TimerTask() {
            public void run() {
                for (String host : HostData.getHosts()) {
                    executor.submit(() -> CommandExecutor.INSTANCE.executeCommand(host, new TcpPingCommand()));
                }
            }
        };
    }

    public static TimerTask getTracerouteTask() {
        return new TimerTask() {
            public void run() {
                for (String host : HostData.getHosts()) {
                    executor.submit(() -> CommandExecutor.INSTANCE.executeCommand(host, new TracerouteCommand()));
                }
            }
        };
    }
}
