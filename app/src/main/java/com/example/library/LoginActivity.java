package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signinbtn = (Button)findViewById(R.id.login);
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signinintent = new Intent(LoginActivity.this,sign_up.class);
                startActivity(signinintent);
            }
        });

        TextView forgotpswd = (TextView) findViewById(R.id.forgotpassword);
        forgotpswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Reset Done", Toast.LENGTH_SHORT).show();
            }
        });

        TextView signup = (TextView) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signuppageintent = new Intent(LoginActivity.this,sign_up.class);
                startActivity(signuppageintent);
            }
        });
    }
}