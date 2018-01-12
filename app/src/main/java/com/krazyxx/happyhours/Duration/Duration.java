package com.krazyxx.happyhours.duration;

/**
 * Created by Krazyxx on 23/12/2017.
 */

public class Duration {
    private int _value, _color, _button;

    public Duration(int value, int color, int button) {
        _value = value;
        _color = color;
        _button = button;
    }

    public int value()                { return _value;    }
    public void setValue(int value)   { _value = value;   }
    public int color()                { return _color;    }
    public void setColor(int color)   { _color = color;   }
    public int button()               { return _button;   }
    public void setbutton(int button) { _button = button; }
}
