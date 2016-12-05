package com.triplea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * Created by Asder on 03.12.2016.
 */
public class DBWorker {

    private static Connection conn = null;

    private static final Logger logger = LogManager.getLogger("DBWorker");
    public static boolean connectToLocalDB()
    {
        logger.info("So we trying to connect to db");
        try{
            Class.forName("org.h2.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:h2:file:C:/data/db", "sa", "");
            return true;
        }
        catch(Exception e)   {
            logger.error("It's hard to admit, we failed!", e);
            return false;
        }

    }

    public static boolean disconnect(){
        logger.info("Closing connection to db");
        try {
            conn.close();
            return true;
        }
        catch (SQLException e) {
            logger.error("We can't even close it properly!", e);
        }
        return false;
    }

    public static Connection provideConnection(){return conn;}


    public static boolean execute(String Str){

        try {
            Statement statement = conn.createStatement();
            statement.execute(Str);
            return true;
        } catch (SQLException e) {
            logger.error("Trying to execute SQL-statement we did not succeed", e);
        }
        return false;
    }

    public static boolean isTableExists(String tableName){
        DatabaseMetaData dbm = null;
        try {
            dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableName, null);
            if (tables.next()) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            logger.error("Something happened while we were checking if table is exist", e);
        }
        return false;
    }

    public static ResultSet ExecuteRequest(String Str){
        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(Str);
        } catch (SQLException e) {
            logger.error("Trying to execute SQL-query we did not succeed", e);
        }
        return null;
    }

    public static PreparedStatement MakePreparedStatement(String Str){

        try {
            return conn.prepareStatement(Str);
        } catch (SQLException e) {
            logger.error("Failed to create PreparedStatement |"+Str+"|", e);
        }
        return null;
    }

}
