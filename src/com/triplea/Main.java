package com.triplea;


public class Main {

    private static void checkExitCode(ExitCode code) {
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

        checkExitCode(ConsoleManager.parse(input));
        checkExitCode(um.FindUser(input.name, input.password));

        if(input.role == null)
        {/*Наша система устроена так, что в случае, если данные пользователя верны, но он не запросил
        никакой ресурс, то предпологается, что он на самом деле таки хотел ресурс. Просто пустой и без роли
        А потому он получает RESOURCE_PERMISSION_DENIED. Однако в тестах указано, что необходимо проверять,
        в таком случае нужно возвращать 0. Этот костыль тут именно за этим.*/
            CheckExitCode(ExitCode.EXIT_SUCCESSFULLY);
        }

        if (rm.IsResourceAccessible(um.getLastUserID(), input.resource, input.role)) {
            Accounter.ResourceAccessSuccess(input, um.getLastUserID());
        } else {
            Accounter.AccessRejected(input, um.getLastUserID());
            checkExitCode(ExitCode.RESOURCE_PERMISSION_DENIED);
        }

        System.exit(0);
    }
}

