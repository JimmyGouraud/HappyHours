package com.krazyxx.happyhours.duration;


import android.support.annotation.NonNull;

import com.krazyxx.happyhours.R;

import java.util.Iterator;

/**
 * Durations class contains all duration
 * Created by Krazyxx on 23/12/2017.
 */

public class Durations implements Iterable<Duration> {

    private Duration _durations[] = {
            new Duration(15,  R.color.color_p15, R.id.p15),
            new Duration(30,  R.color.color_p30, R.id.p30),
            new Duration(45,  R.color.color_p45, R.id.p45),
            new Duration(60,  R.color.color_p60, R.id.p60),
            new Duration(-15, R.color.color_m15, R.id.m15),
            new Duration(-30, R.color.color_m30, R.id.m30),
            new Duration(-45, R.color.color_m45, R.id.m45),
            new Duration(-60, R.color.color_m60, R.id.m60),
    };

    public Durations() {}

    public int getColor(int value) {
        if (value < -60) {
            return R.color.color_m60;
        } else if (value > 60) {
            return R.color.color_p60;
        } else {
            for (Duration duration : _durations) {
                if (duration.value() == value) {
                    return duration.color();
                }
            }
        }

        return R.color.color_white;
    }


    @NonNull
    @Override
    public Iterator<Duration> iterator() {
        Iterator<Duration> it = new Iterator<Duration>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < _durations.length && _durations[currentIndex] != null;
            }

            @Override
            public Duration next() {
                return _durations[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
