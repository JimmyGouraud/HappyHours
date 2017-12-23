package com.krazyxx.happyhours.calendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.krazyxx.happyhours.R;
import com.krazyxx.happyhours.database.Date;


/**
 * Created by Krazyxx on 18/12/2017.
 */

class CalendarAdapter extends ArrayAdapter<Date> {
    private Date[] _dates;
    LayoutInflater _inflater;
    Context _context;

    int timesDuration[] = { 0, 15, 30, 45, 60, -15, -30, -45, -60 };

    private static int _durationColor[] = { R.color.color_white,
            R.color.color_p15, R.color.color_p30, R.color.color_p45, R.color.color_p60,
            R.color.color_m15, R.color.color_m30, R.color.color_m45, R.color.color_m60 };

    public CalendarAdapter(Context context, int resource, Date[] dates) {
        super(context, resource, dates);
        this._context = context;
        this._dates = dates;
        this._inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view = _inflater.inflate(R.layout.date, parent, false);
        TextView textView = view.findViewById(R.id.test);
        textView.setText(String.valueOf(this._dates[position].day()));


        GradientDrawable gradientDrawable = (GradientDrawable) _context.getDrawable(R.drawable.duration);
        assert gradientDrawable != null;
        gradientDrawable.setStroke(10, _context.getResources().getColor(R.color.color_white));
        for (int i = 0; i < timesDuration.length; i++) {
            if (timesDuration[i] == _dates[position].value()) {
                gradientDrawable.setColor(_context.getResources().getColor(_durationColor[i]));
            }
        }
        textView.setBackground(gradientDrawable);

        int actualMonth = _dates[15].month();
        if (_dates[position].month() != actualMonth) {
            textView.setTextColor(Color.parseColor("#9E9E9E"));
        } else {
            textView.setTextColor(Color.parseColor("#424242"));
        }

        return view;
    }
}
