package com.triplea;

import java.time.LocalDate;


public class AccountMessage {
    String action; //login/AccessGranted/accessRejected
    String accountDate;

    int userid;
    String role;
    String path;
    int value;
    LocalDate dateStart;
    LocalDate dateFinished;

    public AccountMessage(String action,
                          String accountDate,
                          int userid,
                          String role,
                          String path,
                          int value,
                          LocalDate dateStart,
                          LocalDate dateFinished) {
        this.action = action;
        this.accountDate = accountDate;
        this.userid = userid;
        this.role = role;
        this.path = path;
        this.value = value;
        this.dateStart = dateStart;
        this.dateFinished = dateFinished;
    }
}