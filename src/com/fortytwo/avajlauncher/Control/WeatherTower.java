package com.fortytwo.avajlauncher.Control;

import com.fortytwo.avajlauncher.Flyable.Coordinates;

public class WeatherTower extends Tower {
    private WeatherProvider wp = WeatherProvider.getProvider();

    public String   getWeather(Coordinates coordinates) {
        return (wp.getCurrentWeather(coordinates));
    }

    protected void changeWeather() {
        this.conditionsChanged();
    }
}
