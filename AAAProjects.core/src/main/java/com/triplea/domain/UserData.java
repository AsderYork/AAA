package com.triplea.domain;

import lombok.*;

@AllArgsConstructor
public class UserData {
    public String username;
    public String name;
    public String hashedPassword;
    public String salt;
    public Integer id;
}
