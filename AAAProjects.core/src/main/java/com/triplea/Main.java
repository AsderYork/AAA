package com.triplea;

import com.triplea.dao.AccountMessageAccess;
import com.triplea.dao.ResourceDataAccess;
import com.triplea.dao.UserDataAccess;
import com.triplea.domain.UserInput;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;

@Log4j2
public class Main {

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


        log.info("The beginning");

        UserInput input = new UserInput();
        UserDataAccess uad = new UserDataAccess();
        AccountMessageAccess ama = new AccountMessageAccess(new DBWorker());

        UserManager um = new UserManager(ama, uad);
        ResourceManager rm = new ResourceManager(new ResourceDataAccess());



        UserInputManager consoleManager = new UserInputManager(args);
        log.info("And now we parsing userinput. It may finish the program, if input is incorrect, btw");
        checkExitCode(consoleManager.parse(input));

        log.info("If we're here, then we at least have pass/username. Let's work with that! It also may throw us");
        checkExitCode(um.findUser(input.name, input.password));

        log.info("So we're here till now, eh? Look's like User/pass is correct. But is there enything else?");
        if (input.levelOfInput < 2) {
            log.info("Looks like nothing more to compute. Finishing ");
            checkExitCode(ExitCode.EXIT_SUCCESSFULLY);
        }

        log.info("Ooh, we receive a resource request! Let's check if we can provide it!");
        if (rm.isResourceAccessible(um.getLastUserID(), input.resource, input.role)) {
            log.info("Yup. We can provide that one!");
            if (input.levelOfInput > 2) {
                log.info("We can even account that!");
                checkExitCode(Accounter.resourceAccessSuccess(input, um.getLastUserID(),ama));
            }
        } else {
            log.info("Can't provide that resource");
            if (input.levelOfInput > 2) {
                log.info("At least we can write this down");
                if (Accounter.accessRejected(input, um.getLastUserID(), ama) == ExitCode.EXIT_SUCCESSFULLY) {
                    checkExitCode(ExitCode.RESOURCE_PERMISSION_DENIED);
                }
                log.info("Nope. We can't. Wrong date or something");
                checkExitCode(ExitCode.INCORRECT_ACTIVITY);
            }
            checkExitCode(ExitCode.RESOURCE_PERMISSION_DENIED);
        }

        DBWorker.disconnect();
        System.exit(0);
    }
}

