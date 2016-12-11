package com.triplea;

import com.triplea.domain.UserInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.BitSet;

@Log4j2
@RequiredArgsConstructor
public class UserInputManager {
    final private String[] args;
    private Options options = new Options();


    public ExitCode parse(UserInput userInput) {
        options.addOption("h", "help", false, "show help.");
        options.addOption("login", "login", true, "your login.");
        options.addOption("pass", "password", true, "your password.");
        options.addOption("res", "resource", true, "Requested resource.");
        options.addOption("role", "role", true, "What you what to do with this resource.");
        options.addOption("ds", "datestart", true, "Using start date. [DD-MM-YYYY]");
        options.addOption("de", "dateend", true, "Using end date. [DD-MM-YYYY]");
        options.addOption("val", "value", true, "Value");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        log.info("So we just received user input");
        for (String arg : args) {
            log.info(arg);
        }

        try {
            cmd = parser.parse(options, args);

            if (!validationGroupsOfOption(cmd)) {
                help();
                log.info("But there's a problem with input. Just showing help");
                return ExitCode.EXIT_SUCCESSFULLY;
            }

            if (cmd.hasOption("login") && (cmd.hasOption("pass"))) {

                log.info("We've got pass/log. Btw it's first stage of input");
                userInput.name = cmd.getOptionValue("login");
                userInput.password = cmd.getOptionValue("pass");
                userInput.levelOfInput = 1;
            }
            if (cmd.hasOption("res") && (cmd.hasOption("role"))) {

                log.info("We've received a res/role combo. Looks like a second stage of input!");
                userInput.resource = cmd.getOptionValue("res");
                userInput.role = roleParser(cmd.getOptionValue("role"));
                userInput.levelOfInput = 2;
            }


            if (cmd.hasOption("ds") && cmd.hasOption("de") && (cmd.hasOption("val"))) {

                userInput.valueOfResourse = -1;//Deafult value in case of wrong input.Before date in case of exception

                log.info("Can't belive it! ds/de/val! Third stage of input");
                //Date Parsing
                userInput.levelOfInput = 3;
                userInput.startDateOfResourceRequest = dateParser(cmd.getOptionValue("ds"));
                userInput.endDateOfResourceRequest = dateParser(cmd.getOptionValue("de"));

                //Value parsing. It's kinda special. Not like fonts, of course

                userInput.valueOfResourse = valueIsIntString(cmd.getOptionValue("val"));
                if (userInput.valueOfResourse < 0) {

                    log.info("The joy was premature. Val was negative. Let's just move it to -1 and go on");
                    System.err.println("val cant be <0");
                    //return ExitCode.INCORRECT_ACTIVITY;
                }
            }


        } catch (DateTimeParseException dt) {

            log.error("Date parsing failed. Happens from time to time", dt);
            System.err.println("Wrong date parameter");
            //From now on, we'll do nothing against incorrect input. Like not our business or something
            //return ExitCode.INCORRECT_ACTIVITY;

        } catch (IllegalArgumentException ill) {

            log.error("Illegal argument", ill);
            //return ExitCode.INCORRECT_ACTIVITY;


        } catch (ParseException pe) {

            log.error("Can't even parse it properly. Let's just show help", pe);
            help();
            return ExitCode.EXIT_SUCCESSFULLY;

        } catch (WrongRoleException wrt) {

            log.error("The role wasn't what expected", wrt);
            return ExitCode.WRONG_ROLE;
        }

        log.info("We worked well. Everything is parsed");
        return ExitCode.DO_NOT_EXIT;
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Main", options);
    }

    private LocalDate dateParser(String inputString) throws DateTimeParseException {
        return LocalDate.parse(inputString);
    }

    private String roleParser(String inputString) throws WrongRoleException {
        if (inputString.equals("READ") | inputString.equals("WRITE") | inputString.equals("EXECUTE")) {
            return inputString;
        } else {
            throw new WrongRoleException("Wrong role parameter");
        }
    }

    private int valueIsIntString(String inputString) throws NumberFormatException {
        return Integer.parseInt(inputString);
    }

    private boolean validationGroupsOfOption(CommandLine cmd) {
        BitSet allowInput = new BitSet(8);
        allowInput.set(0);

        if (cmd.hasOption("login")) {
            allowInput.set(1);
        }

        if (cmd.hasOption("pass")) {
            allowInput.set(2);
        }

        if (cmd.hasOption("res")) {
            allowInput.set(3);
        }

        if (cmd.hasOption("role")) {
            allowInput.set(4);
        }

        if (cmd.hasOption("ds")) {
            allowInput.set(5);
        }

        if (cmd.hasOption("de")) {
            allowInput.set(6);

        }

        if (cmd.hasOption("val")) {
            allowInput.set(7);
        }

        switch (Long.toString(allowInput.toLongArray()[0], 2)) {
            case "111":
                return true;
            case "11111":
                return true;
            case "11111111":
                return true;
            default:
                System.err.println("Not enough attributes");
                return false;
        }
    }
}

