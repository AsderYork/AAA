package com.triplea;

import org.apache.commons.cli.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.BitSet;


public class UserInputManager {
    private String[] args = null;
    private Options options = new Options();

    UserInputManager(String[] args) {
        this.args = args;
        options.addOption("h", "help", false, "show help.");
        options.addOption("login", "login", true, "your login.");
        options.addOption("pass", "password", true, "your password.");
        options.addOption("res", "resource", true, "Requested resource.");
        options.addOption("role", "role", true, "What you what to do with this resource.");
        options.addOption("ds", "datestart", true, "Using start date. [DD-MM-YYYY]");
        options.addOption("de", "dateend", true, "Using end date. [DD-MM-YYYY]");
        options.addOption("val", "value", true, "Value");
    }

    public ExitCode parse(UserInput userInput) {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            if(!ValidationGroupsOfOmotion(cmd)){
                help();
                return ExitCode.EXIT_SUCCESSFULLY;
            }

            if (cmd.hasOption("h")) {
                help();
                return ExitCode.EXIT_SUCCESSFULLY;
            }

            if (cmd.hasOption("login")) {
                userInput.name = cmd.getOptionValue("login");
            }

            if (cmd.hasOption("pass")) {
                userInput.password = cmd.getOptionValue("pass");
            }

            if (cmd.hasOption("res")) {
                userInput.resource = cmd.getOptionValue("res");
            }

            if (cmd.hasOption("role")) {
                userInput.role = RoleParser(cmd.getOptionValue("role"));
            }

            if (cmd.hasOption("ds")) {
                userInput.startDateOfResourceRequest = DateParser(cmd.getOptionValue("ds"));
            }

            if (cmd.hasOption("de")) {
                userInput.endDateOfResourceRequest = DateParser(cmd.getOptionValue("de"));
            }

            if (cmd.hasOption("val")) {
                userInput.valueOfResourse = ValueIsIntString(cmd.getOptionValue("val"));
                if (userInput.valueOfResourse < 0) {
                    System.err.println("val cant be <0");
                    return ExitCode.INCORRECT_ACTIVITY;
                }
            }

        } catch (DateTimeParseException dt) {

            System.err.println("Wrong date parameter");
            return ExitCode.INCORRECT_ACTIVITY;

        } catch (IllegalArgumentException ill) {

            return ExitCode.INCORRECT_ACTIVITY;


        } catch (ParseException pe) {
            System.err.println("Failed to parse command line properties");
            help();
            return ExitCode.EXIT_SUCCESSFULLY;

        } catch (WrongRoleException wrt) {

            System.err.println("Wrong role");
            return ExitCode.WRONG_ROLE;

        }

        return ExitCode.DO_NOT_EXIT;
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Main", options);
    }

    private LocalDate DateParser(String inputString) throws DateTimeParseException {
        return LocalDate.parse(inputString);
    }

    private String RoleParser(String inputString) throws WrongRoleException {
        if (inputString.equals("READ") | inputString.equals("WRITE") | inputString.equals("EXECUTE")) {
            return inputString;
        } else {
            throw new WrongRoleException("Wrong role parameter");
        }
    }

    private int ValueIsIntString(String inputString) throws NumberFormatException {
        return Integer.parseInt(inputString);
    }

    private  boolean ValidationGroupsOfOmotion(CommandLine cmd) {
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

