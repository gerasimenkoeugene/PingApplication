package com.doclerholding.pingapplication.service;

import com.doclerholding.pingapplication.command.Command;

public enum CommandExecutor {

    INSTANCE;

    public void executeCommand(String host, Command command) {
        command.execute(host);
    }

}
