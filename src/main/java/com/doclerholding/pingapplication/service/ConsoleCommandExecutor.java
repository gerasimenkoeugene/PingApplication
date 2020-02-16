package com.doclerholding.pingapplication.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public enum ConsoleCommandExecutor {

    INSTANCE;

    public String executeCommand(String host, String command) throws IOException {
        final Process process = Runtime.getRuntime().exec(String.join(" ", command, host));
        BufferedReader executionCommandInputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return executionCommandInputStream.lines().collect(Collectors.joining("\n"));
    }

}
