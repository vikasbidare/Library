package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class
sign_up extends AppCompatActivity {

    Boolean isEmpty = true;
    String mname,memailId,mpassword,mconfirmPassword,mnumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ProgressBar signUpProgressBar = (ProgressBar) findViewById(R.id.signUpProgressBar);
        signUpProgressBar.setVisibility(View.GONE);

//        add progressbar visiblity in submit button under successful addition


        final EditText name = (EditText)findViewById(R.id.signUpName);
        final EditText emailID = (EditText)findViewById(R.id.signUpEmail);
        final EditText number = (EditText)findViewById(R.id.signUpMobile);
        final EditText password = (EditText) findViewById(R.id.signUpPassword);
        final EditText confirmPassword = (EditText) findViewById(R.id.signUpPasswordConfirm);

        Button signUpButton = (Button)findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mname = name.getText().toString();
                memailId = emailID.getText().toString();
                mnumber = number.getText().toString();
                mpassword = password.getText().toString();
                mconfirmPassword = confirmPassword.getText().toString();

                if(mname.isEmpty())
                {
                    name.setError("Enter Name");
                    isEmpty = true;
                }

                if(mnumber.isEmpty())
                {
                    number.setError("Enter Number");
                    isEmpty = true;
                }

                if(memailId.isEmpty())
                {
                    emailID.setError("Enter Email");
                    isEmpty = true;
                }

                if(mpassword.isEmpty())
                {
                    password.setError("Enter Pasword");
                    isEmpty = true;
                }

                if(mconfirmPassword.isEmpty())
                {
                    confirmPassword.setError("Confirm Password");
                    isEmpty = true;
                }
//                Intent intentToLogin = new Intent(sign_up.this,Login.class);
//                startActivity(intentToLogin);

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        ProgressBar signUpProgressBar = (ProgressBar) findViewById(R.id.signUpProgressBar);

        if(signUpProgressBar.getVisibility() == View.VISIBLE ||signUpProgressBar.getVisibility() == View.INVISIBLE)
            signUpProgressBar.setVisibility(View.GONE);

        isEmpty = true;
    }
}
