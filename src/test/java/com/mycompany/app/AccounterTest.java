package com.mycompany.app;

import com.triplea.Accounter;
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
    public void TestLogin() {

        AccountMessageAccess AMA = mock(AccountMessageAccess.class);
        Accounter.login(new UserData("login","passwd","hash","salt"),AMA);

        verify(AMA).putMessage(any(AccountMessage.class));
    }
    @Test
    public void TestSuccess() {

        AccountMessageAccess AMA = mock(AccountMessageAccess.class);
        Accounter.resourceAccessSuccess(new UserInput(),1, AMA);

        verify(AMA).putMessage(any(AccountMessage.class));
    }
    @Test
    public void TestRejected() {

        AccountMessageAccess AMA = mock(AccountMessageAccess.class);
        Accounter.accessRejected(new UserInput(),1, AMA);

        verify(AMA).putMessage(any(AccountMessage.class));
    }

    @Test
    public void TestSuccessWrongRes() {

        AccountMessageAccess AMA = mock(AccountMessageAccess.class);
        UserInput UI = new UserInput();
        UI.valueOfResourse = -1;
        Accounter.resourceAccessSuccess(UI,1, AMA);

    }
    @Test
    public void TestRejectedWrongRes() {

        AccountMessageAccess AMA = mock(AccountMessageAccess.class);
        UserInput UI = new UserInput();
        UI.valueOfResourse = -1;
        Accounter.accessRejected(UI,1, AMA);
    }
    @Test
    public void TestAM() {

        AccountMessage AM = new AccountMessage();
    }
}
