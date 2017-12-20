package com.krazyxx.happyhours.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.krazyxx.happyhours.database.Date;

/**
 * Created by Krazyxx on 19/12/2017.
 */

class CalendarAlertDialog extends AlertDialog.Builder {

    CalendarAlertDialog(Context context, final View view, final Date date) {
        super(context);
        this.setTitle("Choose your overtime !");
        this.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.d("onClick !", "yes done");
                TextView textView = (TextView) view;
                int value = (date.day() % 2 == 1) ? -15 : 15;

                if (value > 0) {
                    // Set the current selected item background color
                    textView.setBackgroundColor(Color.parseColor("#00FF00"));
                } else {
                    // Set the current selected item background color
                    textView.setBackgroundColor(Color.parseColor("#FF0000"));
                }
            }
        });
        this.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
                Log.d("onClick !", "do nothing");

            }
        });



    }


}