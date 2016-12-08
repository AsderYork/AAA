package com.mycompany.app;

import com.triplea.Accounter;
import com.triplea.ExitCode;
import com.triplea.dao.AccountMessageAccess;
import com.triplea.domain.AccountMessage;
import com.triplea.domain.UserData;
import com.triplea.domain.UserInput;
import org.junit.Test;
import org.mockito.internal.matchers.Any;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by Asder on 07.12.2016.
 */
public class AccounterTest {

    @Test
    public void testLogin() {

        AccountMessageAccess mock = mock(AccountMessageAccess.class);
        Accounter.login(new UserData("login","passwd","hash","salt"),mock);
        //Checking that Acounter realy sends data to AMS
        verify(mock).putMessage(any(AccountMessage.class));

    }
    @Test
    public void testSuccess() {

        AccountMessageAccess mock = mock(AccountMessageAccess.class);

        assertEquals(Accounter.resourceAccessSuccess(new UserInput(),1, mock), ExitCode.EXIT_SUCCESSFULLY);
        verify(mock).putMessage(any(AccountMessage.class));
    }
    @Test
    public void testRejected() {

        AccountMessageAccess mock = mock(AccountMessageAccess.class);
        assertEquals(Accounter.accessRejected(new UserInput(),1, mock), ExitCode.EXIT_SUCCESSFULLY);

        verify(mock).putMessage(any(AccountMessage.class));
    }

    @Test
    public void testSuccessWrongRes() {

        AccountMessageAccess mock = mock(AccountMessageAccess.class);
        UserInput userInput = new UserInput();
        userInput.valueOfResourse = -1;
        assertEquals(Accounter.resourceAccessSuccess(userInput,1, mock), ExitCode.INCORRECT_ACTIVITY);

    }
    @Test
    public void TestRejectedWrongRes() {

        AccountMessageAccess mock = mock(AccountMessageAccess.class);
        UserInput userInput = new UserInput();
        userInput.valueOfResourse = -1;
        assertEquals(Accounter.accessRejected(userInput,1, mock), ExitCode.INCORRECT_ACTIVITY);
    }
}
