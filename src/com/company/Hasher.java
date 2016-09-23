package com.company;


public class Hasher {
    public static String Hash(String Value, String Salt) {
        String Str = Value + Salt;
        Str = String.valueOf(Str.hashCode());
        return Str;
    }
}
