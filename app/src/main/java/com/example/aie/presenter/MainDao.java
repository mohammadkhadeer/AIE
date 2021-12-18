package com.example.aie.presenter;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.aie.model.MainData;

import java.util.List;

@Dao
public interface MainDao {
    //insert query
    @Insert (onConflict = REPLACE)
    void insert(MainData mainData);

    //delete query
    @Delete
    void reset(List<MainData> mainData);

    //delete all query
    @Delete
    void delete(MainData mainData);

    //update
    @Query("UPDATE table_name set time_range_date = :new_time_range_date WHERE ID = :sID")
    void update(int sID,String new_time_range_date);

    //get all data
    @Query("SELECT * FROM table_name")
    List<MainData> getAll();
}
