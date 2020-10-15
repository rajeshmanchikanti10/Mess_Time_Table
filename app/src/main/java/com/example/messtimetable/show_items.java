package com.example.messtimetable;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class show_items extends Fragment {

int weekno,day_of_the_week;

    public static String [][][]str={{{"Masala Dosa,sagu,Chutney,Tea,Coffee,Milk","Green Peass pulao,Chapati,Raita,Black Channa,White Rice,Pickle","Noddles,Tea Coffee","Mini-Feast"}
            ,{"Idli-Vada,Sambar,Chutney,Milk,Tea,Coffee","Puliogare,Mix veg cuury,Chapati,soppu-saaru,White Rice,Butter Milk,Papad,Pickle","Samosa,Tea,Coffee","Fulka,Dall-Tadaka,Methi-Rice,Veg-palya,White Rice,Rassam,Curd,Pickle"}
            ,{"Puri Sagu,Milk,Tea,Coffee","Bhindi Masala,Chapati,Coconut Rice,Yellow Dall,White Rice,Papad,Pickle","Bhel Puri,Tea,Coffee","Green Peas Masala,Fulka,Bombay dall,Rice,Sambar,Curd Rice,Fruit Salad,Pickle"}
            ,{"Aloo Paratha,Curd/Picle,Milk,Tea,Coffee","Veg Pulao,Chana Masala,Chapati,White Rice,Green Sambar,Butter Milk,Pickle","Bhajji,Tea,Coffee","Lachha Paratha,Egg curry,Panner curry,White Rice,Rassam,Pickle"}
            ,{"Khara-Bhath,Kesari-Bhath,Chutney,Milk,Tea,Coffee","Beans Capsicum Masala,Chapati,Vangi Bhath,White Rice,Rassam,Papad,Pickle","Mixture,Tea,Coffee","Chhole-Bhature,Rice,Rassam,Sweet Lassi,Salad,Pickle"}
            ,{"Pav Bhajji,Milk,Tea,Coffee","Chapati,Veg Curry,Bisibele Bhath,White Rice,Sambar,Butter Milk,Pickle","Pasta,Tea,Coffee","Palak Panner,Fulka,Dall fry,White Rice,Rassam,Curd,Pickle"}
            ,{"Plain Dosa,Sambar,Chutney,Milk,Tea,Coffee","Rajma-Masala,Chapati Pudina Rice,White Rice,Sambar,Butter Milk,Pickle","Biscuits,Tea,Coffee","Aloo-Paratha,Curd,Dalla,White Rice,Green Sambar,sweet,Pickle"}}
            ,{{"Idli,Vada,Chutney,Milk,Tea,Coffee","Aloo Parmal,Fulka,Dum-Jeera Rice,Ghiya-Dall,White Rice,Sambar,Pickle,Papad","Pakoda,Tea,Coffee","Veg Biryani,Rassam,Chicken Biryani,Raita,White Rice,Fruit Custard,Pickle"}
            ,{"Puri Sagu,Milk,Tea,Coffee","Palak Rice,Chapati,Methu Dal,Mushroom,Green-Peas Masala,White Rice,Rassam,Pickle","Semiya,Tea,Coffee","Rice,Bombay Dall,Plain Paratha,Mix Veg Kurma,Sambar,Curd,Pickle"}
            ,{"Onion Dosa,Chutney,Milk,Tea,Coffee","Makai Nisha,Chapathi,Ghee Rice,Butter Milk,Pickle","Rusk,Tea,Coffee","Veg Fried Rice,Gobi Manchurian,Fulka,Lobiya Dall,White Rice,Rassam,Juice,Pickle"}
            ,{"Sattu Paratha,Sagu,Milk,Tea,Coffee","Chana Masala,Chapati,VEG-Biryani,Raita,White Rice,Rassam,Pickle","Mixture,Tea,Coffee","Egg-Chilly,Panner-Chilly,Kerala Paratha,Rice,Rassam,Pickle"}
            ,{"Shavige Bhath,Chutney,Milk,Tea,Coffee","Pudina Rice,Gobi-Capsicum Masala,Chapati,White Rice,Sambar,Dall,Butter Milk,Pickle","Bread Jam,Tea,Coffee","Pongal,Cabbage-Palya,Fulka,Channa Dall,Rice,Rassam,Curd,Papad,Pickle"}
            ,{"Pav Bhaji,Milk,Tea,Coffee","Bhindi-Masala,Chapati,Methi-Rice,Dall,Sambar,White Rice,Papad,Pickle","Pani-Puri,Tea,Coffee","Lachha Paratha,Kofta-Curry,Tomato Rice,Dall,White Rice,Rassam,Curd,Pickle"}
            ,{"Idli,Voda,Sambar,Milk,Tea,Coffee","Bread Rice,Dall,Chapati,Green Sambar,Palya,White Rice,Butter Milk,Pickle","Biscuits,Tea,Coffee","Palak Puri,Chana-Masala,Jeera Rice,Rassam,White Rice,Salad,Sweet,Pickle"}}
            ,{{"Masala Dosa,Chutney,Milk,Tea,Coffee","Rice Rasam,Puliogare,Fulka,Rajma Masala,Butter Milk","Poha,Tea,Coffee","Mini Feast"}
            ,{"Puri Sagu,Pudhina Rice,Milk,Tea,Coffee","Rice,Sambar,Kashmiri Dum Rice,Chapati,Mushroom Green Peas","Bonda,Tea/Coffee","Butter Fulka,Veg Kadhai,Dal Tadka,Rice,Rasam,Curd"}
            ,{"Idly-Vada,Sambar,Chutney,Milk,Tea,Coffee","Ghee-Rice,Dal Fry,Chapati,Aloo Baingan,Rice,Green Sambhar","Bheel Puri,Tea,Coffee","Dal Makhani,Plain Paratha,Veg Fried Rice,Rasam,Curd"}
            ,{"Dal Palak,Paratha,Sagu,Milk,Tea,Coffee","Lemon Rice,Puri,Veg Kurma,Butter Milk,Rice,Rasam","Bread Jam,Tea,Coffee","Rice,Rasam,Paratha,Egg Curry,Panner Curry"}
            ,{"Chau chau Bhath,Lemon Rice,Milk,Tea,Coffee","Chana Masala,Chapati,Vangi Bhath,Sambhar,Rice,Butter Milk","Mixture,Tea,Coffee","Veg Hyderabad Biryani,Raita,Fulka,Palak Dal,Sabji,Rice,Rasam"}
            ,{"Chole Bhature,Milk,Tea,Coffee","Rajma Masala,Dum Ghee Rice,Chapati,Rice,Sambar","Samosa,Tea,Coffee","Pudhina Rice,Fulka,Matar Paneer,Curd,Rice,Rasam"},{"Set Dosa,Sagu,Chutney,Milk,Tea,Coffee","Puliogare,Aloo Bhindi Dry,Fulka,Dal Mughalai,Rice,Rasam,Papad","Biscuits,Tea,Coffee","Rice,Sambhar,Aloo Paratha,Curd,Sweet"}}
            ,{{"Idly-Vada,Sambhar,Chutney,Milk,Tea,Coffee","Chana Masala,Fulka,Rice,Green Sambhar,Ghee Rice,Butter Milk,Papad","Pasta,Tea,Coffee","Butter Chicken,Shahi,Panner,Paratha,Salad,Rice,Rasam"}
            ,{"Puri Sagu,Milk,Tea,Coffee","Kashmiri Dum Rice,Makai Nisha,ChapatI,Dal Fry,Rice,Rasam","Bread Pakoda,Tea,Coffee","Chinese Pakoda,Fulka,Seasonal Fried Dry,Rasam,Rice,Juice"}
            ,{"Plain Dosa,Sambhar,Chutney,Milk,Tea,Coffee","Veg Pulao,Raita,Rajma Masala,Chapati,Sambhar,Rice","Noodles,Tea,Coffee","Rice,Fulka,Aloo Methi Dry,Bombay Dal,Curd Rice,Sambhar, Papad"}
            ,{"Aloo Paratha,Curd,Milk,Tea,Coffee","Mix Veg Curry,Chapati,Tomato Rice,Sambhar,Butter Milk","Semiya,Tea,Coffee","Rice,Rasam,Paratha,Egg Curry,Paneer Curry"}
            ,{"Pav Bhaji,Puliogore,Milk,Tea,Coffee","Veg Palya,Fulka,Dal kolapuri,Bisi bele bath,Rice,Rasam","Mirichi Bhajji,Tea,Coffee","Rajma Masala,Plain Paratha,pulao,Rice,Sambhar,Curd"}
            ,{"Bread Omelette,Bread Jam,Rice Bhath,Chutney,Milk,Tea,Coffee","Chana Masala,Chapati,Palak Rice,Moong Dal,Sambhar,Rice,Butter Milk","Masala Vda,Tea,Coffee","Veg Kurma,Puri,Curd,Rice,Rasam"}
            ,{"Veg Roll,Lemon Rice,Milk,Tea,Coffee","Matar Paneer,Fulka,Veg Biryani,Raita,Rice,Rasam","Biscuits,Tea,Coffee","BabyCorn Pulao,Raita,GreenPeas Masala,Fulka,Rice,Dal,Rasam"}}};
TextView bf,lh,ss,dr;
    public show_items() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
    ArrayList<ArrayList<ArrayList<ArrayList<String>>>> items=new ArrayList<ArrayList<ArrayList<ArrayList<String>>>>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_show_items, container, false);
        // Inflate the layout for this fragment
        weekno=getArguments().getInt("weekno",1);
        day_of_the_week=getArguments().getInt("dayofweek",1);
        bf=view.findViewById(R.id.breakfast);
        lh=view.findViewById(R.id.lunch);
        ss=view.findViewById(R.id.snacks);
        dr=view.findViewById(R.id.dinner);
        bf.setText(str[weekno-1][day_of_the_week-1][0]);
        lh.setText(str[weekno-1][day_of_the_week-1][1]);
        ss.setText(str[weekno-1][day_of_the_week-1][2]);
        dr.setText(str[weekno-1][day_of_the_week-1][3]);
        return view;
    }
    protected void showitems(){

        Toast.makeText(getContext(),"the week number is :"+weekno+"\n"+"and the day of week"+day_of_the_week,Toast.LENGTH_SHORT).show();
    }


}

