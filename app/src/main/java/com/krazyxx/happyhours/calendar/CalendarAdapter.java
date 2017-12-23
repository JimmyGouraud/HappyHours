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

import com.krazyxx.happyhours.Duration.Durations;
import com.krazyxx.happyhours.R;
import com.krazyxx.happyhours.database.Date;

import javax.xml.datatype.Duration;


/**
 * Created by Krazyxx on 18/12/2017.
 */

class CalendarAdapter extends ArrayAdapter<Date> {
    private Date[] _dates;
    LayoutInflater _inflater;
    Context _context;
    Durations _durations;

    public CalendarAdapter(Context context, int resource, Date[] dates) {
        super(context, resource, dates);
        this._context = context;
        this._dates = dates;
        this._inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _durations = new Durations();
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
        if (_dates[position].value() == 0) {
            gradientDrawable.setColor(Color.parseColor("#FFFFFF"));
        } else {
            for (int i = 0; i < _durations.length; i++) {
                if (_durations.get(i).time() == _dates[position].value()) {
                    gradientDrawable.setColor(_context.getResources().getColor(_durations.get(i).color()));
                }
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
