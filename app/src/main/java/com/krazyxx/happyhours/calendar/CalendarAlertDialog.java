package com.krazyxx.happyhours.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
    private MainActivity _mainActivity;
    private Date _currentDate;
    private View _view;

    CalendarAlertDialog(MainActivity mainActivity, Date date, int colorTitle) {
        super(mainActivity);
        _mainActivity = mainActivity;
        _currentDate = date;

        initAlertDialog(colorTitle);
    }

    private void initAlertDialog(int colorTitle) {
        // Get layout duration and init the view
        LayoutInflater _inflater = (LayoutInflater) _mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert(_inflater != null);
        _view = _inflater.inflate(R.layout.duration, null);
        this.setView(_view);
        _view.findViewById(R.id.title_overtime).setBackgroundColor(colorTitle);

        // Init description button
        EditText descriptionButton = _view.findViewById(R.id.description);
        descriptionButton.setText(_currentDate.note());
        Drawable drawable = descriptionButton.getBackground();
        drawable.setColorFilter(colorTitle, PorterDuff.Mode.SRC_ATOP);
        descriptionButton.setBackground(drawable);


        // Init duration buttons
        Durations durations = new Durations();
        for (final Duration duration : durations) {
            TextView durationView = _view.findViewById(duration.button());
            ((GradientDrawable) durationView.getBackground()).setColor(_mainActivity.getResources().getColor(duration.color()));

            durationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _currentDate.setValue(_currentDate.value() + duration.value());
                    updateDurationTime();
                }
            });
        }

        // Init "Reset time" button
        TextView resetTimeButton = _view.findViewById(R.id.reset_time);
        ((GradientDrawable) resetTimeButton.getBackground()).setColor(_mainActivity.getResources().getColor(R.color.color_reset_time));
        resetTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _currentDate.setValue(0);
                updateDurationTime();
            }
        });

        // Init "OK" button
        this.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                EditText descriptionButton = _view.findViewById(R.id.description);
                _currentDate.setNote(descriptionButton.getText().toString());
                _mainActivity.saveDate(_currentDate);
            }
        });

        // Init "Cancel" button
        this.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
        });

        // Init duration time view
        updateDurationTime();
    }

    private void updateDurationTime() {
        TextView durationTimeView = _view.findViewById(R.id.duration_time);

        durationTimeView.setBackgroundColor(_mainActivity.getResources().getColor(R.color.color_white));
        durationTimeView.setText(_currentDate.getStrTime());

        if (_currentDate.value() > 0) {
            durationTimeView.setTextColor(_mainActivity.getResources().getColor(R.color.color_p60));
        } else if (_currentDate.value() < 0) {
            durationTimeView.setTextColor(_mainActivity.getResources().getColor(R.color.color_m60));
        } else {
            durationTimeView.setTextColor(_mainActivity.getResources().getColor(R.color.color_black));
        }
    }
}