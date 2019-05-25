package com.fortytwo.avajlauncher.Flyable;

import com.fortytwo.avajlauncher.Control.WeatherTower;
import com.fortytwo.avajlauncher.Exceptions.IncorrectCoordinatesException;
import com.fortytwo.avajlauncher.Exceptions.NegativeCoordinatesException;

import java.util.logging.Logger;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private Logger logger = Logger.getLogger("SimulatorLogger");

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();

        try {
            switch (weatherTower.getWeather(coordinates)){
                case "RAIN":
                    coordinates = new Coordinates(longitude, latitude, height - 5);
                    logger.info(this.toString() + ": Not rain again ! I don't wanna be drown :(");
                    break;
                case "FOG":
                    coordinates = new Coordinates(longitude, latitude, height - 3);
                    logger.info(this.toString() + ": Why it's foggy but i'm not high ??");
                    break;
                case "SUN":
                    coordinates = new Coordinates(longitude + 2, latitude, height + 4);
                    logger.info(this.toString() + ": Qué calor !");
                    break;
                case "SNOW":
                    coordinates = new Coordinates(longitude, latitude, height - 15);
                    logger.info(this.toString() + ": Let it go ! Let it go ! Can't hold it back anymooooore!");
                    break;
            }
        } catch (NegativeCoordinatesException e) {
            coordinates.setHeight(0);
        } catch (IncorrectCoordinatesException e) {
            coordinates.setHeight(100);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }

    @Override
    public String toString() {
        return ("Baloon#" + super.toString());
    }
}