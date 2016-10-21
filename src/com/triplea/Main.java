package com.triplea;


public class Main {

    private static void CheckExitCode(ExitCode code) {
        if (code.getStatusCode() == -1) {
            return;
        }

        System.exit(code.getStatusCode());
    }

    public static void main(String[] args) {
        UserInput input = new UserInput();


        UserManager um = new UserManager();
        um.addUser("jdoe", "John Doe", "sup3rpaZZ", "Salt");
        um.addUser("jrow", "Jane Row", "Qweqrty12", "AnotherSalt");


        ResourceManager rm = new ResourceManager();
        rm.AddPermission("a", 1, 0);
        rm.AddPermission("a.b", 2, 0);
        rm.AddPermission("a.b.c", 3, 1);
        rm.AddPermission("a.bc", 3, 0);


        UserInputManager ConsoleManager = new UserInputManager(args);

        CheckExitCode(ConsoleManager.parse(input));
        CheckExitCode(um.FindUser(input.name, input.password));

        if (rm.IsResourceAccessible(um.getLastUserID(), input.resource, input.role)) {
            Accounter.ResourceAccessSuccess(input, um.getLastUserID());
        } else {
            Accounter.AccessRejected(input, um.getLastUserID());
            CheckExitCode(ExitCode.RESOURCE_PERMISSION_DENIED);
        }

        System.exit(0);
    }
}
