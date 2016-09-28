package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

/*
Блок аккаунтинга. Собирает данные о действиях пользователей.
 */

public class Accounter {
    public static TreeMap<String, AccountMessage> Log = new TreeMap<String, AccountMessage>();


    //Запись логина
    public static void Login(UserData Data) {
        AccountMessage MSG = new AccountMessage();

        MSG.Action = "Login";
        MSG.USERID = Data.ID;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        MSG.AccountDate = dateFormat.format(date); //2014/08/06 15:59:48

        Log.put(String.valueOf(Log.size()), MSG);

    }

    //Запись успешного доступа
    public static void AccessGranted(UserInput Input) {
        AccountMessage MSG = new AccountMessage();

        MSG.Action = "AccessGranted";
        MSG.USERID = Input.USERID;
        MSG.PATH = Input.PATH;
        MSG.Role = Input.Role;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        MSG.AccountDate = dateFormat.format(date);

        MSG.DateFinished = Input.EndDate;
        MSG.DateStart = Input.StartDate;
        MSG.Value = Input.UsageValue;
        Log.put(String.valueOf(Log.size()), MSG);
    }

    //Запись неуспешного доступа
    public static void AccessRejected(UserInput Input) {
        AccountMessage MSG = new AccountMessage();
        MSG.Action = "AccessRejected";
        MSG.USERID = Input.USERID;
        MSG.PATH = Input.PATH;
        MSG.Role = Input.Role;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        MSG.AccountDate = dateFormat.format(date);
        MSG.DateFinished = Input.EndDate;
        MSG.DateStart = Input.StartDate;
        MSG.Value = Input.UsageValue;
        Log.put(String.valueOf(Log.size()), MSG);
    }

}

class AccountMessage{
    String Action; //Login/AccessGranted/AccessRejected
    String AccountDate;

    int USERID;
    int Role;
    String PATH;
    int Value;
    String DateStart;
    String DateFinished;
}
