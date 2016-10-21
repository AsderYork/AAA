package com.triplea;

import java.util.HashMap;
import java.util.Objects;


public class UserManager {
    HashMap<String, UserData> Map;//Карта пользователей.
    private int LastUserID;

    public UserManager() {
        Map = new HashMap<>();
        LastUserID = -1;
    }

    public ExitCode FindUser(String UserLogin, String InputPassword) {
        UserData data = GetUserData(UserLogin);

        if (data == null) {
            System.out.println("Wrong username");
            return ExitCode.WRONG_LOGIN;
        }


        if (Objects.equals(Hasher.HashPassword(InputPassword, data.Salt), data.HashedPassword)) {

            LastUserID = data.id;
            System.out.println("Welcome " + data.Name);
            Accounter.Login(data);
            return ExitCode.DO_NOT_EXIT;
        }

        System.out.println("Wrong password");
        return ExitCode.WRONG_PASSWORD;
    }

    public void addUser(String UserLogin, String UserName, String Password, String Salt) {

        UserData data = new UserData(
                UserLogin,
                UserName,
                Hasher.HashPassword(Password, Salt),
                Salt,
                Map.size());
        Map.put(UserLogin, data);


    }

    private UserData GetUserData(String Username) {
        return Map.get(Username);
    }

    public int getLastUserID() {
        assert (LastUserID >= 0);
        return LastUserID;
    }

}

