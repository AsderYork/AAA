package com.company;

public class Main {

    public static void main(String[] args) {
        UserInput Input = new UserInput();

        UserManager UM = new UserManager();

        UM.addUser("jdoe", "John Doe", "sup3rpaZZ", "Salt");
        UM.addUser("jrow", "Jane Row", "Qweqrty12", "AnotherSalt");


        ResourceManager RM = new ResourceManager();
        RM.AddResource("a",1,0);
        RM.AddResource("a.b",2,0);
        RM.AddResource("a.b.c",3,1);
        RM.AddResource("a.bc", 3, 0);

        //Вот тут должен вызываться менеджер
        if (0 != 0) {
            System.exit(0);
        }

        switch (UM.FindUser(Input))
        {
            case 1: {System.exit(1);}
            case 2: {System.exit(2);}
            case 0: {break;}
        }

        switch(RM.GetResource(Input))
        {
            case 1: {System.exit(4);}
            case 2: {System.exit(3);}
            case 0: {break;}
        }
        System.exit(0);
    }
}

class UserInput {
    String UserName;//Имя пользователя
    String Password;//Пароль
    String PATH;//Путь до ресурса
    int Role;//Роль использования ресурса
    int USERID;//Идентификатор. Появляется только после менеджера ввода
    String StartDate;//Дата начала
    String EndDate;//Дата окончания. Могут оставаться пустыми
    int UsageValue;//Значение использования ресурса.
}