package com.krazyxx.happyhours.calendar;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
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
    private Context _context;
    private Date[] _dates;

    public CalendarAdapter(Context context, int resource, Date[] dates) {
        super(context, resource, dates);
        this._context = context;
        this._dates = dates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(this._context);
        textView.setText(String.valueOf(_dates[position].day()));
        textView.setGravity(Gravity.CENTER);
        textView.setHeight(100);

        switch (_dates[position].value()) {
            case -15:
                textView.setBackgroundColor(_context.getResources().getColor(R.color.color_m15));
                break;
            case -30:
                textView.setBackgroundColor(_context.getResources().getColor(R.color.color_m30));
                break;
            case -45:
                textView.setBackgroundColor(_context.getResources().getColor(R.color.color_m45));
                break;
            case -60:
                textView.setBackgroundColor(_context.getResources().getColor(R.color.color_m60));
                break;
            case 15:
                textView.setBackgroundColor(_context.getResources().getColor(R.color.color_p15));
                break;
            case 30:
                textView.setBackgroundColor(_context.getResources().getColor(R.color.color_p30));
                break;
            case 45:
                textView.setBackgroundColor(_context.getResources().getColor(R.color.color_p45));
                break;
            case 60:
                textView.setBackgroundColor(_context.getResources().getColor(R.color.color_p60));
                break;
            default:
                textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
        }

        int actualMonth = _dates[15].month();
        if (_dates[position].month() != actualMonth) {
            textView.setTextColor(Color.parseColor("#9E9E9E"));
        } else {
            textView.setTextColor(Color.parseColor("#424242"));
        }

        return textView;
    }
}
