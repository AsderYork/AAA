package com.triplea;

public enum EXIT_CODES {
    DO_NOT_EXIT(-1),
    EXIT_SUCCESSFULLY(0),
    WRONG_LOGIN(1),
    WRONG_PASSWORD(2),
    WRONG_ROLE(3),
    RESOURCE_PERMISSION_DENIED(4),
    INCORRECT_ACTIVITY(5);

    private int statusCode;

    EXIT_CODES(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}