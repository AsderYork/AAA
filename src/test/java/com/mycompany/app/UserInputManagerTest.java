package com.mycompany.app;

import com.triplea.ExitCode;
import com.triplea.domain.UserInput;
import org.junit.Test;
import com.triplea.UserInputManager;

import static org.junit.Assert.assertEquals;

/**
 * Created by Asder on 08.12.2016.
 */
public class UserInputManagerTest {
    @Test
    public void TestLP() {
        String args[] = {"-login", "jdoe", "-pass", "XXX"};

       UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.DO_NOT_EXIT);

    }
    @Test
    public void TestNONE() {
        String args[] = {};

        UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.EXIT_SUCCESSFULLY);

    }
    @Test
    public void TestMESS() {
        String args[] = {"-ASD"};

        UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.EXIT_SUCCESSFULLY);
    }
    @Test
    public void TestH() {
        String args[] = {"-h"};

        UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.EXIT_SUCCESSFULLY);
    }

    @Test
    public void TestL() {
        String args[] = {"-login", "a"};

        UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.EXIT_SUCCESSFULLY);

    }
    @Test
    public void TestP() {
        String args[] = {"-pass", "a"};

        UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.EXIT_SUCCESSFULLY);
    }
    @Test
    public void TestLPR() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "READ"};

        UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.EXIT_SUCCESSFULLY);
    }
    @Test
    public void TestLPRm() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "MESS"};

        UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.EXIT_SUCCESSFULLY);
    }
    @Test
    public void TestLPRR() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "READ", "-res", "a"};

        UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.DO_NOT_EXIT);
    }
    @Test
    public void TestLPRRA() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "READ", "-res", "a", "-ds", "2016-01-12",
                "-de", "2016-01-13",  "-val", "100"};

        UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.DO_NOT_EXIT);
    }
    @Test
    public void TestLPRRAm() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "READ", "-res", "a", "-ds", "xxx-xx-x",
                "-de", "2016-01-13",  "-val", "100"};

        UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.DO_NOT_EXIT);
    }
    @Test
    public void TestLPRRAmm() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "READ", "-res", "a", "-ds", "2016-01-12",
                "-de", "2016-01-13",  "-val", "XXX"};

        UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.DO_NOT_EXIT);
    }
    @Test
    public void TestLPRRAmmm() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "READ", "-res", "a", "-ds", "2016-01-12",
                "-de", "2016-01-13",  "-val", "-1"};

        UserInputManager UIM = new UserInputManager(args);
        UserInput UI = new UserInput();

        assertEquals(UIM.parse(UI), ExitCode.DO_NOT_EXIT);
    }
}
