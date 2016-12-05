package com.triplea;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger("Main");

    private static void checkExitCode(ExitCode code) {
        if (code.getStatusCode() == -1) {
            return;
        }

        DBWorker.disconnect();
        System.exit(code.getStatusCode());
    }

    public static void main(String[] args) {
        DBWorker.Migrate();
        DBWorker.connectToLocalDB();


        logger.info("The beginning\nFilling everything with data");

        UserInput input = new UserInput();


        UserManager um = new UserManager();
        um.addUser("jdoe", "John Doe", "sup3rpaZZ", "Salt");
        um.addUser("jrow", "Jane Row", "Qweqrty12", "AnotherSalt");


        ResourceManager rm = new ResourceManager();
        rm.AddPermission("a", 1, 1);
        rm.AddPermission("a.b", 2, 1);
        rm.AddPermission("a.b.c", 4, 2);
        rm.AddPermission("a.bc", 4, 1);


        UserInputManager ConsoleManager = new UserInputManager(args);
        logger.info("And now we parsing userinput. It may finish the program, if input is incorrect, btw");
        checkExitCode(ConsoleManager.parse(input));

        logger.info("If we're here, then we at least have pass/username. Let's work with that! It also may throw us");
        checkExitCode(um.findUser(input.name, input.password));

        logger.info("So we're here till now, eh? Look's like User/pass is correct. But is there enything else?");
        if(input.levelOfInput < 2){
            logger.info("Looks like nothing more to compute. Finishing ");
            checkExitCode(ExitCode.EXIT_SUCCESSFULLY);
        }

        logger.info("Ooh, we recive a resource request! Let's check if we can provide it!");
        if (rm.IsResourceAccessible(um.getLastUserID(), input.resource, input.role)) {
            logger.info("Yup. We can provide that one!");
            if(input.levelOfInput > 2){
                logger.info("We can even acoount that!");
                checkExitCode(Accounter.resourceAccessSuccess(input, um.getLastUserID()));}
        } else {
            logger.info("Can't provide that resource");
            if(input.levelOfInput > 2){
                logger.info("At least we can write this down");
                if(Accounter.accessRejected(input, um.getLastUserID())==ExitCode.EXIT_SUCCESSFULLY);{
            checkExitCode(ExitCode.RESOURCE_PERMISSION_DENIED);}
                logger.info("Nope. We can't. Wrong date or something");
                checkExitCode(ExitCode.INCORRECT_ACTIVITY);}
            checkExitCode(ExitCode.RESOURCE_PERMISSION_DENIED);
        }

        DBWorker.disconnect();
        System.exit(0);
    }
}

