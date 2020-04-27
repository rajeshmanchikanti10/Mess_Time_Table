package com.example.messtimetable;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Review extends Fragment {
   FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
   FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
    EditText cs;

    public Review() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_review, container, false);
        Button postbutton=view.findViewById(R.id.postbutton);
        cs=view.findViewById(R.id.commentsonfood);

        postbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          sendEmail();

            }
        });

        // Inflate the layout for this fragment
        Toast.makeText(getActivity(),firebaseUser.getEmail().trim(),Toast.LENGTH_SHORT).show();


        return view;
    }
    protected void sendEmail()
    {
        String comments=cs.getText().toString().trim();
        if(TextUtils.isEmpty(comments))
        {
            cs.setError("Please Enter your comments");
        }
        else {
            String[] to = {"rajeshmanchikanti10@gmail.com"};
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formatted = df.format(new Date());
            String subject = "Review on   " + formatted + "  days food";
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, comments);
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "choose email client"));
        }
    }

}
