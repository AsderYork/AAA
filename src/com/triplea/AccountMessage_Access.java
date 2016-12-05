package com.triplea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
/**
 * Created by Asder on 06.12.2016.
 */
public class AccountMessage_Access {

    private static final Logger logger = LogManager.getLogger("AccountMessage_Access");

    public static void SecureTable()
    {
        if(DBWorker.isTableExists("ACCOUNTED_DATA")==false)
        {
            logger.info("There is no ACCOUNTED_DATA table. Creating!");
            DBWorker.execute("CREATE TABLE ACCOUNTED_DATA(" +
                    "Action  VARCHAR(255)," +
                    "AccountDate VARCHAR(255)," +
                    "UserID int," +
                    "Role VARCHAR(255)," +
                    "Path VARCHAR(255)," +
                    "Value int," +
                    "DateStart  VARCHAR(255)," +
                    "DateFinished  VARCHAR(255))");
        }
        else{
            logger.info("At least we have ACCOUNTED_DATA table");
        }


    }

    public static boolean putMessage(AccountMessage msg)
    {
        SecureTable();
        String stat = "INSERT INTO ACCOUNTED_DATA(Action, AccountDate, UserID,Role,Path,Value,DateStart,DateFinished)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement Statement = DBWorker.MakePreparedStatement(stat);
        try {
            Statement.setString(1,msg.action);
            Statement.setString(2,msg.accountDate);
            Statement.setInt(3,msg.userid);
            Statement.setString(4,msg.role);
            Statement.setString(5,msg.path);
            Statement.setInt(6,msg.value);
            Statement.setString(7,msg.dateStart.toString());
            Statement.setString(8,msg.dateFinished.toString());
            Statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            logger.error("Well, we failed in putting values in prepared statement, or Executing it", e);
            return false;
        }
    }

}
