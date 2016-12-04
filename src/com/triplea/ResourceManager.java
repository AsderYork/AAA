package com.triplea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TreeMap;

public class ResourceManager {
    public TreeMap<String, ResourceData> resources;

    private static final Logger logger = LogManager.getLogger("ResourceManager");

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
        logger.info("So we've been asked to check accessibility of given user to given resource");
        //Выбрасываемся, если роль нулевая. Ведь если так, то и никакого ресурса нет
        if ((role == null) || (path == null)) {
            logger.info("Roll/Pacth is null. This won't move us too far. Resource is inaccessible");
            return false;
        }

        logger.info("Translate role to int");
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


        logger.info("Parse path and tree-checking it's parts");
        String delims = "[.]";
        String[] tokens = path.split(delims);

        String partPath = "";
        for (String token : tokens) {
            partPath += token;

            if (IsSubresourceAccessible(partPath, intRole, userID)) {

                logger.info("So we have found what we've been looking for. Great!");
                System.out.println("Access Granted");
                return true;
            }
            partPath += ".";
        }

        logger.info("Cant provide that resource to that user.");
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


