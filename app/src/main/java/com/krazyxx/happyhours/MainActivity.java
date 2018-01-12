package com.krazyxx.happyhours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.krazyxx.happyhours.calendar.CalendarDisplay;
import com.krazyxx.happyhours.database.AppDatabase;
import com.krazyxx.happyhours.database.Date;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    public AppDatabase _database;
    CalendarDisplay _calendarDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _database = AppDatabase.getAppDatabase(this);
        //_database.clearDatabase();

        _calendarDisplay = new CalendarDisplay(this);
    }

    private int computeDuration() {
        int durationTime = 0;
        for (Date dateSaved : _database.dateDao().getAll()) {
            durationTime += dateSaved.value();
        }
        return durationTime;
    }

    public void updateDuration() {
        int durationTime = computeDuration();
        TextView durationView = findViewById(R.id.duration);

        String timer;
        if (durationTime > 0) {
            timer = "+";
            durationView.setTextColor(this.getResources().getColor(R.color.color_p60));
        } else if (durationTime < 0) {
            timer = "-";
            durationView.setTextColor(this.getResources().getColor(R.color.color_m60));
        } else {
            timer = "";
            durationView.setTextColor(this.getResources().getColor(R.color.color_black));
        }

        timer += Math.abs(durationTime / 60) + "h " + Math.abs(durationTime % 60) + "min !";

        durationView.setText(timer);
    }

    public void saveDate(Date date) {
        for (Date dateSaved : _database.dateDao().getAll()) {
            if (dateSaved.isEqual(date)) {
                _database.dateDao().delete(dateSaved);
                break;
            }
        }

        if (!date.isEmpty()) {
            _database.dateDao().insertAll(date);
        }

        _calendarDisplay.updateDate(date);
        updateDuration();
    }

    public List<Date> getSavedDate() {
        return _database.dateDao().getAll();
    }
}
