package com.example.edtech_team_new.models;

public class Dates {
    private int id;
    private String temperature;
    private String humidity;
    private String x;
    private String y;
    private String z;
    private String lat;
    private String longg;
    private String internalTemp;
    private String pressure;
    private String distance;


    public Dates(String temperature, String humidity, String x, String y, String z, String lat, String longg, String internalTemp, String pressure, String distance) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.lat = lat;
        this.longg = longg;
        this.internalTemp = internalTemp;
        this.pressure = pressure;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongg() {
        return longg;
    }

    public void setLongg(String longg) {
        this.longg = longg;
    }

    public String getInternalTemp() {
        return internalTemp;
    }

    public void setInternalTemp(String internalTemp) {
        this.internalTemp = internalTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
