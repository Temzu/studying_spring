package com.temzu.spring.command;

import java.util.Arrays;

public enum CommandRequest {
    UNKNOWN("unknown"),
    HELP("help"),
    CREATE("create"),
    GET("get"),
    UPDATE("update"),
    DELETE("delete"),
    STOP("stop"),
    COUNT("count"),
    AVG("avg");

    private final String qualifier;

    CommandRequest(String qualifier) {
        this.qualifier = qualifier;
    }

    public static CommandRequest defineCommand(String command) {
        String cmd = command.toLowerCase();
        return Arrays.stream(CommandRequest.values())
                .filter(commandRequest -> commandRequest.qualifier.equals(cmd))
                .findAny()
                .orElse(UNKNOWN);
    }

    public String getQualifier() {
        return qualifier;
    }
}
