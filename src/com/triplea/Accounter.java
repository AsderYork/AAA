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

    static private void checkForSingleton() {
        if (singleton == null) {
            singleton = new Accounter();
        }
    }

    public static void login(UserData Data) {
        checkForSingleton();


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        AccountMessage MSG = new AccountMessage("login",
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

    public static ExitCode resourceAccessSuccess(UserInput input, int userid) {
        checkForSingleton();


        if(input.endDateOfResourceRequest == null)
        {
            return ExitCode.INCORRECT_ACTIVITY;
        }
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

        return ExitCode.EXIT_SUCCESSFULLY;
    }

    public static ExitCode accessRejected(UserInput input, int userid) {
        checkForSingleton();

        if(input.endDateOfResourceRequest == null)
        {
            return ExitCode.INCORRECT_ACTIVITY;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        AccountMessage MSG = new AccountMessage("accessRejected",
                dateFormat.format(date),
                userid,
                input.role,
                input.resource,
                input.valueOfResourse,
                input.startDateOfResourceRequest,
                input.endDateOfResourceRequest);

        singleton.log.put(String.valueOf(singleton.log.size()), MSG);

        return ExitCode.EXIT_SUCCESSFULLY;
    }

}


