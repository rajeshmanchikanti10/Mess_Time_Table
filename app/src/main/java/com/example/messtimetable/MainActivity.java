package com.example.messtimetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity  implements comment,Passadateforbroadcast{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoogle;
    CalendarView calendar;
    ImageView homebutton,selectbutton,logoutbutton,favoritebutton;
    Long Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* TextView textView=findViewById(R.id.date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());
        textView.setText(currentDateandTime);*/
     /*drawerLayout=findViewById(R.id.mylayout);
     mtoogle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
     drawerLayout.addDrawerListener(mtoogle);
     mtoogle.syncState();
     getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        calendar=findViewById(R.id.calenderforfavorite);
        homebutton=findViewById(R.id.homebutton);
        selectbutton=findViewById(R.id.selectbutton);
        logoutbutton=findViewById(R.id.logoutbutton);
        favoritebutton=findViewById(R.id.favoritebutton);
        alarmmethod();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Calendar calendar=Calendar.getInstance();
                calendar.set(year,month,dayOfMonth);

                int weekno=(calendar.get(Calendar.WEEK_OF_YEAR));
                if(weekno%4==0)
                    weekno=4;
                else
                    weekno%=4;

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                Bundle bundle = new Bundle();
                bundle.putInt("weekno",weekno);
                bundle.putInt("dayofweek",dayOfWeek);


                Fragment fragment=new show_items();
                fragment.setArguments(bundle);
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_activity_fragment,fragment).addToBackStack(null).commit();
               // Toast.makeText(getApplicationContext(),dayOfMonth+"/"+month+"/"+year+"the week number is :"+weekno+"\n"+"and the day of week"+dayOfWeek,Toast.LENGTH_SHORT).show();

            }
        });

       SharedPreferences prefs=getSharedPreferences("prefs",MODE_PRIVATE);
       boolean firststart=prefs.getBoolean("firststart",true);
       if(firststart)
       {
           callLogin();
       }


        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.main_activity_fragment,new Home()).commit();

            }
        });
        selectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_activity_fragment,new select()).commit();
            }
        });
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mauth;
                mauth=FirebaseAuth.getInstance();
                mauth.signOut();
                Intent intent=new Intent(com.example.messtimetable.MainActivity.this,Login.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        favoritebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.main_activity_fragment,new favorite()).commit();
                //Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
            }
        });

    }
  private void alarmmethod(){
        Intent intent=new Intent(this,Broadcastfornotification.class);
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
      PendingIntent pendingIntent= PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
      Calendar calendar=Calendar.getInstance();
      calendar.set(Calendar.SECOND,0);
      calendar.set(Calendar.MINUTE,25);
      calendar.set(Calendar.HOUR_OF_DAY,11);
      calendar.set(Calendar.AM_PM,Calendar.AM);
      alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_HALF_DAY,pendingIntent);


  }

    @Override
    protected void onStart() {

        super.onStart();
        Log.i("onstart","onstart called");
        SharedPreferences prefs=getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firststart=prefs.getBoolean("firststart",false);
        if(firststart)
        {
            callLogin();
        }

    }

    public void callLogin()
    {  SharedPreferences prefs=getSharedPreferences("prefs",MODE_PRIVATE);
    SharedPreferences.Editor editor=prefs.edit();
    editor.putBoolean("firststart",false);
    editor.apply();
        startActivity(new Intent(this,Login.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.item_menu,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.logout1:
            {
                FirebaseAuth mauth;
                mauth=FirebaseAuth.getInstance();
                mauth.signOut();
                SharedPreferences perferences=getSharedPreferences("prefs",MODE_PRIVATE);
                SharedPreferences.Editor editor=perferences.edit();
                editor.putBoolean("firststart",true);
                editor.apply();
                Intent intent=new Intent(this,Login.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;

            }
            case R.id.revw:
            {
               Fragment fragment=new Review();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_activity_fragment,fragment).commit();
               //startActivity(new Intent(this,IntentReview.class))
                return true;
            }
            case R.id.selct:
            {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_activity_fragment,new select()).commit();

                return true;
            }
            case R.id.home:
            {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                FragmentManager manager=getSupportFragmentManager();
                manager.popBackStack();
                transaction.replace(R.id.main_activity_fragment,new Home()).commit();

                return true;
            }

        }


        return super.onOptionsItemSelected(item);
        }


    @Override
    public void openreview() {
        Fragment fragment=new Review();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_activity_fragment,fragment).commit();

    }



    @Override
    public void getdate(int year, int month, int dayOfMonth) {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,25);
        calendar.set(Calendar.HOUR_OF_DAY,11);
        calendar.set(Calendar.AM_PM,Calendar.AM);
        Intent intent=new Intent(getApplicationContext(),Notificationforfavorite.class);


        AlarmManager alarmManager=(AlarmManager)getApplicationContext().getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(getApplicationContext(),0,intent,0);

        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        //Toast.makeText(getApplicationContext(),"favorite food recorded successfully"+calendar.getTime(),Toast.LENGTH_SHORT).show();


    }
}


