package com.company;

import java.util.TreeMap;

/**
 * Created by York on 16.09.2016.
 */
public class Accounter {
    public static TreeMap<String,AccountMessage> Log;

    public static void Login(UserData Data)
    {
        AccountMessage MSG = new AccountMessage();

        MSG.Action = "Login";
        MSG.USERID = Data.ID;
    }

}
