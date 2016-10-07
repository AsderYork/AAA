package com.triplea;

import java.util.HashMap;
import java.util.Objects;

/*
Модуль обработки данных пользователей. Обеспечивает идентификацию пользователя
        */


public class UserManager {
    HashMap<String, UserData> Map;//Карта пользователей.

    public UserManager() {
        Map = new HashMap<String, UserData>();
    }

    /*Поиск пользователя по имени и паролю В случае успеха возвращает через
    * пареметр OUT_UserID, идентификатор пользователя*/
    public EXIT_CODES FindUser(String UserLogin, String InputPassword, int OUT_UserID) {
        UserData Data = GetUserData(UserLogin);

        //Если пользователя с таким именем нет, сообщаем об этом
        if (Data == null) {
            System.out.println("Wrong username");
            return EXIT_CODES.WRONG_LOGIN;
        }


        if (Objects.equals(Hasher.HashPassword(InputPassword, Data.Salt), Data.HashedPassword)) {
            //Если хэш пароля верный, записываем ID
            OUT_UserID = Data.ID;
            System.out.println("Welcome " + Data.Name);
            Accounter.Login(Data);
            return EXIT_CODES.DO_NOT_EXIT;
        }

        //Если же хэш оказался неверным, отмечаем это
        System.out.println("Wrong password");
        return EXIT_CODES.WRONG_PASSWORD;
    }

    //Простой метод добавления пользователя
    public void addUser(String UserLogin, String UserName, String Password, String Salt) {
        UserData Data = new UserData();
        Data.Username = UserLogin;
        Data.HashedPassword = Hasher.HashPassword(Password, Salt);
        Data.Salt = Salt;
        Data.Name = UserName;
        Map.put(UserLogin, Data);
        Data.ID = Map.size();

    }


    //Метод получения данных о пользователе. Вынесен в отдельный метод что бы скрыть реализацию
    private UserData GetUserData(String Username) {
        UserData Data = Map.get(Username);
        return Data;
    }

}

class UserData {//Структура данных пользователя
    String Username;
    String Name;
    String HashedPassword;
    String Salt;
    int ID;
}
