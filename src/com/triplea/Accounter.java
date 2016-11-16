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

    private static Accounter singleton;
    private TreeMap<String, AccountMessage> log;
    private Accounter() {
        log = new TreeMap<>();
    }

    static private void CheckForSingleton() {
        if (singleton == null) {
            singleton = new Accounter();
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
                0,
                LocalDate.now(),
                LocalDate.now()
        );

        singleton.log.put(String.valueOf(singleton.log.size()), MSG);

    }

    public static void ResourceAccessSuccess(UserInput input, int userid) {
        CheckForSingleton();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        AccountMessage MSG = new AccountMessage("AccessGranted",
                dateFormat.format(date),
                userid,
                input.role,
                input.resource,
                input.valueOfResourse,
                input.startDateOfResourceRequest,
                input.endDateOfResourceRequest);

        singleton.log.put(String.valueOf(singleton.log.size()), MSG);
    }

    public static void AccessRejected(UserInput input, int userid) {
        CheckForSingleton();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        AccountMessage MSG = new AccountMessage("AccessRejected",
                dateFormat.format(date),
                userid,
                input.role,
                input.resource,
                input.valueOfResourse,
                input.startDateOfResourceRequest,
                input.endDateOfResourceRequest);

        singleton.log.put(String.valueOf(singleton.log.size()), MSG);
    }

}


