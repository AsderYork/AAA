package com.triplea;

class UserData {
    String username;
    String name;
    String hashedPassword;
    String salt;
    int id;

    public UserData(String username, String name, String hashedPassword, String salt, int id) {
        this.username = username;
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.id = id;
    }
}
