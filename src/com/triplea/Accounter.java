package com.triplea;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.TreeMap;
/*
Блок аккаунтинга. Собирает данные о действиях пользователей.
 */

public class Accounter {

    private static Accounter Singleton;
    private TreeMap<String, AccountMessage> Log;
    private Accounter() {
        Log = new TreeMap<>();
    }

    static private void CheckForSingleton() {
        if (Singleton == null) {
            Singleton = new Accounter();
        }
    }

    public static void Login(UserData Data) {
        CheckForSingleton();


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        AccountMessage MSG = new AccountMessage("Login",
                dateFormat.format(date),
                Data.id,
                "",
                "",
<<<<<<< HEAD
                "",
                0,
                LocalDate.now (),
                LocalDate.now ()
=======
                0,
                LocalDate.now(),
                LocalDate.now()
>>>>>>> master
        );

        Singleton.Log.put(String.valueOf(Singleton.Log.size()), MSG);

    }

    public static void ResourceAccessSuccess(UserInput Input, int USERID) {
        CheckForSingleton();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        AccountMessage MSG = new AccountMessage("AccessGranted",
                dateFormat.format(date),
                USERID,
                Input.role,
                Input.resource,
                Input.valueOfResourse,
                Input.startDateOfResourceRequest,
                Input.endDateOfResourceRequest);

        Singleton.Log.put(String.valueOf(Singleton.Log.size()), MSG);
    }

    public static void AccessRejected(UserInput Input, int userid) {
        CheckForSingleton();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        AccountMessage MSG = new AccountMessage("AccessRejected",
                dateFormat.format(date),
                userid,
                Input.role,
                Input.resource,
                Input.valueOfResourse,
                Input.startDateOfResourceRequest,
                Input.endDateOfResourceRequest);

        Singleton.Log.put(String.valueOf(Singleton.Log.size()), MSG);
    }

}


