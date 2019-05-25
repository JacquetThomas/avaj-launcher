package com.fortytwo.avajlauncher.Control;

import com.fortytwo.avajlauncher.Flyable.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int randIndex =
                (coordinates.getLongitude() + coordinates.getLatitude())
                * coordinates.getHeight()
                % weather.length;

        return (weather[randIndex]);
    }

    private WeatherProvider() {
    }
}
