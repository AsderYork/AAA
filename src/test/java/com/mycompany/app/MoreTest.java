package com.mycompany.app;

import com.triplea.Hasher;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Asder on 07.12.2016.
 */
public class MoreTest {

    @Test
    public void testHash() {
        try {
            assertEquals(Hasher.hashPassword("teststring", "Salt", "MD5"), "ad176b8aa379361cafa1f6777d56ab66");
        } catch (NoSuchAlgorithmException e) {
            fail();
        }
    }
    @Test
    public void testHashWrongAlgorithm() {
        try {
            Hasher.hashPassword("teststring", "Salt", "WRONG");
            fail();
        } catch (NoSuchAlgorithmException e) {
             ;
        }
    }

}

