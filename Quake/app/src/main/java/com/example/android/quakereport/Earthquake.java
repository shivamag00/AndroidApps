package com.example.android.quakereport;

public class Earthquake {
    private String magnitude;
    private String city;
    private String date;
    public Earthquake(String magnitude, String city, String date ) {
        this.magnitude = magnitude;
        this.city = city;
        this.date = date;
    }
}
