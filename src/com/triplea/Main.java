package com.triplea;


public class Main {

    private static void CheckExitCode(EXIT_CODES Code) {//Метод поддержки, обеспечивающий обработку возвращаемых значений главных методов блоков. Боже мой, какая дикая терминология!
        switch (Code) {
            case DO_NOT_EXIT: {
                return;
            }
            case EXIT_SUCCESSFULLY: {
                System.exit(0);
                break;
            }
            case WRONG_LOGIN: {
                System.exit(1);
                break;
            }
            case WRONG_PASSWORD: {
                System.exit(2);
                break;
            }
            case WRONG_ROLE: {
                System.exit(3);
                break;
            }
            case RESOURCE_PERMISSION_DENIED: {
                System.exit(4);
                break;
            }
            case INCORRECT_ACTIVITY: {
                System.exit(5);
                break;
            }

            default: {
                System.out.println("One of the modules have returned " + Code.name() + ". It's unexpected.");
                break;
            }
        }

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

        //Костыль, ага
        UserID  USERID = new UserID(0);//Принимаем из менеджера пользователей идентификатор
        CheckExitCode(UM.FindUser(Input.name, Input.password, USERID));

        if (RM.IsResourceAccessible(USERID.getUserID(), Input.resource, Input.role)) {
            Accounter.ResourceAccessSuccess(Input, USERID.getUserID());
        } else {
            Accounter.AccessRejected(Input, USERID.getUserID());
            CheckExitCode(EXIT_CODES.RESOURCE_PERMISSION_DENIED);
        }

        System.exit(0);//Если все прошло хорошо, заканчиваем так.
    }
}

