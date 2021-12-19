package com.example.aie.model;

import androidx.annotation.ColorInt;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "table_name")
public class MainData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="creator_name")
    private String creator_name;

    @ColumnInfo(name="time_range_date")
    private String time_range_date;

    @ColumnInfo(name="name_of_day")
    private String name_of_day;

    @ColumnInfo(name="time_range_start")
    private String time_range_start;

    @ColumnInfo(name="time_range_end")
    private String time_range_end;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime_range_date() {
        return time_range_date;
    }

    public void setTime_range_date(String time_range_date) {
        this.time_range_date = time_range_date;
    }

    public String getName_of_day() {
        return name_of_day;
    }

    public void setName_of_day(String name_of_day) {
        this.name_of_day = name_of_day;
    }

    public String getTime_range_start() {
        return time_range_start;
    }

    public void setTime_range_start(String time_range_start) {
        this.time_range_start = time_range_start;
    }

    public String getTime_range_end() {
        return time_range_end;
    }

    public void setTime_range_end(String time_range_end) {
        this.time_range_end = time_range_end;
    }
}
