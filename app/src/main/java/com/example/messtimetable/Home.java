package com.example.messtimetable;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

TextView currentdaymeal;
    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        currentdaymeal=view.findViewById(R.id.currentdaysmeal);
        Calendar calendar=Calendar.getInstance();
        int weekno=calendar.get(Calendar.WEEK_OF_YEAR);
        if(weekno%4==0)
            weekno=4;
        else
            weekno%=4;

        int dayOftheWeek = calendar.get(Calendar.DAY_OF_WEEK);
        Log.i("day of the week","hello");
        currentdaymeal.setMovementMethod(new ScrollingMovementMethod());
       currentdaymeal.setText("BreakFast:\n\n"+show_items.str[weekno-1][dayOftheWeek-1][0]+"\n\nLunch:\n\n"+show_items.str[weekno-1][dayOftheWeek-1][1]+"\n\nSnacks:\n\n"+show_items.str[weekno-1][dayOftheWeek-1][2]+"\n\nDinner\n\n"+show_items.str[weekno-1][dayOftheWeek-1][3]);
    Button button=view.findViewById(R.id.comment);

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            comment cc;
            cc=(comment)getActivity();
            cc.openreview();

        }
    });





        return view;
    }

}
