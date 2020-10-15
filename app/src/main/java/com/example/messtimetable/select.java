package com.example.messtimetable;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class select extends Fragment {
    ImageView homebutton,selectbutton,logoutbutton;

    public select() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_select, container, false);

        CalendarView calendar=view.findViewById(R.id.calenderforfavorite);
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
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_activity_fragment,fragment).commit();
               // Toast.makeText(getContext(),dayOfMonth+"/"+month+"/"+year+"the week number is :"+weekno+"\n"+"and the day of week"+dayOfWeek,Toast.LENGTH_SHORT).show();

            }
        });



        return view;
    }


}
