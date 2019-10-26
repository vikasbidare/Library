package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    String email;
    String password;
    int flag=0;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView signUp = (TextView) findViewById(R.id.signUp);
        final EditText Username = findViewById(R.id.username);
        final EditText Password = findViewById(R.id.password);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToSignUp = new Intent(Login.this,sign_up.class);
                startActivity(intentToSignUp);
            }
        });

        Button loginbtn = (Button) findViewById(R.id.loginButton);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                email = Username.getText().toString().trim();
                password = Password.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Username.setError("Enter Username");
                    flag = 1;
                }
                if (!email.matches(emailPattern)){
                    Username.setError("Enter valid email address");
                    flag = 1;
                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Enter Password");
                    flag = 1;
                }
                if(flag == 0)
                {
                    Intent intentToMainActivity = new Intent(Login.this,MainActivity.class);
                    startActivity(intentToMainActivity);
                }
            }
        });

        TextView forgotPassword = (TextView) findViewById(R.id.loginforgotpassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToForgotPassword = new Intent(Login.this,ForgotPassword.class);
                startActivity(intentToForgotPassword);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
