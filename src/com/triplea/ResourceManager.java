package com.triplea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResourceManager {

    private static final Logger logger = LogManager.getLogger("ResourceManager");


    private boolean checkFlag(int Data, int FlagOfInterest){
        //If Data were incorrect, return false
        if((Data <= 0)||(Data>7)){return false;}
        //If unexisted flag were requested, return false
        if(!((FlagOfInterest==1)||(FlagOfInterest==2)||(FlagOfInterest==4))){return false;}

        if(FlagOfInterest==1)  {
            if((Data==1)||(Data==3)||(Data==5)||(Data==7)){return true;};
        }
        if(FlagOfInterest==2)  {
            if((Data==2)||(Data==3)||(Data==6)||(Data==7)){return true;};
        }
        if(FlagOfInterest==4)  {
            if((Data==4)||(Data==5)||(Data==6)||(Data==7)){return true;};
        }
        return false;
    }

    public void AddPermission(String PATH, int Role, int USERID) {
        logger.info("Adding new Permission");

        boolean TableExists = DBWorker.isTableExists("PERMISSIONSDATA");
        if(!TableExists){
            logger.info("There is no PERMISSIONSDATA table. Creating!");
            DBWorker.execute("CREATE TABLE PERMISSIONSDATA(" +
                    "ID int," +
                    "Subresource VARCHAR(255)," +
                    "Permission int)");        }
        else {
            logger.info("At least we have PERMISSIONSDATA table");
        }


        ResourceData dataInDB = ResourceData_Access.getResourceData_ByIDAndPath(USERID, PATH);
        if(dataInDB == null)      {
            logger.info("This permission will be first of his kind!");
            ResourceData_Access.putResourceData(new ResourceData(USERID, PATH, Role));
        }
        else{
            logger.info("But this permission is already exist. Probably we need to augment it?");
            if(!checkFlag(dataInDB.Permission, Role))   {
                logger.info("Yup. It needs augmentation");
                dataInDB.Permission+=Role;
                ResourceData_Access.updateResourceDataPermission(dataInDB);
            }
            else{
                logger.info("Nah, its fine! No augmentation needed");
            }
        }



        //Check if permission already exists and we just need to update it
       /* ResultSet RS = DBWorker.ExecuteRequest("SELECT * FROM PERMISSIONSDATA WHERE (ID="+USERID+")AND(Subresource='" +
                PATH+"');");
        try {
            if(RS.next()) {
                int ValueFromDB =  RS.getInt("Permission");
                logger.info("But this permission is already exist. Probably we need to augment it?");
                if(!checkFlag(ValueFromDB, Role))
                {
                    logger.info("Yup. It needs augmentation");
                    ValueFromDB+=Role;
                    DBWorker.execute("UPDATE PERMISSIONSDATA SET Permission = "+ValueFromDB+" WHERE" +
                            "(ID="+USERID+")AND(Subresource='"+PATH+"');");
                }
                else{
                    logger.info("Nah, its fine! No augmentation needed");
                }

            }
            else  {
                logger.info("This permission will be first of his kind!");
                DBWorker.execute("INSERT INTO PERMISSIONSDATA(ID, Subresource, Permission) " +
                        "VALUES("+USERID+", '"+PATH+"', "+ Role+");");

            }
        } catch (SQLException e) {
            logger.error("While we were checking if there is already permission for that user:path, we got smthin ", e);
        }*/

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


        ResourceData dataInDB = ResourceData_Access.getResourceData_ByIDAndPath(userid, path);
        if(dataInDB == null){
            return false;}

        return checkFlag(dataInDB.Permission, role);

       /* ResultSet RS = DBWorker.ExecuteRequest("SELECT * FROM PERMISSIONSDATA WHERE (ID="+userid+")AND(Subresource='" +
                path+"');");
        int DBPermission = 0;
        try {

            if(RS.next())
            {
                DBPermission =  RS.getInt("Permission");
                return checkFlag(DBPermission, role);
            }
            else {
                return false;
            }



        } catch (SQLException e) {
            logger.error("While we were checking permission for IsResourceAccessible, we got smthin ", e);

        }
        return false;*/

    }

}


