package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    Boolean isEmpty = false;
    String memailId , mpassword, mconfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        final EditText Username = findViewById(R.id.resetEmail);
        final EditText Password = findViewById(R.id.resetPassword);
        final EditText PasswordConfirm = findViewById(R.id.resetPasswordConfirm);


        ProgressBar signUpProgressBar = (ProgressBar) findViewById(R.id.resetPasswordProgressBar);
        signUpProgressBar.setVisibility(View.GONE);

        Button passwordresetbtn = (Button) findViewById(R.id.resetPasswordBtn);
        passwordresetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isEmpty = false;
                memailId = Username.getText().toString().trim();
                mpassword = Password.getText().toString().trim();
                mconfirmPassword = PasswordConfirm.getText().toString().trim();

                if(memailId.isEmpty())
                {
                    Username.setError("Enter Email");
                    isEmpty = true;
                }

                if(mpassword.isEmpty())
                {
                    Password.setError("Enter Password");
                    isEmpty = true;
                }

                if(mconfirmPassword.isEmpty())
                {
                    PasswordConfirm.setError("Confirm Password");
                    isEmpty = true;
                }

                if(!mconfirmPassword.equals(mpassword))
                {
                    Toast.makeText(ForgotPassword.this, "Check your password again", Toast.LENGTH_SHORT).show();
                    isEmpty = true;
                }

                if(!isEmpty) {
                    DatabaseHelperClass db = new DatabaseHelperClass(ForgotPassword.this);
                    boolean isUpdated = db.updatePassword(memailId,mpassword);

                    if(isUpdated)
                    {
                        Toast.makeText(ForgotPassword.this, "Password Updated", Toast.LENGTH_SHORT).show();
                        Intent intentToLogin = new Intent(ForgotPassword.this, Login.class);
                        startActivity(intentToLogin);
                    }
                    else
                    {
                        Toast.makeText(ForgotPassword.this, "Wrong UserID", Toast.LENGTH_SHORT).show();
                    }
                }
//                Intent intentToLogin = new Intent(ForgotPassword.this,Login.class);
//                startActivity(intentToLogin);
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
