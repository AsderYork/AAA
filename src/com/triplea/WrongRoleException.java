package com.triplea;

public class WrongRoleException extends Exception {
    public WrongRoleException() {
        super();
    }

    public WrongRoleException(String message) {
        super(message);
    }
}
