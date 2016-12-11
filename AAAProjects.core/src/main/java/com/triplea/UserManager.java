package com.triplea;

import com.triplea.dao.AccountMessageAccess;
import com.triplea.dao.UserDataAccess;
import com.triplea.domain.UserData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
public class UserManager {


    final private AccountMessageAccess aCaaccess;
    @Getter private int lastUserID = -1;
    final UserDataAccess access;


    public ExitCode findUser(String userLogin, String inputPassword) throws NoSuchAlgorithmException {


        log.info("So we've been asked to find a user. Asking db the same thing. Like we have db, huh?");
        UserData data = getUserData(userLogin);

        if (data == null) {

            log.info("It returned us nothing. So there's no such user");
            System.out.println("Wrong username");
            return ExitCode.WRONG_LOGIN;
        }


        log.info("It fond a user with that username. Now let's check passwords");
        if (Objects.equals(Hasher.hashPassword(inputPassword, data.salt, "MD5"), data.hashedPassword)) {
            log.info("If you see this message after 1000 atempts of connect, then brutforce have succeed");
            lastUserID = data.id;
            System.out.println("Welcome " + data.name);
            Accounter.login(data, aCaaccess);
            return ExitCode.DO_NOT_EXIT;
        }

        log.info("Password mismatch. Can't go with that");
        System.out.println("Wrong password");
        return ExitCode.WRONG_PASSWORD;
    }

    private UserData getUserData(String username) {
        return access.getUserByLogin(username);
    }

}

