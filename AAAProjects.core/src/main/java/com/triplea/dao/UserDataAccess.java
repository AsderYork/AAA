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

    private static final Logger LOGGER = LogManager.getLogger("UserDataAccess");

    public UserData getUserByLogin(String login) {
        String username;
        String name;
        String hashedPassword;
        String salt;
        Integer id;

        String request = "SELECT * FROM USERS_DATA WHERE Login=?;";

        PreparedStatement statement = DBWorker.makePreparedStatement(request);

        try {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                username = rs.getString("Login");
                name = rs.getString("Username");
                hashedPassword = rs.getString("HashedPassword");
                salt = rs.getString("Salt");
                id = rs.getInt("id");
                return new UserData(username, name, hashedPassword, salt, id);
            } else {
                return null;
            }

        } catch (SQLException e) {
            LOGGER.error("Well, we failed in putting values in prepared " +
                    "statement, or Executing Querry", e);
            return null;
        }
    }
}
