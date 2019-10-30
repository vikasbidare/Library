package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class
sign_up extends AppCompatActivity {

    Boolean isEmpty = false;
    String mname,memailId,mpassword,mconfirmPassword,mnumber;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";



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
                isEmpty = false;
                mname = name.getText().toString().trim();
                memailId = emailID.getText().toString().trim();
                mnumber = number.getText().toString().trim();
                mpassword = password.getText().toString().trim();
                mconfirmPassword = confirmPassword.getText().toString().trim();

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
                if(!mnumber.matches(MobilePattern))
                {
                    number.setError("Enter Valid Phone Number");
                    isEmpty = true;
                }

                if(memailId.isEmpty())
                {
                    emailID.setError("Enter Email");
                    isEmpty = true;
                }

                if (!memailId.matches(emailPattern)){
                    emailID.setError("Enter valid email address");
                    isEmpty = true;
                }

                if(mpassword.isEmpty())
                {
                    password.setError("Enter Password");
                    isEmpty = true;
                }

                if(mconfirmPassword.isEmpty())
                {
                    confirmPassword.setError("Confirm Password");
                    isEmpty = true;
                }

                if(!mconfirmPassword.equals(mpassword))
                {
                    Toast.makeText(sign_up.this, "Check your password again", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                }

                if(!isEmpty) {
                    DatabaseHelperClass db = new DatabaseHelperClass(sign_up.this);
                    boolean isInserted = db.insertIntoSignUp(mname,memailId,mnumber,mpassword);

                    if(isInserted)
                    {
                        Toast.makeText(sign_up.this, "Sign-Up completed", Toast.LENGTH_SHORT).show();
                        Intent intentToLogin = new Intent(sign_up.this, Login.class);
                        startActivity(intentToLogin);
                    }
                    else
                    {
                        Toast.makeText(sign_up.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
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
