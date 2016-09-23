package com.company;

import java.util.TreeMap;


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

    public int GetResource(UserInput Input)//0-OK; 1-FORBIDDEN 2-WRONG_ROLE
    {
        if ((Input.Role < 1) || (Input.Role > 3)) {
            return 2;
        }


        String PartPath = new String();
        String delims = "[,]";
        String[] tokens = Input.PATH.split(delims);
        ResourceData Data;


        for (int i = 0; i < tokens.length; i++) {
            PartPath += tokens[i];
            Data = Resources.get(PartPath);
            if (Data != null) {
                if ((Data.UserID == Input.USERID) && (Data.Access == Input.Role)) {
                    Accounter.AccessGranted(Input);
                    System.out.println("Access Granted");
                    return 0;//Требуемая запись о ресурсе найдена. Предоставляем
                }
            }

        }
        System.out.println("Access Rejected");
        Accounter.AccessRejected(Input);
        return 1;
    }
}
