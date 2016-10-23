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


<<<<<<< HEAD
        if (Objects.equals(Hasher.HashPassword(InputPassword, Data.Salt), Data.HashedPassword)) {
            //Если хэш пароля верный, записываем ID
            LastUserID = Data.ID;
            System.out.println("Welcome " + Data.Name);
            Accounter.Login(Data);
            return EXIT_CODES.DO_NOT_EXIT;
=======
        if (Objects.equals(Hasher.HashPassword(InputPassword, data.Salt), data.HashedPassword)) {

            LastUserID = data.id;
            System.out.println("Welcome " + data.Name);
            Accounter.Login(data);
            return ExitCode.DO_NOT_EXIT;
>>>>>>> master
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

<<<<<<< HEAD
    //Метод получения данных о пользователе. Вынесен в отдельный метод что бы скрыть реализацию
=======
>>>>>>> master
    private UserData GetUserData(String Username) {
        return Map.get(Username);
    }

    public int getLastUserID() {
<<<<<<< HEAD
        assert (LastUserID >= 0);/*Ага, теперь у нас получилась временная зависимость между этим методом, и FindUser.
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
=======
        assert (LastUserID >= 0);
>>>>>>> master
        return LastUserID;
    }

}

