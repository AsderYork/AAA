package com.triplea;

import com.triplea.dao.AccountMessage_Access;
import com.triplea.domain.AccountMessage;
import com.triplea.domain.UserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.TreeMap;


public class Accounter {

    private static final Logger logger = LogManager.getLogger("Accounter");



    public static void login(UserData Data) {

        logger.info("Simple as that: we just creating AccountMessage with tmp date and given data");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        AccountMessage MSG = new AccountMessage("login",
                dateFormat.format(date),
                Data.id,
                "",
                "",
                0,
                LocalDate.now(),
                LocalDate.now()
        );

        AccountMessage_Access.putMessage(MSG);

    }

    public static ExitCode resourceAccessSuccess(UserInput input, int userid) {


        logger.info("Looks like we succeed at resource providing");
        if (input.valueOfResourse < 0) {

            logger.info("But we dont have enough data fo accounting. This one remain silent!");
            return ExitCode.INCORRECT_ACTIVITY;
        }

        logger.info("So just putting data in");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        AccountMessage MSG = new AccountMessage("AccessGranted",
                dateFormat.format(date),
                userid,
                input.role,
                input.resource,
                input.valueOfResourse,
                input.startDateOfResourceRequest,
                input.endDateOfResourceRequest);

        AccountMessage_Access.putMessage(MSG);

        return ExitCode.EXIT_SUCCESSFULLY;
    }

    public static ExitCode accessRejected(UserInput input, int userid) {

        logger.info("Oh, we failed at resource providing");
        if (input.valueOfResourse < 0) {

            logger.info("We can't even do accounting, provided data is not enough. This one remain silent!");
            return ExitCode.INCORRECT_ACTIVITY;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        logger.info("At least we can do accounting.");
        AccountMessage MSG = new AccountMessage("accessRejected",
                dateFormat.format(date),
                userid,
                input.role,
                input.resource,
                input.valueOfResourse,
                input.startDateOfResourceRequest,
                input.endDateOfResourceRequest);

        AccountMessage_Access.putMessage(MSG);

        return ExitCode.EXIT_SUCCESSFULLY;
    }

}


