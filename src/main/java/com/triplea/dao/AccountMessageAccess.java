package com.triplea.dao;

import com.triplea.DBWorker;
import com.triplea.domain.AccountMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountMessageAccess {

    private static final Logger logger = LogManager.getLogger("AccountMessageAccess");
    private DBWorker DBW;

    public AccountMessageAccess()
    {
        DBW = new DBWorker();
    }

    public AccountMessageAccess(DBWorker dBW)
    {
        DBW = dBW;
    }

    public boolean putMessage(AccountMessage msg) {
        String stat = "INSERT INTO ACCOUNTED_DATA(Action, AccountDate, UserID,Role,Path,Value,DateStart,DateFinished)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement Statement = DBW.makePreparedStatement(stat);
        try {
            Statement.setString(1, msg.action);
            Statement.setString(2, msg.accountDate);
            Statement.setInt(3, msg.userid);
            Statement.setString(4, msg.role);
            Statement.setString(5, msg.path);
            Statement.setInt(6, msg.value);
            Statement.setString(7, msg.dateStart.toString());
            Statement.setString(8, msg.dateFinished.toString());
            Statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            logger.error("Well, we failed in putting values in prepared statement, or Executing it", e);
            return false;
        }
    }

}
