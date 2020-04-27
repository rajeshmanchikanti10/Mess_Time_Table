package com.example.messtimetable;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;


public class favorite extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_favorite, container, false);

        CalendarView calendar=view.findViewById(R.id.calenderforfavorite);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Passadateforbroadcast passadateforbroadcast=(Passadateforbroadcast) getContext();
                passadateforbroadcast.getdate(year,month,dayOfMonth);



              /*  try {
                    dateNotification = formatToCompare
                            .parse(date);
                    calendar.setTime(dateNotification);
                } catch (java.text.ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/






              /*  alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),calendar.getTimeInMillis(),pendingIntent);*/


            }
        });
        return view;
    }


}
