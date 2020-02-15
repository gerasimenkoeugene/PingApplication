package com.doclerholding.pingapplication;

import com.doclerholding.pingapplication.config.ApplicationConfig;
import com.doclerholding.pingapplication.factory.TaskFactory;

import java.io.IOException;
import java.util.Timer;

public class Application {

    public static void main(String[] args) throws IOException {

        Timer timer = new Timer("Timer");

        long period = Integer.valueOf(ApplicationConfig.getValue("SCHEDULER_SECONDS_DELAY")) * 1000L;

        timer.scheduleAtFixedRate(TaskFactory.getIcmpPingTask(), 0, period);
        timer.scheduleAtFixedRate(TaskFactory.getTcpTask(), 0, period);
        timer.scheduleAtFixedRate(TaskFactory.getTracerouteTask(), 0, period);
    }
}
