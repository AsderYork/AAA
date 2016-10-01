package com.triplea;

import java.util.HashMap;
import java.util.Objects;

/*
Модуль обработки данных пользователей. Обеспечивает идентификацию пользователя на основании структуры UserInput
 */


public class UserManager {
    HashMap<String, UserData> Map;//Карта пользователей.

    public UserManager() {
        Map = new HashMap<String, UserData>();
    }

    /*Поиск пользователя по имени и паролю из UserInput. В случае успеха дописывает в поле ID - номер пользоваетеля
  Возвращает 0 в случае успеха
  1 - Неизвестный логин
  2 - Неверный пароль*/
    public int FindUser(UserInput User) {


        //Если пользователя с таким именем нет, сообщаем об этом
        UserData Data = GetUserData(User.UserName);

        if (Data == null) {
            System.out.println("Wrong username");
            return 1;
        }


        if (Objects.equals(Hasher.HashPassword(User.Password, Data.Salt), Data.HashedPassword)) {
            //Если хэш пароля верный, записываем ID пользователя и возвращаем 0
            User.USERID = Data.ID;
            System.out.println("Welcome " + Data.Name);
            Accounter.Login(Data);
            return 0;
        }

        //Если же хэш оказался неверным, отмечаем это и вываливаемся с кодом 2
        System.out.println("Wrong password");
        return 2;
    }

    //Простой метод добавления пользователя
    public void addUser(String Username, String Name, String Password, String Salt) {
        UserData Data = new UserData();
        Data.Username = Username;
        Data.HashedPassword = Hasher.HashPassword(Password, Salt);
        Data.Salt = Salt;
        Data.Name = Name;
        Map.put(Username, Data);
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
