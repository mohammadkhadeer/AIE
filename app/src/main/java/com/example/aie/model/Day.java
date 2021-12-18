package com.example.aie.model;

public class Day {
    String name_of_day,date_of_day;

    public Day(String name_of_day, String date_of_day) {
        this.name_of_day = name_of_day;
        this.date_of_day = date_of_day;
    }

    public String getName_of_day() {
        return name_of_day;
    }

    public void setName_of_day(String name_of_day) {
        this.name_of_day = name_of_day;
    }

    public String getDate_of_day() {
        return date_of_day;
    }

    public void setDate_of_day(String date_of_day) {
        this.date_of_day = date_of_day;
    }
}
