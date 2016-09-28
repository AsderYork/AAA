package com.company;
import java.security.*;
import java.math.*;

/*
Модуль хэширования. Обеспечивает независимость от метода шифрования пароля
*/

public class Hasher {

    public static String HashPassword(String Value, String Salt)
    {
        return Hash(Hash(Value)+Salt);
    }

    private static String Hash(String Value)
    {
        //Метод поддуржки. Принимает строку, возвращает её хэш

        MessageDigest Digest = null;

        try
        {//Дэйджест может не найти метод и выбросить исключение. Ловим его и возвращаем значение ошибки
            Digest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return "ERROR";
        }

        //Подготавливаем дайджест к работе и вностим в него нашу строку
        Digest.reset();
        Digest.update(Value.getBytes());

        //Подготавливаем переменную для хранения результата
        byte[] ReturnedByteCode = new byte[0];
        ReturnedByteCode = Digest.digest();


        BigInteger HashInBigInteger = new BigInteger(1, ReturnedByteCode);

        String md5Hex = HashInBigInteger.toString(16);

        return md5Hex;
    }
}
