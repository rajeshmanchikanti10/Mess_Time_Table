package com.example.messtimetable;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Review extends Fragment {
   FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
   FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
    EditText cs;
    FirebaseAuth fAuth;
    LinearLayout layout;
    TextView dispcommnt,usrname,detailsofcomments;
    public Review() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_review, container, false);

        layout=view.findViewById(R.id.commentslayout);
        Button postbutton=view.findViewById(R.id.postbutton);
        cs=view.findViewById(R.id.commentsonfood);
        dispcommnt=view.findViewById(R.id.displaycomments);
        usrname=view.findViewById(R.id.usernameofcomments);
        detailsofcomments=view.findViewById(R.id.commentsdetails);

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
        final String commnts=cs.getText().toString().trim();
        if(TextUtils.isEmpty(commnts))
        {
            cs.setError("Please Enter your comments");
        }
        else {
            /*String[] to = {"rajeshmanchikanti10@gmail.com"};
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formatted = df.format(new Date());
            String subject = "Review on   " + formatted + "  days food";
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, comments);
            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "choose email client"));*/
          // layout.setVisibility(View.VISIBLE);

            fAuth=FirebaseAuth.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formatted = df.format(new Date());
            comments cmnts=new comments(commnts,formatted);

            DatabaseReference myRef1 = FirebaseDatabase.getInstance().getReference(); //Getting root reference
            final FirebaseUser user = fAuth.getCurrentUser();
            final String userid=user.getUid();

           myRef1.child("comments").child(userid).setValue(cmnts);

           myRef1.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   Toast.makeText(getContext(),"hello world",Toast.LENGTH_SHORT).show();
                  // comments retrievedcommments=dataSnapshot.child("comments").child(userid).getValue(comments.class);
                   //user user1=dataSnapshot.child("users").child(userid).getValue(user.class);
                   layout.setVisibility(View.VISIBLE);
                   for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                       comments post = postSnapshot.getValue(comments.class);
                       user user1=postSnapshot.getValue(user.class);
                       detailsofcomments.setText(post.Date);
                       usrname.setText(user1.username);
                       dispcommnt.setText(post.comments);
                   }


               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });



        }
    }

}
