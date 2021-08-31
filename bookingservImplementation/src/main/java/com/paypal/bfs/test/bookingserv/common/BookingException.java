package com.paypal.bfs.test.bookingserv.common;

public class BookingException extends RuntimeException {

    public BookingException() {
        super();
    }

    public BookingException(String message) {
        super(message);
    }

    public BookingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingException(Throwable cause) {
        super(cause);
    }

    public BookingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
