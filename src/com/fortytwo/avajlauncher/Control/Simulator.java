package com.fortytwo.avajlauncher.Control;

import com.fortytwo.avajlauncher.Exceptions.*;
import com.fortytwo.avajlauncher.Flyable.AircraftFactory;
import com.fortytwo.avajlauncher.Flyable.Flyable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


public class Simulator {

    public static void main(String[] args) {

        // create and configure logger
        try {
            setLogger();
        } catch (LoggerException e) {
            System.out.println(e.getMessage());
            return ;
        }

        // checking argument
        if (args.length == 0) {
            System.out.println("Missing scenario file");
            return ;
        } else if ( args.length > 1) {
            System.out.println("Too much arguments, only one scenario file is required");
            return ;
        }

        AircraftFactory af = new AircraftFactory();
        WeatherTower wt = new WeatherTower();
        int turn;

        try {
            turn = parseFile(args[0], af, wt);
        } catch (ParseScenarioException e) {
            ///////////////////////////////////////
            // Need to choose between those two  //
            // System.out.println(e.toString()); //
            ///////////////////////////////////////
            System.out.println(e.getMessage());
            return ;
        }

        for (int i = 0; i < turn; i++) {
            wt.changeWeather();
        }
    }

    private static int parseFile(String filename, AircraftFactory af, WeatherTower wt) throws ParseScenarioException{
        File file;
        int turn;
        int linenb = 0;

        try {
            file = new File(filename);
        } catch (NullPointerException e) {
            throw new ParseScenarioException(e.getMessage(), e);
        }

        try (Scanner sc = new Scanner(file)) {
            String line;

            try {
                line = sc.nextLine();
                turn = Integer.valueOf(line);
                linenb++;
                if (turn < 0)
                    throw new ParseScenarioException("First line must be a positive number.");
            } catch (NumberFormatException | NoSuchElementException e) {
                throw new ParseScenarioException("Invalid simulation number. First line must be a positive number.", e);
            }

            while (sc.hasNextLine()) {
                String[] data;

                line = sc.nextLine();
                data = line.split(" ");
                linenb++;
                if (data.length == 5) {
                    Flyable fly;
                    try {
                        fly = af.newAircraft(
                                data[0],                    // type
                                data[1],                    // name
                                Integer.valueOf(data[2]),   // longitude
                                Integer.valueOf(data[3]),   // latitude
                                Integer.valueOf(data[4]));  // height
                        fly.registerTower(wt);
                    } catch (NegativeCoordinatesException | IncorrectFlyableTypeException | IncorrectCoordinatesException e) {
                        throw new ParseScenarioException(e.getMessage() + " at line " + linenb + ": " + line, e);
                    }
                } else {
                    throw new ParseScenarioException("Invalid format at line " + linenb + ": " + line + "\nValid format is TYPE NAME LONGITUDE LATITUDE HEIGHT");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect file ("+ filename +") passed: " + e.getMessage());
            turn = -4;
        }

        return (turn);
    }

    private static void setLogger() throws LoggerException {
        Logger logger = Logger.getLogger("SimulatorLogger");
        FileHandler fh;

        try {
            fh = new FileHandler("./simulation.txt");
            logger.addHandler(fh);
            fh.setFormatter(new MyFormatter());
            logger.setUseParentHandlers(false);
        } catch (SecurityException e) {
            throw new LoggerException(e);
        } catch (IOException e) {
            throw new LoggerException("Input/Output exception" , e);
        }
    }
}
