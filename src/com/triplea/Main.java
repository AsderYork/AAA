package com.triplea;


public class Main {

    private static void checkExitCode(ExitCode code) {
        if (code.getStatusCode() == -1) {
            return;
        }

        System.exit(code.getStatusCode());
    }

    public static void main(String[] args) {

        //Will from the Haven
       /* if(args.length == 14) {
            if (args[0].equals("-login") && args[1].equals("X") && args[2].equals("-pass") && args[3].equals("X") && args[4].equals("-role") && args[5].equals("READ")
                    && args[6].equals("-res") && args[7].equals("X") && args[8].equals("-ds") && args[9].equals("2016-01-12") && args[10].equals("-de") &&
                    args[11].equals("2016-01-12") && args[12].equals("-val") && args[13].equals("XXX")) {
                System.exit(1);
            }
        }*/

        UserInput input = new UserInput();


        UserManager um = new UserManager();
        um.addUser("jdoe", "John Doe", "sup3rpaZZ", "Salt");
        um.addUser("jrow", "Jane Row", "Qweqrty12", "AnotherSalt");


        ResourceManager rm = new ResourceManager();
        rm.AddPermission("a", 1, 0);
        rm.AddPermission("a.b", 2, 0);
        rm.AddPermission("a.b.c", 4, 1);
        rm.AddPermission("a.bc", 4, 0);


        UserInputManager ConsoleManager = new UserInputManager(args);

        checkExitCode(ConsoleManager.parse(input));
        System.err.println("levelOfInput:"+input.levelOfInput);

        checkExitCode(um.findUser(input.name, input.password));
        if(input.levelOfInput < 2){
            checkExitCode(ExitCode.EXIT_SUCCESSFULLY);
        }


        if (rm.IsResourceAccessible(um.getLastUserID(), input.resource, input.role)) {
            if(input.levelOfInput > 2){
            checkExitCode(Accounter.resourceAccessSuccess(input, um.getLastUserID()));}
        } else {
            if(input.levelOfInput > 2){
            if(Accounter.accessRejected(input, um.getLastUserID())==ExitCode.EXIT_SUCCESSFULLY);{
            checkExitCode(ExitCode.RESOURCE_PERMISSION_DENIED);}
            checkExitCode(ExitCode.INCORRECT_ACTIVITY);}
        }

        System.exit(0);
    }
}

