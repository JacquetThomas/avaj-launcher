package com.fortytwo.avajlauncher.Flyable;

import com.fortytwo.avajlauncher.Control.WeatherTower;
import com.fortytwo.avajlauncher.Exceptions.IncorrectCoordinatesException;
import com.fortytwo.avajlauncher.Exceptions.NegativeCoordinatesException;

import java.util.logging.Logger;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private Logger logger = Logger.getLogger("SimulatorLogger");

    JetPlane(String name, Coordinates coordinates) {
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
                    coordinates = new Coordinates(longitude, latitude + 5, height);
                    logger.info(this.toString() + ": Not afraid of rain, I'm the boss.");
                    break;
                case "FOG":
                    coordinates = new Coordinates(longitude, latitude + 1, height);
                    logger.info(this.toString() + ": Fog is like a second-skin to me.");
                    break;
                case "SUN":
                    coordinates = new Coordinates(longitude, latitude + 10, height + 2);
                    logger.info(this.toString() + ": Like that.. but not for too long.");
                    break;
                case "SNOW":
                    coordinates = new Coordinates(longitude, latitude, height - 7);
                    logger.info(this.toString() + ": I WANNA MAKE A SNOWPLANE!!!");
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
        return ("JetPlane#" + super.toString());
    }

    public String toString(String message) {
        return (this.toString() + ": " + message);
    }
}