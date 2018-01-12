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

    @ColumnInfo(name = "note")
    private String _note;

    public Date(int day, int month, int year, int value) {
        _day   = day;
        _month = month;
        _year  = year;
        _value = value;
        _note = "";
    }

    public boolean isEqual(Date date) {
        return (_day   == date.day())   &&
               (_month == date.month()) &&
               (_year  == date.year());
    }

    // Getter
    public int id()      { return _id;    }
    public int day()     { return _day;   }
    public int month()   { return _month; }
    public int year()    { return _year;  }
    public int value()   { return _value; }
    public String note() { return _note;  }

    // Setter
    public void setId(int id)        { _id = id;       }
    public void setValue(int value)  { _value = value; }
    public void setNote(String note) { _note = note;   }
    public void setInfos(int value, String note) {
        _value = value;
        _note = note;
    }

    public boolean isEmpty() {
        return (_value == 0 && _note.equals(""));
    }

    public String getStrTime() {
        String sign = "";

        if (_value > 0) {
            sign = "+";
        } else if (_value < 0) {
            sign = "-";
        }

        return sign + Math.abs(_value / 60) + "h " + Math.abs(_value % 60) + "min !";
    }
}
