package com.triplea.dao;

import com.triplea.DBWorker;
import com.triplea.domain.UserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Asder on 05.12.2016.
 */
public class UserDataAccess {

    private static final Logger logger = LogManager.getLogger("UserDataAccess");

    public UserData getUserByLogin(String login) {
        String username;
        String name;
        String hashedPassword;
        String salt;
        Integer ID;

        String Request = "SELECT * FROM USERS_DATA WHERE Login=?;";

        PreparedStatement Statement = DBWorker.makePreparedStatement(Request);

        try {
            Statement.setString(1, login);
            ResultSet RS = Statement.executeQuery();
            if (RS.next()) {
                username = RS.getString("Login");
                name = RS.getString("Username");
                hashedPassword = RS.getString("HashedPassword");
                salt = RS.getString("Salt");
                ID = RS.getInt("id");
                return new UserData(username, name, hashedPassword, salt, ID);
            } else {
                return null;
            }

        } catch (SQLException e) {
            logger.error("Well, we failed in putting values in prepared statement, or Executing Querry", e);
            return null;
        }
    }
}
