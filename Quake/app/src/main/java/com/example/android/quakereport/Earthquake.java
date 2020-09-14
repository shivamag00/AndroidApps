package com.example.android.quakereport;

public class Earthquake {
    private String magnitude;
    private String city;
    private long date;
    public Earthquake(String magnitude, String city, long date ) {
        this.magnitude = magnitude;
        this.city = city;
        this.date = date;
    }

    public String getMagnitude() {
        return this.magnitude;
    }

    public String getCity() {
        return this.city;
    }

    public long getDate() {
        return this.date;
    }
}
