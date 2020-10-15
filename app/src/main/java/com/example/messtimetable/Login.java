package com.example.messtimetable;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button sigup;
    Button lgn;
    EditText eml;
    EditText passwd;FirebaseAuth fauth;
    ProgressBar login_progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        lgn=findViewById(R.id.login);

        eml=findViewById(R.id.gmail_login_page);
        passwd=findViewById(R.id.password_login);
        login_progressbar=findViewById(R.id.login_progress_bar);
       fauth =FirebaseAuth.getInstance();
    sigup=findViewById(R.id.signup_btn);
    sigup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Login.this,com.example.messtimetable.SignUp.class);
            startActivity(intent);
        }
    });
    lgn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           loginUserAccount();
           login_progressbar.setVisibility(View.VISIBLE);

        }
    });
    }
    private  void loginUserAccount(){
        String email=eml.getText().toString().trim();
        String password=passwd.getText().toString().trim();
        if(TextUtils.isEmpty(email))
        {
            eml.setError("This field is required!");
            login_progressbar.setVisibility(View.INVISIBLE);
            return;



        }


        if(TextUtils.isEmpty(password))
        {
            passwd.setError("This field is required!");
            login_progressbar.setVisibility(View.INVISIBLE);
            return;
        }
        fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                login_progressbar.setVisibility(View.INVISIBLE);

                if(task.isSuccessful())
                {

                    Toast.makeText(getApplicationContext(),"Login Successful!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);
                    finish();
                }
                else
                { login_progressbar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                   // Toast.makeText(getApplicationContext(),"Login Failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
