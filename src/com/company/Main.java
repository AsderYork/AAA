package com.company;

public class Main {

    public static int main(String[] args) {
        UserInput Input = new UserInput();

        UserManager UM = new UserManager();

        UM.addUser("jdoe", "John Doe", "sup3rpaZZ", "Salt");
        UM.addUser("jrow", "Jane Row", "Qweqrty12", "AnotherSalt");

        ResourceManager RM = new ResourceManager();
        RM.AddResource("a",1,0);
        RM.AddResource("a.b",2,0);
        RM.AddResource("a.b.c",3,1);
        RM.AddResource("a.bc",3,0);

        //Вот тут должен вызываться менеджер
        if (0 != 0) {
            return 0;
        }

        switch (UM.FindUser(Input))
        {
            case 1: {return 1;}
            case 2: {return 2;}
            case 0: {break;}
        }

        switch(RM.GetResource(Input))
        {
            case 1: {return 4;}
            case 2: {return 3;}
            case 0: {break;}
        }
        return 0;
    }
}
