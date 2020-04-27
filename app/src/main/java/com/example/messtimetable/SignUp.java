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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    Button onregister;
    EditText username;
    EditText password,cpassword;
    FirebaseAuth fAuth;
    EditText email;
    ProgressBar progressbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        onregister=findViewById(R.id.register_submit);
        username=findViewById(R.id.register_username);
        password=findViewById(R.id.register_password);
        cpassword=findViewById(R.id.register_confirm_password);
        progressbar=findViewById(R.id.progbar);
        email=findViewById(R.id.gmailretrivedfromfirebase);
        fAuth=FirebaseAuth.getInstance();

        onregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrname=username.getText().toString().trim();
                String psswd=password.getText().toString().trim();
                String eml=email.getText().toString().trim();
                String cpasswd=cpassword.getText().toString().trim();
                if(TextUtils.isEmpty(usrname))
                {

                    username.setError("User name required!");
                    return;
                }
                if(!usrname.startsWith("PES"))
                {
                    username.setError("your username starts with PES!");
                    return;                }
                if(TextUtils.isEmpty(psswd))
                {
                    password.setError("This filed is required!");
                    return;

                }
                if(TextUtils.isEmpty(eml))
                {
                    email.setError("This is required!");
                    return;
                }
                if(psswd.length()<6)
                {
                    password.setError("minimum 6 characters required!");
                    return;
                }
                if(!cpasswd.matches(psswd))
                {
                    cpassword.setError("password not matching!");
                }
                if(fAuth.getCurrentUser()!=null)
                {   Toast.makeText(SignUp.this,"User already Exists!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUp.this,Login.class));
                }
                fAuth.createUserWithEmailAndPassword(eml,psswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {   progressbar.setVisibility(View.VISIBLE);
                            Toast.makeText(SignUp.this,"registerd successfully!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this,Login.class));
                        }
                        else
                        {
                            progressbar.setVisibility(View.INVISIBLE);
                         Toast.makeText(SignUp.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
