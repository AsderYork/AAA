package com.triplea;

class UserData {//Структура данных пользователя
    String Username;
    String Name;
    String HashedPassword;
    String Salt;
    int ID;
    public UserData(String username, String name, String hashedPassword, String salt, int ID) {
        Username = username;
        Name = name;
        HashedPassword = hashedPassword;
        Salt = salt;
        this.ID = ID;
    }
}
