package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ProgressBar signUpProgressBar = (ProgressBar) findViewById(R.id.resetPasswordProgressBar);
        signUpProgressBar.setVisibility(View.GONE);

        Button passwordresetbtn = (Button) findViewById(R.id.resetPasswordBtn);
        passwordresetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToLogin = new Intent(ForgotPassword.this,Login.class);
                startActivity(intentToLogin);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        ProgressBar signUpProgressBar = (ProgressBar) findViewById(R.id.resetPasswordProgressBar);

        if(signUpProgressBar.getVisibility() == View.VISIBLE ||signUpProgressBar.getVisibility() == View.INVISIBLE)
            signUpProgressBar.setVisibility(View.GONE);
    }
}
