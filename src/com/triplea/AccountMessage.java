package com.triplea;

import java.ime.LocalDate;


public class AccountMessage {
    String Action; //Login/AccessGranted/AccessRejected
    String AccountDate;

    int USERID;
    String Role;
    String PATH;
    int Value;
    LocalDate DateStart;
    LocalDate DateFinished;

    public AccountMessage(String action,
                          String accountDate,
                          int USERID,
                          String role,
                          String PATH,
                          int value,
                          LocalDate dateStart,
                          LocalDate dateFinished) {
        Action = action;
        AccountDate = accountDate;
        this.USERID = USERID;
        Role = role;
        this.PATH = PATH;
        Value = value;
        DateStart = dateStart;
        DateFinished = dateFinished;
    }
}