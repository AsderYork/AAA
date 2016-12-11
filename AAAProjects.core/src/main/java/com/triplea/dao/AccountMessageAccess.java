package com.triplea.dao;

import com.triplea.DBWorker;
import com.triplea.domain.AccountMessage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Log4j2
@AllArgsConstructor
public class AccountMessageAccess {


    private DBWorker dbWorker;

    public boolean putMessage(AccountMessage msg) {
        String stat = "INSERT INTO ACCOUNTED_DATA(Action, AccountDate," +
                " UserID,Role,Path,Value,DateStart,DateFinished)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement statement = dbWorker.makePreparedStatement(stat);
        try {
            statement.setString(1, msg.action);
            statement.setString(2, msg.accountDate);
            statement.setInt(3, msg.userid);
            statement.setString(4, msg.role);
            statement.setString(5, msg.path);
            statement.setInt(6, msg.value);
            statement.setString(7, msg.dateStart.toString());
            statement.setString(8, msg.dateFinished.toString());
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            log.error("Well, we failed in putting values in prepared" +
                    " statement, or Executing it", e);
            return false;
        }
    }

}
