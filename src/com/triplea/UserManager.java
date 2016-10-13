package com.triplea;

import java.util.HashMap;
import java.util.Objects;


/*
Модуль обработки данных пользователей. Обеспечивает идентификацию пользователя
        */


public class UserManager {
    HashMap<String, UserData> Map;//Карта пользователей.

    public UserManager() {
        Map = new HashMap<>();
        LastUserID = -1;
    }

    /*Поиск пользователя по имени и паролю В случае успеха возвращает через
    * пареметр OUT_UserID, идентификатор пользователя*/
    /*Жизнь программиста на Java похожа на попытку отобрать портфель у кучки школьных задир:
    * Кажеться в близки к цели, но это всего лишь иллюзия. Язык никогда не дает вам столько контроля, сколько нужно
    * Так к примеру язык отказывается передать int по ссылке, как он поступил бы с любым другим объектом
    * Именно для этого сейчас я буду городить целый класс-обертку, только что бы обойти это ограничение - очередной
    * прыжок в попытке отнять метафорический портфель*/
    public EXIT_CODES FindUser(String UserLogin, String InputPassword) {
        UserData Data = GetUserData(UserLogin);

        //Если пользователя с таким именем нет, сообщаем об этом
        if (Data == null) {
            System.out.println("Wrong username");
            return EXIT_CODES.WRONG_LOGIN;
        }


        if (Objects.equals(Hasher.HashPassword(InputPassword, Data.Salt), Data.HashedPassword)) {
            //Если хэш пароля верный, записываем ID
            LastUserID=Data.ID;
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

        UserData Data = new UserData(
                UserLogin,
                UserName,
                Hasher.HashPassword(Password, Salt),
                Salt,
                Map.size());


    }


    //Метод получения данных о пользователе. Вынесен в отдельный метод что бы скрыть реализацию
    private UserData GetUserData(String Username) {
        return Map.get(Username);
    }

    public int getLastUserID() {
       assert(LastUserID >=0);/*Ага, теперь у нас получилась временная зависимость между этим методом, и FindUser.
       Это связано с тем, что FindUser является главной функцией, а потом обязана возвращать EXIT_CODE, а значит
       вернуть ID не может.
       Другие варианты решения
       1) Разбить функцию на куски. Проверка, есть ли пользователь с таким именем. Есть ли пользователь с таким паролем
       Это снижает уровень абстракции, а так же приводит к многократному выполнению одних и тех же операций(
       Что в свою очередь можно решить кэшированием значений, но это совершенно без надобность усложнит программу)
       2) Изменить архитектуру проэкта - сделать так, что функции возвращали EXIT_CODE в виде исключений. Это хороший
       вариант, и скорее всего я так и сделаю. Но это третует пересмотра проекта, так что пока не стоит с этим
       торопиться.
        */
        return LastUserID;
    }

    private int LastUserID;

}

