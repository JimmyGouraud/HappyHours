package com.krazyxx.happyhours;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
        //_db.clearDatabase();
        Log.d("Tag", "Saved date number: " + _database.dateDao().countDates());

        _calendarDisplay = new CalendarDisplay(this);

    }

    public void updateDuration(int durationTime) {
        TextView durationView = findViewById(R.id.duration);

        String timer;
        if (durationTime > 0) {
            timer = "+";
            durationView.setTextColor(Color.parseColor("#27ae60"));
        } else if (durationTime < 0) {
            timer = "-";
            durationView.setTextColor(Color.parseColor("#c0392b"));
        } else {
            timer = "";
            durationView.setTextColor(Color.parseColor("#000000"));
        }

        timer += Math.abs(durationTime / 60) + "h " + Math.abs(durationTime % 60) + "min !";

        durationView.setText(timer);
    }

    public void saveDate(Date date) {
        _database.dateDao().insertAll(date);
    }

    public List<Date> getSavedDate() {
        return _database.dateDao().getAll();
    }
}
