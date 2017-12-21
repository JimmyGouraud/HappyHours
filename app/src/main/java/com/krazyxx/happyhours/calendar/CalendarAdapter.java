package com.krazyxx.happyhours.calendar;

import android.content.Context;
import android.graphics.Color;
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


    public CalendarAdapter(Context context, int resource, Date[] dates) {
        super(context, resource, dates);
        this._dates = dates;
        this._inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view = _inflater.inflate(R.layout.date, parent, false);
        TextView textView = view.findViewById(R.id.test);
        textView.setText(String.valueOf(this._dates[position].day()));

        switch (_dates[position].value()) {
            case -15:
                textView.setBackgroundResource(R.drawable.m15);
                break;
            case -30:
                textView.setBackgroundResource(R.drawable.m30);
                break;
            case -45:
                textView.setBackgroundResource(R.drawable.m45);
                break;
            case -60:
                textView.setBackgroundResource(R.drawable.m60);
                break;
            case 15:
                textView.setBackgroundResource(R.drawable.p15);
                break;
            case 30:
                textView.setBackgroundResource(R.drawable.p30);
                break;
            case 45:
                textView.setBackgroundResource(R.drawable.p45);
                break;
            case 60:
                textView.setBackgroundResource(R.drawable.p60);
                break;
        }

        int actualMonth = _dates[15].month();
        if (_dates[position].month() != actualMonth) {
            textView.setTextColor(Color.parseColor("#9E9E9E"));
        } else {
            textView.setTextColor(Color.parseColor("#424242"));
        }

        return view;
    }
}
