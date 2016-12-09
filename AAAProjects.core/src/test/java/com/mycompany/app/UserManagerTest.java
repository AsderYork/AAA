package com.mycompany.app;

import com.triplea.ExitCode;
import com.triplea.UserManager;
import com.triplea.dao.AccountMessageAccess;
import com.triplea.dao.UserDataAccess;
import com.triplea.domain.UserData;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.triplea.ExitCode;

/**
 * Created by Asder on 07.12.2016.
 */



public class UserManagerTest {
    @Test
    public void testWrongData() {
        UserDataAccess userDataAccess = mock(UserDataAccess.class);
        AccountMessageAccess ama = mock(AccountMessageAccess.class);

        UserData ud = new UserData("Login","User","HP","Salt",1);
        when(userDataAccess.getUserByLogin("Login") ).thenReturn(ud);


        UserManager userManager = new UserManager(userDataAccess, ama);
        ExitCode result =  ExitCode.WRONG_LOGIN;
        try {
            result = userManager.findUser(null, null);
        } catch (NoSuchAlgorithmException e) {
           fail();
        }
        assertEquals(result, ExitCode.WRONG_LOGIN);

    }
    @Test
    public void testGoodData() {
        UserDataAccess uda = mock(UserDataAccess.class);
        AccountMessageAccess ama = mock(AccountMessageAccess.class);

        UserData ud = new UserData("Login","User","4fe0ab0674f0685d638141b4bc2d0d0c","A",1);
        when(uda.getUserByLogin("Login") ).thenReturn(ud);


        UserManager um = new UserManager(uda, ama);
        ExitCode result =  ExitCode.WRONG_LOGIN;
        try {
            result = um.findUser("Login", "A");
        } catch (NoSuchAlgorithmException e) {
            fail();
        }
        assertEquals(result, ExitCode.DO_NOT_EXIT);

    }
    @Test
    public void testIncorrectData() {
        UserDataAccess uda = mock(UserDataAccess.class);
        AccountMessageAccess ama = mock(AccountMessageAccess.class);

        UserData userData = new UserData("Login","User","sq","A",1);
        when(uda.getUserByLogin("Login") ).thenReturn(userData);


        UserManager um = new UserManager(uda, ama);
        ExitCode result =  ExitCode.WRONG_LOGIN;
        try {
            result = um.findUser("Login", "A");
        } catch (NoSuchAlgorithmException e) {
            fail();
        }
        assertEquals(result, ExitCode.WRONG_PASSWORD);

    }
}
