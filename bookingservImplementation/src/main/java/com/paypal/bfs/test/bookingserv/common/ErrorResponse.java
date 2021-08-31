package com.paypal.bfs.test.bookingserv.common;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {

    String message;
    List<String> details;

    public ErrorResponse(String msg, List<String> list) {
        super();
        this.message = msg;
        this.details = list;
    }
}
