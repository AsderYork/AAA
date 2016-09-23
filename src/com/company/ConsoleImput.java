/*
package com.company;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class ConsoleImput {
    private String[] cl = null;

    Options options = new Options();

    CommandLineParser parser = new DefaultParser();

    public ConsoleImput(String[] args, UserInput ui) {

        cl = args;
        options.addOption("h", "help", false, "show help");
    }

    public int parse() {
        CommandLineParser parser = new BasicParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, cl);


            if (cmd.hasOption("h")) {
                help();

                //need set in user input i gess
            }


        } catch (ParseException e) {
            System.err.println("Failed to parse command line properties");
            help();

        }
        private void help(){
            HelpFormatter formater = new HelpFormatter();
            formater.printHelp("Main", options);
            System.exit(0);
        }

    }