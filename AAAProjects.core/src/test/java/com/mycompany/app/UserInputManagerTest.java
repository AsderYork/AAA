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
    public void testLP() {
        String args[] = {"-login", "jdoe", "-pass", "XXX"};

       UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.DO_NOT_EXIT);

    }
    @Test
    public void testNONE() {
        String args[] = {};

        UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.EXIT_SUCCESSFULLY);

    }
    @Test
    public void testMESS() {
        String args[] = {"-ASD"};

        UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.EXIT_SUCCESSFULLY);
    }
    @Test
    public void testH() {
        String args[] = {"-h"};

        UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.EXIT_SUCCESSFULLY);
    }

    @Test
    public void testL() {
        String args[] = {"-login", "a"};

        UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.EXIT_SUCCESSFULLY);

    }
    @Test
    public void testP() {
        String args[] = {"-pass", "a"};

        UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.EXIT_SUCCESSFULLY);
    }
    @Test
    public void testLPR() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "READ"};

        UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.EXIT_SUCCESSFULLY);
    }
    @Test
    public void testLPRm() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "MESS"};

        UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.EXIT_SUCCESSFULLY);
    }
    @Test
    public void testLPRR() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "READ", "-res", "a"};

        UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.DO_NOT_EXIT);
    }
    @Test
    public void testLPRRA() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "READ", "-res", "a", "-ds", "2016-01-12",
                "-de", "2016-01-13",  "-val", "100"};

        UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.DO_NOT_EXIT);
    }
    @Test
    public void testLPRRAm() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "READ", "-res", "a", "-ds", "xxx-xx-x",
                "-de", "2016-01-13",  "-val", "100"};

        UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.DO_NOT_EXIT);
    }
    @Test
    public void testLPRRAmm() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "READ", "-res", "a", "-ds", "2016-01-12",
                "-de", "2016-01-13",  "-val", "XXX"};

        UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.DO_NOT_EXIT);
    }
    @Test
    public void testLPRRAmmm() {
        String args[] = {"-login", "jdoe", "-pass", "XXX", "-role", "READ", "-res", "a", "-ds", "2016-01-12",
                "-de", "2016-01-13",  "-val", "-1"};

        UserInputManager uim = new UserInputManager(args);
        UserInput ui = new UserInput();

        assertEquals(uim.parse(ui), ExitCode.DO_NOT_EXIT);
    }
}
