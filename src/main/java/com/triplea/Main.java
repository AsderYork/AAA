package com.triplea;

import com.triplea.dao.AccountMessageAccess;
import com.triplea.dao.ResourceDataAccess;
import com.triplea.dao.UserDataAccess;
import com.triplea.domain.UserInput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger("Main");

    private static void checkExitCode(ExitCode code) {
        if (code.getStatusCode() == -1) {
            return;
        }
        DBWorker.disconnect();
        System.exit(code.getStatusCode());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        DBWorker.connectToLocalDB();
        DBWorker.migrate();


        LOGGER.info("The beginning");

        UserInput input = new UserInput();
        UserManager um = new UserManager(new UserDataAccess(), new AccountMessageAccess());
        ResourceManager rm = new ResourceManager(new ResourceDataAccess());



        UserInputManager consoleManager = new UserInputManager(args);
        LOGGER.info("And now we parsing userinput. It may finish the program, if input is incorrect, btw");
        checkExitCode(consoleManager.parse(input));

        LOGGER.info("If we're here, then we at least have pass/username. Let's work with that! It also may throw us");
        checkExitCode(um.findUser(input.name, input.password));

        LOGGER.info("So we're here till now, eh? Look's like User/pass is correct. But is there enything else?");
        if (input.levelOfInput < 2) {
            LOGGER.info("Looks like nothing more to compute. Finishing ");
            checkExitCode(ExitCode.EXIT_SUCCESSFULLY);
        }

        LOGGER.info("Ooh, we receive a resource request! Let's check if we can provide it!");
        if (rm.isResourceAccessible(um.getLastUserID(), input.resource, input.role)) {
            LOGGER.info("Yup. We can provide that one!");
            if (input.levelOfInput > 2) {
                LOGGER.info("We can even account that!");
                checkExitCode(Accounter.resourceAccessSuccess(input, um.getLastUserID(),new AccountMessageAccess()));
            }
        } else {
            LOGGER.info("Can't provide that resource");
            if (input.levelOfInput > 2) {
                LOGGER.info("At least we can write this down");
                if (Accounter.accessRejected(input, um.getLastUserID(),new AccountMessageAccess()) == ExitCode.EXIT_SUCCESSFULLY) {
                    checkExitCode(ExitCode.RESOURCE_PERMISSION_DENIED);
                }
                LOGGER.info("Nope. We can't. Wrong date or something");
                checkExitCode(ExitCode.INCORRECT_ACTIVITY);
            }
            checkExitCode(ExitCode.RESOURCE_PERMISSION_DENIED);
        }

        DBWorker.disconnect();
        System.exit(0);
    }
}

