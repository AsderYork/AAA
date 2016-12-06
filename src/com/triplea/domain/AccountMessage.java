package com.triplea;

import java.time.LocalDate;


public class AccountMessage {
    String action; //login/AccessGranted/accessRejected
    String accountDate;

    Integer userid;
    String role;
    String path;
    Integer value;
    LocalDate dateStart;
    LocalDate dateFinished;

    public AccountMessage(String action,
                          String accountDate,
                          Integer userid,
                          String role,
                          String path,
                          Integer value,
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

    public AccountMessage() {
        this.action = null;
        this.accountDate = null;
        this.userid = null;
        this.role = null;
        this.path = null;
        this.value = null;
        this.dateStart = null;
        this.dateFinished = null;
    }
}