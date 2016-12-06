package com.triplea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Asder on 05.12.2016.
 */
public class UserData_Access {

    private static final Logger logger = LogManager.getLogger("UserData_Access");

    public static boolean putUser(UserData userData) {
        //ID will be INGORED. It's in DB's managment
        if (userData.id != null) {
            logger.info("It happened that user already have ID. It shouldn't be like that! And id will be ignored!");
        }

        String Request = "INSERT INTO USERSDATA(Login, Username, HashedPassword, Salt) " +
                "VALUES (?,?,?,?)";

        PreparedStatement Statement = DBWorker.MakePreparedStatement(Request);

        try {
            Statement.setString(1, userData.username);
            Statement.setString(2, userData.name);
            Statement.setString(3, userData.hashedPassword);
            Statement.setString(4, userData.salt);
            Statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            logger.error("Well, we failed in putting values in prepared statement, or Executing it", e);
            return false;
        }
    }

    public static UserData getUserByLogin(String login) {
        String username;
        String name;
        String hashedPassword;
        String salt;
        Integer ID;

        String Request = "SELECT * FROM USERSDATA WHERE Login=?;";

        PreparedStatement Statement = DBWorker.MakePreparedStatement(Request);

        try {
            Statement.setString(1, login);
            ResultSet RS = Statement.executeQuery();
            if (RS.next()) {
                username = RS.getString("Login");
                name = RS.getString("Username");
                hashedPassword = RS.getString("HashedPassword");
                salt = RS.getString("Salt");
                ID = RS.getInt("ID");
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
