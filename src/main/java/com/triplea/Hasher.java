package com.triplea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

    private static final Logger logger = LogManager.getLogger("Hasher");
    private static String algorithmName;

    public static String hashPassword(String Password, String Salt, String AlgorithmName) throws NoSuchAlgorithmException {
        return hash(hash(Password,AlgorithmName) + Salt,AlgorithmName);
    }

    private static String hash(String Value, String AlgorithmName) throws NoSuchAlgorithmException {
        //Метод поддержки. Принимает строку, возвращает её хэш

        MessageDigest Digest;

        Digest = MessageDigest.getInstance(AlgorithmName);

        Digest.reset();
        Digest.update(Value.getBytes());
        byte[] ReturnedByteCode = null;
        ReturnedByteCode = Digest.digest();


        BigInteger hashInBigInteger = new BigInteger(1, ReturnedByteCode);

        return hashInBigInteger.toString(16);
    }
}
