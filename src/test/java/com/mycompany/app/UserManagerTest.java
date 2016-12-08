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
    public void TestWrongData() {
        UserDataAccess UDA = mock(UserDataAccess.class);
        AccountMessageAccess AMA = mock(AccountMessageAccess.class);

        UserData UD = new UserData("Login","User","HP","Salt",1);
        when(UDA.getUserByLogin("Login") ).thenReturn(UD);


        UserManager UM = new UserManager(UDA, AMA);
        ExitCode Result =  ExitCode.WRONG_LOGIN;
        try {
            Result = UM.findUser(null, null);
        } catch (NoSuchAlgorithmException e) {
           fail();
        }
        assertEquals(Result, ExitCode.WRONG_LOGIN);

    }
    @Test
    public void TestGoodData() {
        UserDataAccess UDA = mock(UserDataAccess.class);
        AccountMessageAccess AMA = mock(AccountMessageAccess.class);

        UserData UD = new UserData("Login","User","4fe0ab0674f0685d638141b4bc2d0d0c","A",1);
        when(UDA.getUserByLogin("Login") ).thenReturn(UD);


        UserManager UM = new UserManager(UDA, AMA);
        ExitCode Result =  ExitCode.WRONG_LOGIN;
        try {
            Result = UM.findUser("Login", "A");
        } catch (NoSuchAlgorithmException e) {
            fail();
        }
        assertEquals(Result, ExitCode.DO_NOT_EXIT);

    }
    @Test
    public void TestIncorrectData() {
        UserDataAccess UDA = mock(UserDataAccess.class);
        AccountMessageAccess AMA = mock(AccountMessageAccess.class);

        UserData UD = new UserData("Login","User","sq","A",1);
        when(UDA.getUserByLogin("Login") ).thenReturn(UD);


        UserManager UM = new UserManager(UDA, AMA);
        ExitCode Result =  ExitCode.WRONG_LOGIN;
        try {
            Result = UM.findUser("Login", "A");
        } catch (NoSuchAlgorithmException e) {
            fail();
        }
        assertEquals(Result, ExitCode.WRONG_PASSWORD);

    }
    @Test
    public void TestLUID() {
        UserDataAccess UDA = mock(UserDataAccess.class);
        AccountMessageAccess AMA = mock(AccountMessageAccess.class);
        UserManager UM = new UserManager(UDA, AMA);
        assertEquals(UM.getLastUserID(), -1);
    }
}
