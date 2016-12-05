package com.triplea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Asder on 05.12.2016.
 */
public class ResourceData_Access {

    private static final Logger logger = LogManager.getLogger("ResourceData_Access");

    public static boolean putResourceData(ResourceData data)
    {
        String statement = "INSERT INTO PERMISSIONSDATA(ID, Subresource, Permission)" +
                " VALUES(?, ?, ?);";

        PreparedStatement Statement = DBWorker.MakePreparedStatement(statement);
        try {
            Statement.setInt(1,data.ID);
            Statement.setString(2,data.Subresource);
            Statement.setInt(3,data.Permission);
            Statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            logger.error("Well, we failed in putting values in prepared statement, or Executing it", e);
            return false;
        }
    }

    public static boolean updateResourceDataPermission(ResourceData data)
    {
        String statement = ("UPDATE PERMISSIONSDATA SET Permission = ?" +
                " WHERE(ID=?)AND(Subresource=?);");

        PreparedStatement Statement = DBWorker.MakePreparedStatement(statement);
        try {
            Statement.setInt(1,data.Permission);
            Statement.setInt(2,data.ID);
            Statement.setString(3,data.Subresource);
            Statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            logger.error("Well, we failed in putting values in prepared statement, or Executing it", e);
            return false;
        }
    }


    public static ResourceData getResourceData_ByIDAndPath(Integer ID, String Path)
    {
       String request = "SELECT * FROM PERMISSIONSDATA WHERE (ID=?)AND(Subresource=?);";

        PreparedStatement Statement = DBWorker.MakePreparedStatement(request);

        ResourceData RetVal = new ResourceData();

        try {
            Statement.setInt(1,ID);
            Statement.setString(2,Path);


            ResultSet RS =  Statement.executeQuery();
            if (RS.next()) {
                RetVal.ID =  RS.getInt("ID");
                RetVal.Permission =  RS.getInt("Permission");
                RetVal.Subresource =  RS.getString("Subresource");
                return RetVal;
            }
            else {
                return null;
            }

        } catch (SQLException e) {
            logger.error("Well, we failed in putting values in prepared statement, or Executing Querry", e);
            return null;
        }
    }

}
