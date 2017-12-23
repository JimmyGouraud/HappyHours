package com.krazyxx.happyhours.duration;

/**
 * Created by Krazyxx on 23/12/2017.
 */

public class Duration {
    private int _time;
    private int _color;
    private int _button;

    public Duration(int time, int color, int button) {
        _time = time;
        _color = color;
        _button = button;
    }

    public int time()                 { return _time;     }
    public void setTime(int time)     { _time = time;     }
    public int color()                { return _color;    }
    public void setColor(int color)   { _color = color;   }
    public int button()               { return _button;   }
    public void setbutton(int button) { _button = button; }
}
