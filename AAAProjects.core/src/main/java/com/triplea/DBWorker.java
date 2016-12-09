package com.triplea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import java.sql.*;


public class DBWorker {

    private static final Logger LOGGER = LogManager.getLogger("DBWorker");
    private static Connection conn = null;


    public static void migrate() {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:file:./db-aaa", "user", "password");
        flyway.clean();
        flyway.migrate();
    }

    public static boolean connectToLocalDB() {
        LOGGER.info("So we trying to connect to db");
        try {
            Class.forName("org.h2.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:h2:file:./db-aaa", "user", "password");
            return true;
        } catch (Exception e) {
            LOGGER.error("It's hard to admit, we failed!", e);
            return false;
        }

    }

    public static boolean disconnect() {
        LOGGER.info("Closing connection to db");
        try {
            conn.close();
            return true;
        } catch (SQLException e) {
            LOGGER.error("We can't even close it properly!", e);
        }
        return false;
    }

    public static PreparedStatement makePreparedStatement(String str) {

        try {
            return conn.prepareStatement(str);
        } catch (SQLException e) {
            LOGGER.error("Failed to create PreparedStatement |" + str + "|", e);
        }
        return null;
    }

}
