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

    private static final Logger LOGGER = LogManager.getLogger("ResourceDataAccess");

    public ResourceData getResourceDataByIDAndPath(Integer id, String path) {
        String request = "SELECT * FROM PERMISSION_SDATA WHERE (id=?)AND(subresource=?);";

        PreparedStatement statement = DBWorker.makePreparedStatement(request);

        ResourceData retVal = new ResourceData();

        try {
            statement.setInt(1, id);
            statement.setString(2, path);


            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                retVal.id = rs.getInt("id");
                retVal.permission = rs.getInt("permission");
                retVal.subresource = rs.getString("subresource");
                return retVal;
            } else {
                return null;
            }

        } catch (SQLException e) {
            LOGGER.error("Well, we failed in putting values in prepared statement, or Executing Querry", e);
            return null;
        }
    }

}
