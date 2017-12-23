package com.krazyxx.happyhours.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.krazyxx.happyhours.Duration.Durations;
import com.krazyxx.happyhours.MainActivity;
import com.krazyxx.happyhours.R;
import com.krazyxx.happyhours.database.Date;

/**
 * Created by Krazyxx on 19/12/2017.
 */

class CalendarAlertDialog extends AlertDialog.Builder {
    LayoutInflater _inflater;
    int _value;
    View _viewDateCalendar;
    Date _date;
    MainActivity _mainActivity;

    Durations _durations;

    CalendarAlertDialog(MainActivity mainActivity, View viewDateCalendar, Date date, int color) {
        super(mainActivity);
        _mainActivity = mainActivity;
        _date = date;
        _viewDateCalendar = viewDateCalendar;
        _value = date.value();
        _durations = new Durations();

        this._inflater = (LayoutInflater) _mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        for (int i = 0; i < _durations.length; i++) {
            final int value = _durations.get(i).time();
            TextView textView = view.findViewById(_durations.get(i).button());

            GradientDrawable gradientDrawable = (GradientDrawable) view.findViewById(_durations.get(i).button()).getBackground();
            gradientDrawable.setColor(_mainActivity.getResources().getColor(_durations.get(i).color()));
            gradientDrawable.setStroke(5, Color.WHITE);

            textView.setClickable(true);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _value = value;

                    for (int buttonsDuration : _durations.getDurationButton()) {
                        GradientDrawable d = (GradientDrawable) view.findViewById(buttonsDuration).getBackground();
                        d.setStroke(5, Color.WHITE);
                    }
                    GradientDrawable d = (GradientDrawable) v.getBackground();
                    d.setStroke(5, Color.BLUE);
                }
            });
        }
    }
}