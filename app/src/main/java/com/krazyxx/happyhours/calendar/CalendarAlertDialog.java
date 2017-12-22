package com.krazyxx.happyhours.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

                switch (_value) {
                    case -15:
                        _viewDateCalendar.setBackgroundResource(R.drawable.m15);
                        break;
                    case -30:
                        _viewDateCalendar.setBackgroundResource(R.drawable.m30);
                        break;
                    case -45:
                        _viewDateCalendar.setBackgroundResource(R.drawable.m45);
                        break;
                    case -60:
                        _viewDateCalendar.setBackgroundResource(R.drawable.m60);
                        break;
                    case 15:
                        _viewDateCalendar.setBackgroundResource(R.drawable.p15);
                        break;
                    case 30:
                        _viewDateCalendar.setBackgroundResource(R.drawable.p30);
                        break;
                    case 45:
                        _viewDateCalendar.setBackgroundResource(R.drawable.p45);
                        break;
                    case 60:
                        _viewDateCalendar.setBackgroundResource(R.drawable.p60);
                        break;
                    default:
                        _viewDateCalendar.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        break;
                }

                _mainActivity.saveDate(_date);
            }
        });

        this.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
        });
    }

    private void initTextViews(View view) {
        TextView textView;
        textView = view.findViewById(R.id.p15);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _value = 15;
            }
        });

        textView = view.findViewById(R.id.p30);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _value = 30;
            }
        });

        textView = view.findViewById(R.id.p45);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _value = 45;
            }
        });

        textView = view.findViewById(R.id.p60);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _value = 60;
            }
        });

        textView = view.findViewById(R.id.m15);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _value = -15;
            }
        });

        textView = view.findViewById(R.id.m30);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _value = -30;
            }
        });

        textView = view.findViewById(R.id.m45);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _value = -45;
            }
        });

        textView = view.findViewById(R.id.m60);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _value = -60;
            }
        });

        textView = view.findViewById(R.id.m0);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _value = 0;
            }
        });
    }
}