package com.krazyxx.happyhours.calendar;

import com.krazyxx.happyhours.R;

/**
 * Created by Krazyxx on 23/12/2017.
 */

class Months {
    private Month _months[] = {
            new Month("January",   R.color.color_january),
            new Month("February",  R.color.color_february),
            new Month("March",     R.color.color_march),
            new Month("April",     R.color.color_april),
            new Month("May",       R.color.color_may),
            new Month("June",      R.color.color_june),
            new Month("July",      R.color.color_july),
            new Month("Aout",      R.color.color_august),
            new Month("September", R.color.color_september),
            new Month("October",   R.color.color_october),
            new Month("November",  R.color.color_november),
            new Month("December",  R.color.color_december),
    };

    Months() {}

    int getColor(int i) {
        return _months[i].color();
    }
    String getString(int i) {
        return _months[i].string();
    }
}
