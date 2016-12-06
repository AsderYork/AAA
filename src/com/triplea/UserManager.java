package com.triplea;

import com.triplea.dao.UserData_Access;
import com.triplea.domain.UserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class UserManager {
    private static final Logger logger = LogManager.getLogger("UserManager");
    private int lastUserID;

    public UserManager() {
        lastUserID = -1;
    }

    public ExitCode findUser(String userLogin, String inputPassword) {


        logger.info("So we've been asked to find a user. Asking db the same thing. Like we have db, huh?");
        UserData data = getUserData(userLogin);

        if (data == null) {

            logger.info("It returned us nothing. So there's no such user");
            System.out.println("Wrong username");
            return ExitCode.WRONG_LOGIN;
        }


        logger.info("It fond a user with that username. Now let's check passwords");
        if (Objects.equals(Hasher.HashPassword(inputPassword, data.salt), data.hashedPassword)) {
            logger.info("If you see this message after 1000 atempts of connect, then brutforce have succeed");
            lastUserID = data.id;
            System.out.println("Welcome " + data.name);
            Accounter.login(data);
            return ExitCode.DO_NOT_EXIT;
        }

        logger.info("Password mismatch. Can't go with that");
        System.out.println("Wrong password");
        return ExitCode.WRONG_PASSWORD;
    }

    public void addUser(String userLogin, String userName, String password, String salt) {


        boolean ResultOfExecution;
        UserData userData = new UserData(userLogin, userName, Hasher.HashPassword(password, salt), salt);
        ResultOfExecution = UserData_Access.putUser(userData);


        if (ResultOfExecution == false) {
            logger.info("Failed to add new record. Probably it's allready exist, but who knows?");
        }


    }


    private UserData getUserData(String Username) {
        return UserData_Access.getUserByLogin(Username);
    }

    public int getLastUserID() {
        return lastUserID;
    }

}

