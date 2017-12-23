package com.krazyxx.happyhours.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.krazyxx.happyhours.duration.Duration;
import com.krazyxx.happyhours.MainActivity;
import com.krazyxx.happyhours.R;
import com.krazyxx.happyhours.database.Date;
import com.krazyxx.happyhours.duration.Durations;

/**
 * Created by Krazyxx on 19/12/2017.
 */

class CalendarAlertDialog extends AlertDialog.Builder {
    private int _value;
    private Date _date;
    private MainActivity _mainActivity;
    private Durations _durations;

    CalendarAlertDialog(MainActivity mainActivity, Date date, int color) {
        super(mainActivity);
        _mainActivity = mainActivity;
        _date = date;
        _value = date.value();
        _durations = new Durations();

        LayoutInflater _inflater = (LayoutInflater) _mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert _inflater != null;
        final View view = _inflater.inflate(R.layout.duration, null);
        initTextViews(view);

        view.findViewById(R.id.title_alertDialog).setBackgroundColor(color);

        this.setView(view);

        this.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                _date.setValue(_value);
                _mainActivity.saveDate(_date);
            }
        });

        this.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
        });
    }


    private void initTextViews(final View view) {
        for (Duration duration : _durations) {
            final int value = duration.time();
            TextView textView = view.findViewById(duration.button());

            GradientDrawable gradientDrawable = (GradientDrawable) view.findViewById(duration.button()).getBackground();
            gradientDrawable.setColor(_mainActivity.getResources().getColor(duration.color()));
            gradientDrawable.setStroke(5, Color.WHITE);

            textView.setClickable(true);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _value = value;

                    for (Duration duration : _durations) {
                        GradientDrawable gradientDrawable1 = (GradientDrawable) view.findViewById(duration.button()).getBackground();
                        gradientDrawable1.setStroke(5, Color.WHITE);
                    }
                    GradientDrawable gradientDrawable1 = (GradientDrawable) v.getBackground();
                    gradientDrawable1.setStroke(5, Color.BLUE);
                }
            });
        }
    }
}