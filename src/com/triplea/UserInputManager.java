package com.triplea;

import org.apache.commons.cli.*;

import java.util.BitSet;


public class UserInputManager {
    private String[] args = null;
    private Options options = new Options();
    private BitSet allowInput = new BitSet();

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

    public int parse(UserInput ui) {
        CommandLineParser parser = new
                BasicParser();
        CommandLine cmd;
        final int CORRECT_WORK =     0;
        final int CORRECT_EXIT =     1;
        final int INVALID_ACTIVITY = 2;
        final int WRONG_ROLE =       3;
        // WRONG_DATE


        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                help();
                return CORRECT_EXIT;
            }

            if (cmd.hasOption("login")) {
                allowInput.set(0);
                ui.UserName = cmd.getOptionValue("login");
            }

            if (cmd.hasOption("pass")) {
                allowInput.set(1);
                ui.Password = cmd.getOptionValue("pass");
            }

            if (cmd.hasOption("res")) {
                allowInput.set(2);
                ui.PATH = cmd.getOptionValue("res");
            }

            if (cmd.hasOption("role")) {
                allowInput.set(3);
                switch (cmd.getOptionValue("role")) {
                    case "READ":
                        ui.Role = 1;
                        break;
                    case "WRITE":
                        ui.Role = 2;
                        break;
                    case "EXECUTE":
                        ui.Role = 3;
                    default:
                        System.err.println("Wrong role format");
                        return WRONG_ROLE;
                }

            }

            if (cmd.hasOption("ds")) {
                allowInput.set(4);
                ui.StartDate = cmd.getOptionValue("ds");
            }

            if (cmd.hasOption("de")) {
                allowInput.set(5);
                ui.EndDate = cmd.getOptionValue("de");
            }

            if (cmd.hasOption("val")) {
                allowInput.set(6);
                ui.UsageValue = Integer.parseInt(cmd.getOptionValue("val"));
            }

        } catch (ParseException e) {
            System.err.println("Failed to parse command line properties");
            help();
            return CORRECT_EXIT;
        }

        try {
            switch (Long.toString(allowInput.toLongArray()[0], 2)) {
                case "11":
                    help();
                    return CORRECT_EXIT;
                case "1111":
                    return CORRECT_WORK;
                case "1111111":
                    return CORRECT_WORK;
                default:
                    System.err.println("Wrong attributes");
                    help();
                    return CORRECT_EXIT;
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {

            System.err.println("Not enough attributes");
            help();
            return CORRECT_EXIT;

        }

    }

    private void help() {
        // This prints out some help
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("Main", options);
    }


}

