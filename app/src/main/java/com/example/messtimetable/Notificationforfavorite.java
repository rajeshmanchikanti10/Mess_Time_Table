package com.example.messtimetable;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class Notificationforfavorite extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        showNotification(context);

    }
    private  void showNotification(Context context)
    {


        Calendar calendar=Calendar.getInstance();

        Toast.makeText(context,"in side notification for favorite",Toast.LENGTH_SHORT).show();

        int weekno=calendar.get(Calendar.WEEK_OF_YEAR);
        if(weekno%4==0)
            weekno=4;
        else
            weekno%=4;

        int dayOftheWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String title=context.getString(R.string.Notificationforfavorite);
      //  Toast.makeText(context,"favorite food recorded successfully",Toast.LENGTH_SHORT).show();
        Notificationforfavorite.NotificationHelper notificationHelper=new Notificationforfavorite.NotificationHelper(context);
        notificationHelper.createNotification(title,"BreakFast:\n\n"+show_items.str[weekno-1][dayOftheWeek-1][0]+"\n\nLunch:\n\n"+show_items.str[weekno-1][dayOftheWeek-1][1]+"\n\nSnacks:\n\n"+show_items.str[weekno-1][dayOftheWeek-1][2]+"\n\nDinner\n\n"+show_items.str[weekno-1][dayOftheWeek-1][3]);


    }




    public class NotificationHelper {

        private Context mContext;
        private NotificationManager mNotificationManager;
        private NotificationCompat.Builder mBuilder;
        public static final String NOTIFICATION_CHANNEL_ID = "10001";

        public NotificationHelper(Context context) {
            mContext = context;
        }

        /**
         * Create and push the notification
         */
        public void createNotification(String title, String message)
        {
            /**Creates an explicit intent for an Activity in your app**/
            Intent resultIntent = new Intent(mContext , MainActivity.class);
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                    0 /* Request code */, resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder = new NotificationCompat.Builder(mContext);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(false)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(message))

                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setContentIntent(resultPendingIntent);

            mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
           // Toast.makeText(mContext,"in the notification for favorite",Toast.LENGTH_SHORT).show();

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
            {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                assert mNotificationManager != null;
                mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
                mNotificationManager.createNotificationChannel(notificationChannel);
            }
            assert mNotificationManager != null;
            mNotificationManager.notify(1 /* Request Code */, mBuilder.build());
        }
    }
}
