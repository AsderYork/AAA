package com.triplea;

import java.util.TreeMap;

/*
Блок управления доступом к ресурсам
 */

public class ResourceManager {
    public TreeMap<String, ResourceData> Resources;

    public ResourceManager() {
        Resources = new TreeMap<>();
    }

    public void AddPermission(String PATH, int Role, int USERID) {
        ResourceData Data = new ResourceData();
        Data.AddUserPermission(USERID, Role);
        Resources.put(PATH, Data);
    }

    public boolean IsResourceAccessible(int UserID, String PATH, String Role) {
        int IntROLE;//Преводим роль к интовому типу
        switch (Role) {
            case "READ": {
                IntROLE = 1;
                break;
            }
            case "WRITE": {
                IntROLE = 2;
                break;
            }
            case "EXECUTE": {
                IntROLE = 4;
                break;
            }
            default: {
                System.out.println("IsResourceAccasiable did not expect " + Role + " as a role");
                return false;
            }
        }

        //Разбиваем путь до ресурса на куски
        String delims = "[.]";
        String[] tokens = PATH.split(delims);

        /*Проверяем с самого корня, есть ли у нас доступ к родительским ресурсам
        Так как доступ наследуется. Если находим требуемый доступ у дочернего ресурса, значит и у данного он есть*/
        String PartPath = "";
        for (String token : tokens) {
            PartPath += token;

            if (IsSubresourceAccessible(PartPath, IntROLE, UserID)) {
                System.out.println("Access Granted");
                return true;
            }
            PartPath += ".";//Так как у нас только токены, мы должны добавлять между ними точку, что бы это было похоже на путь

        }

        System.out.println("Access Rejected");
        return false;
    }

    private boolean IsSubresourceAccessible(String PATH, int ROLE, int USERID) {
        //Метод поддержки. Проверяет, доступна ли часть пути ресурса.
        ResourceData Data;
        Data = Resources.get(PATH);
        if (Data != null) {
            if (Data.IsPermissionExist(USERID, ROLE)) {
                return true;
            }
        }
        return false;
    }

}

class ResourceData {
    private TreeMap<Integer, Integer> UserID;//Ключ - ид пользователя. Значение - тип доступа

    public ResourceData() {//Инициализируем контейнер
        UserID = new TreeMap<>();
    }

    public void AddUserPermission(int ID, int Role) {//Еще раз напомню, 1-READ;2-WRITE;4-EXECUTE;
        Integer Data = UserID.get(ID);
        if (Data == null)//Если записи о пользователе еще нет
        {
            UserID.put(ID, Role);
        } else {
            if ((Data & Role) == 0) {//Если такой роли еще нет, то добавляем её
                UserID.put(ID, Data + Role);
            }//Иначе не делаем ничего
        }
    }

    public boolean IsPermissionExist(int ID, int Role) {
        Integer Data = UserID.get(ID);
        if (Data != null)//Проверяем, есть ли запись у этого пользователея.
        {
            if ((Data & Role) == 1) {//Если такая роль есть, то сообщаем об этом
                return true;
            }
        }
        return false;
    }


}
