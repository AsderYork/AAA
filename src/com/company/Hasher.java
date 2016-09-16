package com.company;

/**
 * Created by York on 16.09.2016.
 */
public class Hasher {
    public static String Hash(String Value, String Salt)
    {
        String Str = Value + Salt;
        Str = String.valueOf(Str.hashCode());
        return Str;
    }
}
