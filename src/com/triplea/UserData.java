package com.triplea;

class UserData {
    String Username;
    String Name;
    String HashedPassword;
    String Salt;
    int id;

    public UserData(String username, String name, String hashedPassword, String salt, int id) {
        Username = username;
        Name = name;
        HashedPassword = hashedPassword;
        Salt = salt;
        this.id = id;
    }
}
