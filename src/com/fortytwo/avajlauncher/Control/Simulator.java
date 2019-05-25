package com.fortytwo.avajlauncher.Control;

import com.fortytwo.avajlauncher.Exceptions.IncorrectCoordinatesException;
import com.fortytwo.avajlauncher.Exceptions.IncorrectFlyableTypeException;
import com.fortytwo.avajlauncher.Exceptions.NegativeCoordinatesException;
import com.fortytwo.avajlauncher.Flyable.AircraftFactory;
import com.fortytwo.avajlauncher.Flyable.Flyable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Simulator {

    public static void main(String[] args) {

        String version = Runtime.class.getPackage().getImplementationVersion();
        System.out.println(version);

        int turn = 0;
        // create and configure logger
        Logger logger = Logger.getLogger("SimulatorLogger");
        setLogger();

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

        turn = parseFile(args[0], af, wt);
        if (turn < 0) {
            if (turn == -1)
                System.out.println("Invalid simulation number. First line must be a positive number.");
            else if (turn == -3)
                System.out.println("NullPointerException caused by incorrect filename");
            if (turn % 2 == 0)
                System.out.println("Simulation stop because of the exception above.");
            return ;
        }
        for (int i = 0; i < turn; i++) {
            wt.changeWeather();
            //logger.info("turn number : " + i);
        }
    }

    private static int parseFile(String filename, AircraftFactory af, WeatherTower wt)  {
        int turn = -1;
        int linenb = 0;
        File file;

        try {
            file = new File(filename);
        } catch (NullPointerException e) {
            return (-3);
        }
        try (Scanner sc = new Scanner(file)) {

            while (sc.hasNextLine()) {
                String line;
                String[] data;

                linenb++;
                if (linenb == 1) {
                    line = sc.nextLine();
                    try {
                        turn = Integer.valueOf(line);
                    } catch (NumberFormatException e) {
                        sc.close();
                        return (-1);
                    }

                } else {
                    line = sc.nextLine();
                    data = line.split(" ");

                    if (data.length == 5) {
                        Flyable fly;
                        try {
                            fly = af.newAircraft(data[0], data[1], Integer.valueOf(data[2]), Integer.valueOf(data[3]), Integer.valueOf(data[4]));
                            fly.registerTower(wt);
                        } catch (NegativeCoordinatesException | IncorrectFlyableTypeException | IncorrectCoordinatesException e) {
                            System.out.println(e.getMessage() + " at line " + linenb + ": " + line);
                            sc.close();
                            return (-2);
                        }
                    } else {
                        System.out.println("Invalid format at line " + linenb + ": " + line);
                        System.out.println("Valid format is TYPE NAME LONGITUDE LATITUDE HEIGHT");
                        return (-6);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Incorrect file ("+ filename +") passed: " + e.getMessage());
            return (-4);
        }

        return (turn);
    }


    // Gestion du printStackTrace !!!!
    private static void setLogger() {
        // parameters of logger
        Logger logger = Logger.getLogger("SimulatorLogger");
        FileHandler fh;

        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler("./simulation.txt");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(new MyFormatter());
            logger.setUseParentHandlers(false);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
