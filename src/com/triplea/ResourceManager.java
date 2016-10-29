package com.triplea;

import java.util.TreeMap;

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
        int IntROLE;
        //Выбрасываемся, если роль нулевая. Ведь если так, то и никакого ресурса нет
        if ((Role == null) || (PATH == null)) {
            return false;
        }

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

        String delims = "[.]";
        String[] tokens = PATH.split(delims);

        String PartPath = "";
        for (String token : tokens) {
            PartPath += token;

            if (IsSubresourceAccessible(PartPath, IntROLE, UserID)) {
                System.out.println("Access Granted");
                return true;
            }
            PartPath += ".";//Так как у нас только токены, мы должны добавлять между ними точку, что бы это было похоже
            // на путь

        }

        System.out.println("Access Rejected");
        return false;
    }

    private boolean IsSubresourceAccessible(String path, int role, int userid) {
        ResourceData Data;
        Data = Resources.get(path);
        if (Data != null) {
            if (Data.IsPermissionExist(userid, role)) {
                return true;
            }
        }
        return false;
    }

}


