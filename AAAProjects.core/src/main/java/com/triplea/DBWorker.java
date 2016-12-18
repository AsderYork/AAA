package com.triplea;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Log4j2
public class DBWorker {

    private static Connection conn = null;


    public static void migrate() {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:file:./db-aaa", "user", "password");
        flyway.clean();
        flyway.migrate();
    }

    public static boolean connectToLocalDB() {
        log.info("So we trying to connect to db");
        try {
            Class.forName("org.h2.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:h2:file:./db-aaa", "user", "password");
            return true;
        } catch (Exception e) {
            log.error("It's hard to admit, we failed!", e);
            return false;
        }

    }

    public static boolean disconnect() {
        log.info("Closing connection to db");
        try {
            conn.close();
            return true;
        } catch (SQLException e) {
            log.error("We can't even close it properly!", e);
        }
        return false;
    }

    public static PreparedStatement makePreparedStatement(String str) {

        try {
            return conn.prepareStatement(str);
        } catch (SQLException e) {
            log.error("Failed to create PreparedStatement |" + str + "|", e);
        }
        return null;
    }

}
