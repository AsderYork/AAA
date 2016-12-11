package com.triplea;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor

public enum ExitCode {
    DO_NOT_EXIT(-1),
    EXIT_SUCCESSFULLY(0),
    WRONG_LOGIN(1),
    WRONG_PASSWORD(2),
    WRONG_ROLE(3),
    RESOURCE_PERMISSION_DENIED(4),
    INCORRECT_ACTIVITY(5);

   @Getter private int statusCode;
    
}