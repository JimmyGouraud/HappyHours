package com.krazyxx.happyhours.calendar;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.krazyxx.happyhours.MainActivity;
import com.krazyxx.happyhours.R;
import com.krazyxx.happyhours.database.Date;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Krazyxx on 20/12/2017.
 */

public class CalendarDisplay {
    private MainActivity _mainActivity;
    private static GregorianCalendar _calendar;
    private Date[] _calendarDate;
    private CalendarAdapter _calendarAdapter;
    private Months _months;

    public CalendarDisplay(MainActivity mainActivity) {
        _mainActivity = mainActivity;
        _calendar = new GregorianCalendar();
        _calendarDate = new Date[7 * 6];
        _months = new Months();

        initGridView();

        updateCalendar(_calendar.get(Calendar.YEAR), _calendar.get(Calendar.MONTH));
    }

    private void initGridView() {
        GridView calendarGridView = _mainActivity.findViewById(R.id.calendar_gridview);
        _calendarAdapter = new CalendarAdapter(_mainActivity.getApplicationContext(), android.R.layout.simple_list_item_1, _calendarDate);
        calendarGridView.setAdapter(_calendarAdapter);

        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewDateCalendar, int i, long l) {
                int monthColor = _mainActivity.getResources().getColor(_months.getColor(_calendarDate[i].month()));
                final CalendarAlertDialog builder = new CalendarAlertDialog(_mainActivity, _calendarDate[i], monthColor);
                AlertDialog dialog = builder.create();
                dialog.show();

                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(monthColor);
                dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(monthColor);
            }
        });


        calendarGridView.setOnTouchListener(new View.OnTouchListener() {
            float posX = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        posX = event.getX();
                        break;
                    case (MotionEvent.ACTION_UP):
                        float distX = event.getX() - posX;

                        int year = _calendar.get(Calendar.YEAR);
                        int month = _calendar.get(Calendar.MONTH);
                        if (distX > 200) { // Swipe Left
                            month--;
                            if (month < 0) {
                                year--;
                                month = 11;
                            }
                            updateCalendar(year, month);
                        } else if (distX < -200) { // Swipe Right
                            month++;
                            if (month > 11) {
                                year++;
                                month = 0;
                            }
                            updateCalendar(year, month);
                        }
                        break;

                }
                return false;
            }
        });
    }

    private void updateCalendar(int year, int month) {
        _calendar.set(year, month, 1);

        ((TextView) _mainActivity.findViewById(R.id.date_month_year)).setText(getStrDate());
        int monthColor = _mainActivity.getResources().getColor(_months.getColor(month));
        _mainActivity.findViewById(R.id.layout_month_year).setBackgroundColor(monthColor);
        _mainActivity.findViewById(R.id.layout_days).setBackgroundColor(monthColor);

        //backtracks to the beginning of current week (Sunday)
        _calendar.add(Calendar.DATE, Calendar.MONDAY - _calendar.get(Calendar.DAY_OF_WEEK));

        for (int i = 0; i < _calendarDate.length; i++) {
            Date date = new Date(_calendar.get(Calendar.DATE),
                    _calendar.get(Calendar.MONTH),
                    _calendar.get(Calendar.YEAR),
                    0);

            for (Date dateSaved : _mainActivity.getSavedDate()) {
                if (date.isEqual(dateSaved)) {
                    date.setInfos(dateSaved.value(), dateSaved.note());
                    break;
                }
            }

            _calendarDate[i] = date;
            _calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        _calendar.set(year, month, 1);
        _mainActivity.updateDuration();
        _calendarAdapter.notifyDataSetChanged();
    }


    private String getStrDate() {
        return _months.getString(_calendar.get(Calendar.MONTH)) + ", " + _calendar.get(Calendar.YEAR);
    }

    public void updateDate(Date newDate) {
        for (Date date : _calendarDate) {
            if (date.isEqual(newDate)) {
                date.setInfos(newDate.value(), newDate.note());
                break;
            }
        }

        _calendarAdapter.notifyDataSetChanged();
    }
}
