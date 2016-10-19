package com.triplea;


public class Main {

    private static void CheckExitCode(EXIT_CODES Code) {//Метод поддержки, обеспечивающий обработку возвращаемых значений главных методов блоков. Боже мой, какая дикая терминология!
        if (Code.getStatusCode() == -1) {
            return;
        }

        System.exit(Code.getStatusCode());
    }

    public static void main(String[] args) {
        UserInput Input = new UserInput();//Результат пользоватльского ввода будет записан сюда


        UserManager UM = new UserManager();
        UM.addUser("jdoe", "John Doe", "sup3rpaZZ", "Salt");
        UM.addUser("jrow", "Jane Row", "Qweqrty12", "AnotherSalt");


        ResourceManager RM = new ResourceManager();
        RM.AddPermission("a", 1, 0);
        RM.AddPermission("a.b", 2, 0);
        RM.AddPermission("a.b.c", 3, 1);
        RM.AddPermission("a.bc", 3, 0);


        UserInputManager ConsoleManager = new UserInputManager(args);

        //Важно помнить, что метод CheckExitCode на самом деле может завершать программу.
        CheckExitCode(ConsoleManager.parse(Input));
        CheckExitCode(UM.FindUser(Input.name, Input.password));

        if (RM.IsResourceAccessible(UM.getLastUserID(), Input.resource, Input.role)) {
            Accounter.ResourceAccessSuccess(Input, UM.getLastUserID());
        } else {
            Accounter.AccessRejected(Input, UM.getLastUserID());
            CheckExitCode(EXIT_CODES.RESOURCE_PERMISSION_DENIED);
        }

        System.exit(0);//Если все прошло хорошо, заканчиваем так.
    }
}

