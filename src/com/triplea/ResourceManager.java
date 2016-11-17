package com.triplea;

import java.util.TreeMap;

public class ResourceManager {
    public TreeMap<String, ResourceData> resources;

    public ResourceManager() {
        resources = new TreeMap<>();
    }

    public void AddPermission(String PATH, int Role, int USERID) {
        ResourceData Data = new ResourceData();
        Data.addUserPermission(USERID, Role);
        resources.put(PATH, Data);
    }

    public boolean IsResourceAccessible(int userID, String path, String role) {
        int intRole;
        //Выбрасываемся, если роль нулевая. Ведь если так, то и никакого ресурса нет
        if ((role == null) || (path == null)) {
            return false;
        }

        switch (role) {
            case "READ": {
                intRole = 1;
                break;
            }
            case "WRITE": {
                intRole = 2;
                break;
            }
            case "EXECUTE": {
                intRole = 4;
                break;
            }
            default: {
                System.out.println("IsResourceAccasiable did not expect " + role + " as a role");
                return false;
            }
        }

        String delims = "[.]";
        String[] tokens = path.split(delims);

        String partPath = "";
        for (String token : tokens) {
            partPath += token;

            if (IsSubresourceAccessible(partPath, intRole, userID)) {
                System.out.println("Access Granted");
                return true;
            }
            partPath += ".";
        }

        System.out.println("Access Rejected");
        return false;
    }

    private boolean IsSubresourceAccessible(String path, int role, int userid) {
        ResourceData data;
        data = resources.get(path);
        if (data != null) {
            if (data.isPermissionExist(userid, role)) {
                return true;
            }
        }
        return false;
    }

}


