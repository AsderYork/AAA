package com.triplea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

    private static final Logger logger = LogManager.getLogger("Hasher");
    public static String HashPassword(String Password, String Salt) {
        return hash(hash(Password) + Salt);
    }

    private static String hash(String Value) {
        //Метод поддержки. Принимает строку, возвращает её хэш

        MessageDigest Digest;

        try {

            Digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("Something happened to hash func!", e);
            System.exit(-2);
            return "hash error";
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
