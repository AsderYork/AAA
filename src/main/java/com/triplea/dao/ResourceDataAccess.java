package com.triplea.dao;

import com.triplea.DBWorker;
import com.triplea.domain.ResourceData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Asder on 05.12.2016.
 */
public class ResourceDataAccess {

    private static final Logger logger = LogManager.getLogger("ResourceDataAccess");

    public ResourceData getResourceData_ByIDAndPath(Integer ID, String Path) {
        String request = "SELECT * FROM PERMISSION_SDATA WHERE (id=?)AND(subresource=?);";

        PreparedStatement Statement = DBWorker.makePreparedStatement(request);

        ResourceData RetVal = new ResourceData();

        try {
            Statement.setInt(1, ID);
            Statement.setString(2, Path);


            ResultSet RS = Statement.executeQuery();
            if (RS.next()) {
                RetVal.id = RS.getInt("id");
                RetVal.permission = RS.getInt("permission");
                RetVal.subresource = RS.getString("subresource");
                return RetVal;
            } else {
                return null;
            }

        } catch (SQLException e) {
            logger.error("Well, we failed in putting values in prepared statement, or Executing Querry", e);
            return null;
        }
    }

}
