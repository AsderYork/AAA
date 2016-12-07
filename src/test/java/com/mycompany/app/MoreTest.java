package com.mycompany.app;

import junit.framework.Assert;
import com.triplea.Hasher;
import org.junit.Test;

/**
 * Created by Asder on 07.12.2016.
 */
public class MoreTest extends Assert {

    @Test
    public void testHash() {

        assertEquals(Hasher.HashPassword("teststring","Salt"),"ad176b8aa379361cafa1f6777d56ab66");
    }
}

