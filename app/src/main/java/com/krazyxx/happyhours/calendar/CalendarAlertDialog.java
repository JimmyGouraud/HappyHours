package com.krazyxx.happyhours.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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


    int buttonsDuration[] = { R.id.m0,
            R.id.p15, R.id.p30, R.id.p45, R.id.p60,
            R.id.m15, R.id.m30, R.id.m45, R.id.m60
    };

    int timesDuration[] = { 0, 15, 30, 45, 60, -15, -30, -45, -60 };

    private static int _durationColor[] = { R.color.color_0,
            R.color.color_p15, R.color.color_p30, R.color.color_p45, R.color.color_p60,
            R.color.color_m15, R.color.color_m30, R.color.color_m45, R.color.color_m60 };

    CalendarAlertDialog(MainActivity mainActivity, View viewDateCalendar, Date date, int color) {
        super(mainActivity);
        _mainActivity = mainActivity;
        _date = date;
        _viewDateCalendar = viewDateCalendar;
        _value = date.value();

        this._inflater = (LayoutInflater) _mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = _inflater.inflate(R.layout.duration, null);
        initTextViews(view);

        view.findViewById(R.id.title_alertDialog).setBackgroundColor(color);

        this.setView(view);

        this.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                _date.setValue(_value);
                GradientDrawable gradientDrawable = (GradientDrawable) _mainActivity.getDrawable(R.drawable.duration);

                for (int buttonsDuration : buttonsDuration) {
                    GradientDrawable gd = (GradientDrawable) view.findViewById(buttonsDuration).getBackground();
                    gd.setStroke(5, Color.WHITE);
                }

                _viewDateCalendar.setBackground(gradientDrawable);

                _mainActivity.saveDate(_date);
            }
        });

        this.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
        });
    }


    private void initTextViews(final View view) {
        for (int i = 0; i < buttonsDuration.length; i++) {
            final int value = timesDuration[i];
            TextView textView = view.findViewById(buttonsDuration[i]);

            GradientDrawable gradientDrawable = (GradientDrawable) view.findViewById(buttonsDuration[i]).getBackground();
            gradientDrawable.setColor(_mainActivity.getResources().getColor(_durationColor[i]));
            gradientDrawable.setStroke(5, Color.WHITE);

            textView.setClickable(true);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _value = value;

                    for (int buttonsDuration : buttonsDuration) {
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