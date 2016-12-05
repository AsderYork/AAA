package com.triplea;

class UserData {
    String username;
    String name;
    String hashedPassword;
    String salt;
    Integer id;

    public UserData(String username, String name, String hashedPassword, String salt, int id) {
        this.username = username;
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.id = id;
    }

    public UserData(String username, String name, String hashedPassword, String salt) {
        this.username = username;
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.id = null;
    }

    public UserData() {
        this.username = null;
        this.name = null;
        this.hashedPassword = null;
        this.salt = null;
        this.id = null;
    }
}
