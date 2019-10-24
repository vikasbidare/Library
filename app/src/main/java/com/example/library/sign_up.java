package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class
sign_up extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ProgressBar signUpProgressBar = (ProgressBar) findViewById(R.id.signUpProgressBar);
        signUpProgressBar.setVisibility(View.GONE);

//        add progressbar visiblity in submit button under successful addition

        Button signUpButton = (Button)findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToLogin = new Intent(sign_up.this,Login.class);
                startActivity(intentToLogin);

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        ProgressBar signUpProgressBar = (ProgressBar) findViewById(R.id.signUpProgressBar);

        if(signUpProgressBar.getVisibility() == View.VISIBLE ||signUpProgressBar.getVisibility() == View.INVISIBLE)
            signUpProgressBar.setVisibility(View.GONE);
    }
}
