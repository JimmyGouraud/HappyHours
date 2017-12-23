package com.krazyxx.happyhours.duration;


import android.support.annotation.NonNull;

import com.krazyxx.happyhours.R;

import java.util.Iterator;

/**
 * Created by Krazyxx on 23/12/2017.
 */

public class Durations implements Iterable<Duration> {
    private Duration _durations[] = {
            new Duration(0,   R.color.color_0,   R.id.m0),
            new Duration(15,  R.color.color_p15, R.id.p15),
            new Duration(30,  R.color.color_p30, R.id.p30),
            new Duration(45,  R.color.color_p45, R.id.p45),
            new Duration(60,  R.color.color_p60, R.id.p60),
            new Duration(-15, R.color.color_m15, R.id.m15),
            new Duration(-30, R.color.color_m30, R.id.m30),
            new Duration(-45, R.color.color_m45, R.id.m45),
            new Duration(-60, R.color.color_m60, R.id.m60),
    };

    public int length;

    public Durations() {
        length = _durations.length;
    }

    public int[] getDurationTime() {
        int times[] = new int[_durations.length];
        for (int i = 0; i < _durations.length; i++) {
            times[i] = _durations[i].time();
        }
        return times;
    }

    public int[] getDurationColor() {
        int times[] = new int[_durations.length];
        for (int i = 0; i < _durations.length; i++) {
            times[i] = _durations[i].color();
        }
        return times;
    }

    public int[] getDurationButton() {
        int times[] = new int[_durations.length];
        for (int i = 0; i < _durations.length; i++) {
            times[i] = _durations[i].button();
        }
        return times;
    }

    public Duration get(int i) {
        return _durations[i];
    }

    @NonNull
    @Override
    public Iterator<Duration> iterator() {
        Iterator<Duration> it = new Iterator<Duration>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < length && _durations[currentIndex] != null;
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
