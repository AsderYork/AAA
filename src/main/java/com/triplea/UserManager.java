package com.triplea;

import com.triplea.dao.AccountMessageAccess;
import com.triplea.dao.UserDataAccess;
import com.triplea.domain.UserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class UserManager {
    private static final Logger LOGGER = LogManager.getLogger("UserManager");
    private AccountMessageAccess aCaaccess;
    private int lastUserID;
    UserDataAccess access;

    public UserManager(UserDataAccess access, AccountMessageAccess aCAccess) {
        this.access = access;
        lastUserID = -1;
        aCaaccess = aCAccess;
    }

    public ExitCode findUser(String userLogin, String inputPassword) throws NoSuchAlgorithmException {


        LOGGER.info("So we've been asked to find a user. Asking db the same thing. Like we have db, huh?");
        UserData data = getUserData(userLogin);

        if (data == null) {

            LOGGER.info("It returned us nothing. So there's no such user");
            System.out.println("Wrong username");
            return ExitCode.WRONG_LOGIN;
        }


        LOGGER.info("It fond a user with that username. Now let's check passwords");
        if (Objects.equals(Hasher.hashPassword(inputPassword, data.salt, "MD5"), data.hashedPassword)) {
            LOGGER.info("If you see this message after 1000 atempts of connect, then brutforce have succeed");
            lastUserID = data.id;
            System.out.println("Welcome " + data.name);
            Accounter.login(data, aCaaccess);
            return ExitCode.DO_NOT_EXIT;
        }

        LOGGER.info("Password mismatch. Can't go with that");
        System.out.println("Wrong password");
        return ExitCode.WRONG_PASSWORD;
    }

    private UserData getUserData(String username) {
        return access.getUserByLogin(username);
    }

    public int getLastUserID() {
        return lastUserID;
    }

}

