package com.triplea;

import com.triplea.dao.AccountMessageAccess;
import com.triplea.domain.AccountMessage;
import com.triplea.domain.UserData;
import com.triplea.domain.UserInput;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Log4j2
public class Accounter {

    public static void login(UserData data, AccountMessageAccess access) {

        log.info("Simple as that: we just creating AccountMessage" +
                " with tmp date and given data");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        AccountMessage msg = new AccountMessage("login",
                dateFormat.format(date),
                data.id,
                "",
                "",
                0,
                LocalDate.now(),
                LocalDate.now()
        );

        access.putMessage(msg);

    }

    public static ExitCode resourceAccessSuccess(UserInput input, int userid, AccountMessageAccess access) {


        log.info("Looks like we succeed at resource providing");
        if (input.valueOfResourse < 0) {

            log.info("But we dont have enough data fo accounting."
                    + " This one remain silent!");
            return ExitCode.INCORRECT_ACTIVITY;
        }

        log.info("So just putting data in");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        AccountMessage msg = new AccountMessage("AccessGranted",
                dateFormat.format(date),
                userid,
                input.role,
                input.resource,
                input.valueOfResourse,
                input.startDateOfResourceRequest,
                input.endDateOfResourceRequest);

        access.putMessage(msg);

        return ExitCode.EXIT_SUCCESSFULLY;
    }

    public static ExitCode accessRejected(UserInput input, int userid, AccountMessageAccess access) {

        log.info("Oh, we failed at resource providing");
        if (input.valueOfResourse < 0) {

            log.info("We can't even do accounting, provided data is not enough."
                    +  " This one remain silent!");
            return ExitCode.INCORRECT_ACTIVITY;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        log.info("At least we can do accounting.");
        AccountMessage msg = new AccountMessage("accessRejected",
                dateFormat.format(date),
                userid,
                input.role,
                input.resource,
                input.valueOfResourse,
                input.startDateOfResourceRequest,
                input.endDateOfResourceRequest);

        access.putMessage(msg);

        return ExitCode.EXIT_SUCCESSFULLY;
    }

}


