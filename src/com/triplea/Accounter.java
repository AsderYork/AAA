package com.triplea;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import java.time.LocalDateTime;
/*
Блок аккаунтинга. Собирает данные о действиях пользователей.
 */

public class Accounter {

    private TreeMap<String, AccountMessage> Log;

    private static Accounter Singleton;

    //Приватный конструктор. Никто, кроме аккаунтера, не может создать экземпляр аккаунтера
    private Accounter()
    {
        Log = new TreeMap<String, AccountMessage>();
    }

    //Проверка наличия синглетона, если нет, то создаем
   static private void CheckForSingleton() {
        if(Singleton == null){
            Singleton = new Accounter();
        }
    }

    //Запись логина
    public static void Login(UserData Data) {
        CheckForSingleton();
        AccountMessage MSG = new AccountMessage();

        MSG.Action = "Login";
        MSG.USERID = Data.ID;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        MSG.AccountDate = dateFormat.format(date); //2014/08/06 15:59:48

        Singleton.Log.put(String.valueOf(Singleton.Log.size()), MSG);

    }

    //Запись успешного доступа
    public static void ResourceAccessSuccess(UserInput Input, int USERID) {
        CheckForSingleton();

        AccountMessage MSG = new AccountMessage();

        MSG.Action = "AccessGranted";
        MSG.USERID = USERID;
        MSG.PATH = Input.resource;
        MSG.Role = Input.role;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        MSG.AccountDate = dateFormat.format(date);

        MSG.DateFinished = Input.endDateOfResourceRequest;
        MSG.DateStart = Input.startDateOfResourceRequest;
        MSG.Value = Input.valueOfResourse;
        Singleton.Log.put(String.valueOf(Singleton.Log.size()), MSG);
    }

    //Запись о отказе в доступе
    public static void AccessRejected(UserInput Input, int USERID) {
        CheckForSingleton();

        AccountMessage MSG = new AccountMessage();

        MSG.Action = "AccessRejected";
        MSG.USERID = USERID;
        MSG.PATH = Input.resource;
        MSG.Role = Input.role;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        MSG.AccountDate = dateFormat.format(date);

        MSG.DateFinished = Input.endDateOfResourceRequest;
        MSG.DateStart = Input.startDateOfResourceRequest;
        MSG.Value = Input.valueOfResourse;
        Singleton.Log.put(String.valueOf(Singleton.Log.size()), MSG);
    }

}

class AccountMessage {
    String Action; //Login/AccessGranted/AccessRejected
    String AccountDate;

    int USERID;
    String Role;
    String PATH;
    int Value;
    LocalDateTime DateStart;
    LocalDateTime DateFinished;
}
