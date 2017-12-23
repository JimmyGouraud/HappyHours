package com.krazyxx.happyhours.calendar;

/**
 * Created by Krazyxx on 23/12/2017.
 */

public class Month {
    private String _string;
    private int _color;

    Month(String string, int color) {
        _string = string;
        _color = color;
    }

    public String string()              { return _string;  }
    public void setString(String month) { _string = month; }
    public int color()                  { return _color;   }
    public void setColor(int color)     { _color = color;  }
}
