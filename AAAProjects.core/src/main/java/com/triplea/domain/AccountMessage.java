package com.triplea.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AccountMessage {
    public String action; //login/AccessGranted/accessRejected
    public String accountDate;

    public Integer userid;
    public String role;
    public String path;
    public Integer value;
    public LocalDate dateStart;
    public LocalDate dateFinished;
}