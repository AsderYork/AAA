package com.triplea;

import java.util.HashMap;
import java.util.Objects;


class UserID
{
    private int UserID;

    public UserID(int Value){UserID = Value;}

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}


/*
Модуль обработки данных пользователей. Обеспечивает идентификацию пользователя
        */


public class UserManager {
    HashMap<String, UserData> Map;//Карта пользователей.

    public UserManager() {
        Map = new HashMap<>();
    }

    /*Поиск пользователя по имени и паролю В случае успеха возвращает через
    * пареметр OUT_UserID, идентификатор пользователя*/
    /*Жизнь программиста на Java похожа на попытку отобрать портфель у кучки школьных задир:
    * Кажеться в близки к цели, но это всего лишь иллюзия. Язык никогда не дает вам столько контроля, сколько нужно
    * Так к примеру язык отказывается передать int по ссылке, как он поступил бы с любым другим объектом
    * Именно для этого сейчас я буду городить целый класс-обертку, только что бы обойти это ограничение - очередной
    * прыжок в попытке отнять метафорический портфель*/
    public EXIT_CODES FindUser(String UserLogin, String InputPassword, UserID OUT_UserID) {
        UserData Data = GetUserData(UserLogin);

        //Если пользователя с таким именем нет, сообщаем об этом
        if (Data == null) {
            System.out.println("Wrong username");
            return EXIT_CODES.WRONG_LOGIN;
        }


        if (Objects.equals(Hasher.HashPassword(InputPassword, Data.Salt), Data.HashedPassword)) {
            //Если хэш пароля верный, записываем ID
            OUT_UserID.setUserID(Data.ID);
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
        return Map.get(Username);
    }

}

class UserData {//Структура данных пользователя
    String Username;
    String Name;
    String HashedPassword;
    String Salt;
    int ID;
}
