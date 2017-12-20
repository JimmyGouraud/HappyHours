package com.krazyxx.happyhours.calendar;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.krazyxx.happyhours.MainActivity;
import com.krazyxx.happyhours.R;
import com.krazyxx.happyhours.database.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Krazyxx on 20/12/2017.
 */

public class CalendarDisplay {
    private MainActivity _mainActivity;

    private static GregorianCalendar _calendar;
    private ArrayList<Date> _datesSaved;
    private Date[] _calendarDate;

    private static String _months[] = { "January", "February", "March", "April", "May", "June",
            "July", "Aout", "September", "October", "November", "December" };

    public CalendarDisplay(MainActivity mainActivity) {
        _mainActivity = mainActivity;
        _datesSaved = (ArrayList<Date>) mainActivity.getSavedDate();

        _calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        _calendarDate = new Date[7*6];

        initGridView();

        updateCalendar(_calendar.get(Calendar.YEAR), _calendar.get(Calendar.MONTH));
    }

    private void initGridView() {
        GridView calendarGridview = _mainActivity.findViewById(R.id.calendar_gridview);
        CalendarAdapter calendarAdapter = new CalendarAdapter(_mainActivity.getApplicationContext(), android.R.layout.simple_list_item_1, _calendarDate);
        calendarGridview.setAdapter(calendarAdapter);

        calendarGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CalendarAlertDialog builder = new CalendarAlertDialog(_mainActivity, view, _calendarDate[i]);
                AlertDialog dialog = builder.create();
                dialog.show();

                int value = (_calendarDate[i].day() % 2 == 1) ? -15 : 15;

                Date newDate = new Date(_calendarDate[i]);
                newDate.setValue(value);

                if (isNewDate(newDate)) {
                    saveDate(newDate);
                }

                _mainActivity.updateDuration(computeDuration());
            }
        });
    }

    private void updateCalendar(int year, int month) {
        _calendar.set(year, month, 1);
        ((TextView) _mainActivity.findViewById(R.id.date_month_year)).setText(getDate());

        //backtracks to the beginning of current week (Sunday)
        _calendar.add(Calendar.DAY_OF_MONTH, Calendar.MONDAY - _calendar.get(Calendar.DAY_OF_WEEK));

        for (int i = 0; i < _calendarDate.length; i++){
            Date date = new Date(_calendar.get(Calendar.DAY_OF_MONTH),
                                 _calendar.get(Calendar.MONTH),
                                 _calendar.get(Calendar.YEAR),
                                 0);

            for (Date dateSaved : _datesSaved) {
                if (date.isEqual(dateSaved)) {
                    date.setValue(dateSaved.value());
                    break;
                }
            }

            _calendarDate[i] = date;

            //add one to the day and keep going
            _calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        _mainActivity.updateDuration(computeDuration());
    }

    private int computeDuration() {
        int minutes = 0;
        for (Date date : _datesSaved) {
            minutes += date.value();
        }
        return minutes;
    }

    private String getDate() {
        return _months[_calendar.get(Calendar.MONTH)] + ", " + _calendar.get(Calendar.YEAR);
    }

    private boolean isNewDate(Date newDate) {
        for (Date date : _datesSaved) {
            if (newDate.isEqual(date)) {
                return false;
            }
        }
        return true;
    }

    private void saveDate(Date date) {
        Log.d("saveDate", "date saved : " + _datesSaved.size());
        _datesSaved.add(date);
        _mainActivity.saveDate(date);
    }
}
