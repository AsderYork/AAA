package com.triplea;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

    public static String HashPassword(String Password, String Salt) {
        return Hash(Hash(Password) + Salt);
    }

    private static String Hash(String Value) {
        //Метод поддуржки. Принимает строку, возвращает её хэш

        MessageDigest Digest;

        try {

            Digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            assert (false);
            return "Hash error. You should'n see this because of assert!";
        }

        //Подготавливаем дайджест к работе и вностим в него нашу строку
        Digest.reset();
        Digest.update(Value.getBytes());

        //Подготавливаем переменную для хранения результата
        byte[] ReturnedByteCode;
        ReturnedByteCode = Digest.digest();


        BigInteger hashInBigInteger = new BigInteger(1, ReturnedByteCode);

        return hashInBigInteger.toString(16);
    }
}
