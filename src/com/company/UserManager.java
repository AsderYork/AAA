package com.company;

import java.util.HashMap;

/**
 * Created by York on 16.09.2016.
 */

public class UserManager {
    HashMap<String, UserData> Map;//Name is the Key!

    public int FindUser(UserInput User) {
        UserData Data = Map.get(User.UserName);
        if (Data == null) {
            System.out.println("Wrong username");
            return 1;
        }//Если пользователя с таким именем нет, сообщаем об этом
        if (Hasher.Hash(User.Password, Data.Salt) != Data.HashedPassword) {
            System.out.println("Wrong password");
            return 2;/*Пользователь найден, но пароль нневерен*/
        }
        User.USERID = Data.ID;
        System.out.println("Welcome "+Data.Name);
        return 0;
    }

    public void addUser(String Username, String Name, String Password, String Salt)
    {
        UserData Data = new UserData();
        Data.Username = Username;
        Data.HashedPassword = Hasher.Hash(Password,Salt);
        Data.Salt = Salt;
        Data.Name = Name;
        Map.put(Username,Data);
        Data.ID = Map.size();

    }

}
