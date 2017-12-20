package com.krazyxx.happyhours.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Krazyxx on 18/12/2017.
 */

@Dao
public interface DateDao {
    @Query("SELECT * FROM date")
    List<Date> getAll();

    @Query("SELECT COUNT(*) from date")
    int countDates();

    @Insert
    void insertAll(Date... dates);

    @Delete
    void delete(Date date);
}
