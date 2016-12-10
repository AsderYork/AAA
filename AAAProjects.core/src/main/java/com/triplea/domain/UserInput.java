package com.triplea.domain;

import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class UserInput {
    public String name;
    public String password;
    public String resource;
    public String role;
    public LocalDate startDateOfResourceRequest;
    public LocalDate endDateOfResourceRequest;
    public int valueOfResourse;
    //Describes the amount of valid input parts; 0-Nothing; 1-Name/Password; 2- 1+Resource/Role; 3-2+Dates/value
    public int levelOfInput;
}