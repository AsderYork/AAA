package com.triplea;

import com.triplea.dao.ResourceDataAccess;
import com.triplea.domain.ResourceData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceManager {

    private static final Logger LOGGER = LogManager.getLogger("ResourceManager");


    private ResourceDataAccess access;

    public ResourceManager(ResourceDataAccess access)    {
        this.access = access;
    }

    private boolean checkFlag(int data, int flagOfInterest) {
        //If Data were incorrect, return false
        if ((data <= 0) || (data > 7)) {
            return false;
        }
        //If unexisted flag were requested, return false
        if (!((flagOfInterest == 1) || (flagOfInterest == 2) || (flagOfInterest == 4))) {
            return false;
        }

        if (flagOfInterest == 1) {
            if ((data == 1) || (data == 3) || (data == 5) || (data == 7)) {
                return true;
            }
            ;
        }
        if (flagOfInterest == 2) {
            if ((data == 2) || (data == 3) || (data == 6) || (data == 7)) {
                return true;
            }
            ;
        }
        if (flagOfInterest == 4) {
            if ((data == 4) || (data == 5) || (data == 6) || (data == 7)) {
                return true;
            }
            ;
        }
        return false;
    }

    public boolean isResourceAccessible(int userID, String path, String role) {
        int intRole = 0;
        LOGGER.info("So we've been asked to check accessibility of given user to given resource");
        if ((role == null) || (path == null)) {
            LOGGER.info("Roll/Pacth is null. This won't move us too far. Resource is inaccessible");
            return false;
        }

        LOGGER.info("Translate role to int");
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


        LOGGER.info("Parse path and tree-checking it's parts");
        String delims = "[.]";
        String[] tokens = path.split(delims);

        String partPath = "";
        for (String token : tokens) {
            partPath += token;

            if (isSubresourceAccessible(partPath, intRole, userID)) {

                LOGGER.info("So we have found what we've been looking for. Great!");
                System.out.println("access Granted");
                return true;
            }
            partPath += ".";
        }

        LOGGER.info("Cant provide that resource to that user.");
        System.out.println("access Rejected");
        return false;
    }

    private boolean isSubresourceAccessible(String path, int role, int userid) {


        ResourceData dataInDB = access.getResourceDataByIDAndPath(userid, path);
        if (dataInDB == null) {
            return false;
        }

        return checkFlag(dataInDB.permission, role);
    }

}


