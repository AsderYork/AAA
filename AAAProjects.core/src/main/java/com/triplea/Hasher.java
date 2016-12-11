package com.triplea;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Log4j2
public class Hasher {

    private static String algorithmName;

    public static String hashPassword(String password, String salt, String algorithmName) throws NoSuchAlgorithmException {
        return hash(hash(password, algorithmName) + salt, algorithmName);
    }

    private static String hash(String value, String algorithmName) throws NoSuchAlgorithmException {
        //Метод поддержки. Принимает строку, возвращает её хэш

        MessageDigest digest;

        digest = MessageDigest.getInstance(algorithmName);

        digest.reset();
        digest.update(value.getBytes());
        byte[] returnedByteCode = null;
        returnedByteCode = digest.digest();


        BigInteger hashInBigInteger = new BigInteger(1, returnedByteCode);

        return hashInBigInteger.toString(16);
    }
}
