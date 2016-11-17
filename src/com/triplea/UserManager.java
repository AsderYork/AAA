package com.triplea;

import java.util.HashMap;
import java.util.Objects;


public class UserManager {
    HashMap<String, UserData> Map;
    private int lastUserID;

    public UserManager() {
        Map = new HashMap<>();
        lastUserID = -1;
    }

    public ExitCode findUser(String userLogin, String inputPassword) {
        UserData data = getUserData(userLogin);

        if (data == null) {
            System.out.println("Wrong username");
            return ExitCode.WRONG_LOGIN;
        }


        if (Objects.equals(Hasher.HashPassword(inputPassword, data.salt), data.hashedPassword)) {

            lastUserID = data.id;
            System.out.println("Welcome " + data.name);
            Accounter.login(data);
            return ExitCode.DO_NOT_EXIT;
        }

        System.out.println("Wrong password");
        return ExitCode.WRONG_PASSWORD;
    }

    public void addUser(String userLogin, String userName, String password, String salt) {

        UserData data = new UserData(
                userLogin,
                userName,
                Hasher.HashPassword(password, salt),
                salt,
                Map.size());
        Map.put(userLogin, data);


    }

    private UserData getUserData(String Username) {
        return Map.get(Username);
    }

    public int getLastUserID() {
        assert (lastUserID >= 0);
        return lastUserID;
    }

}

