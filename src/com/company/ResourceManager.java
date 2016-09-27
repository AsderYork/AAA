package com.company;

import java.util.TreeMap;

/*
Блок управления доступом к ресурсам
 */

public class ResourceManager {
    public TreeMap<String, ResourceData> Resources;

    public ResourceManager() {
        Resources = new TreeMap<String, ResourceData>();
    }

    public void AddResource(String PATH, int Role, int USERID) {
        ResourceData Data = new ResourceData();
        Data.UserID = USERID;
        Data.Access = Role;
        Resources.put(PATH, Data);
    }

    public int GetResource(UserInput Input)//0-OK; 1-FORBIDDEN
    {
        assert ((Input.Role < 1) || (Input.Role > 3));//Проверяем условия контракта

        //Разбиваем путь до ресурса на куски
        String PartPath = new String();
        String delims = "[.]";
        String[] tokens = Input.PATH.split(delims);

        /*Проверяем с самого корня, есть ли у нас доступ к родительским ресурсам
        Так как доступ наследуется. Если находим требуемый доступ у дочернего ресурса, значит и у данного он есть*/

        for (int i = 0; i < tokens.length; i++) {
            PartPath += tokens[i];

            if( IsResourceAccessible(PartPath, Input.Role, Input.USERID) ) {
                Accounter.AccessGranted(Input);
                System.out.println("Access Granted");
                return 0;//Требуемая запись о ресурсе найдена. Предоставляем
            }

        }
        System.out.println("Access Rejected");
        Accounter.AccessRejected(Input);
        return 1;
    }

    private boolean IsResourceAccessible(String PATH, int ROLE, int USERID)
    {
        ResourceData Data;
        Data = Resources.get(PATH);
        if (Data != null) {
            if ((Data.UserID == USERID) && (Data.Access == ROLE))
            {return true;}
        }
        return false;
    }

}

class ResourceData {
    int UserID;
    int Access;//1-R;2-W;3-X;
}
