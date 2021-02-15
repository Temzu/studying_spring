package com.temzu.spring.command;

import com.temzu.spring.requests.Request;
import com.temzu.spring.requests.crud_req.CreateRequest;
import com.temzu.spring.requests.crud_req.DeleteRequest;
import com.temzu.spring.requests.crud_req.ReadRequest;
import com.temzu.spring.requests.crud_req.UpdateRequest;
import com.temzu.spring.requests.default_req.*;

import java.util.Arrays;

public enum CommandRequest {
    UNKNOWN("unknown", new UnknownRequest()),
    HELP("help", new HelpRequest()),
    CREATE("create", new CreateRequest()),
    GET("get", new ReadRequest()),
    UPDATE("update", new UpdateRequest()),
    DELETE("delete", new DeleteRequest()),
    STOP("stop", new StopRequest()),
    COUNT("count", new CountRequest()),
    AVG("avg", new AvgRequest());

    private final String qualifier;

    private final Request request;

    CommandRequest(String qualifier, Request request) {
        this.qualifier = qualifier;
        this.request = request;
    }

    public static CommandRequest defineCommand(String command) {
        String cmd = command.toLowerCase();
        return Arrays.stream(CommandRequest.values())
                .filter(commandRequest -> commandRequest.qualifier.equals(cmd))
                .findAny()
                .orElse(UNKNOWN);
    }

    public Request getRequest() {
        return request;
    }
}
