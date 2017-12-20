package com.krazyxx.happyhours.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.util.Log;

/**
 * Created by Krazyxx on 17/12/2017.
 */

@Entity(tableName = "date")
public class Date {
    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "day")
    private int _day;

    @ColumnInfo(name = "month")
    private int _month;

    @ColumnInfo(name = "year")
    private int _year;

    @ColumnInfo(name = "value")
    private int _value;


    public Date(int day, int month, int year, int value) {
        _day   = day;
        _month = month;
        _year  = year;
        _value = value;
    }

    public Date(Date date) {
        _day   = date.day();
        _month = date.month();
        _year  = date.year();
        _value = date.value();
    }

    public boolean isEqual(Date date) {
        return (_day   == date.day())   &&
               (_month == date.month()) &&
               (_year  == date.year());
    }

    public int day()                { return _day;    }
    public void setDay(int day)     { _day = day;     }
    public int month()              { return _month;  }
    public void setMonth(int month) { _month = month; }
    public int year()               { return _year;   }
    public void setYear(int year)   { _year = year;   }
    public int value()              { return _value;  }
    public void setValue(int value) { _value = value; }
    public int id()                 { return _id;     }
    public void setId(int id)       { _id = id;       }

    public void displayDate() {
        Log.d("displayDate", this.day() + "/" + this.month() + "/" + this.year() + " - " + this.value());
    }
}
