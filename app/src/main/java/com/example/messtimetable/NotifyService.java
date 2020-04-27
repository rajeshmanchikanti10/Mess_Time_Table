package com.example.messtimetable;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.net.URI;
import java.util.Calendar;

public class NotifyService extends Service {
    public NotifyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Uri Sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager nfm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getService(this,0,intent,0);
        Calendar calendar=Calendar.getInstance();
        int weekno=calendar.get(Calendar.WEEK_OF_YEAR);
        if(weekno%4==0)
            weekno=4;
        else
            weekno%=4;

        int dayOftheWeek = calendar.get(Calendar.DAY_OF_WEEK);
       Notification mnotify = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Today's Meal")
                .setContentText("BreakFast:\n\n"+show_items.str[weekno-1][dayOftheWeek-1][0]+"\n\nLunch:\n\n"+show_items.str[weekno-1][dayOftheWeek-1][1]+"\n\nSnacks:\n\n"+show_items.str[weekno-1][dayOftheWeek-1][2]+"\n\nDinner\n\n"+show_items.str[weekno-1][dayOftheWeek-1][3])

                .setSound(Sound)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        nfm.notify(1,mnotify);

    }
}
