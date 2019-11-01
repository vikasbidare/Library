package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    String email;
    String password;
    String checkpassword;
    int flag=0;
    Boolean hasfound = false;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    DatabaseHelperClass myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDB = new DatabaseHelperClass(Login.this);

        final SharedPreferences.Editor preferences = getSharedPreferences("MyFiles",MODE_PRIVATE).edit();

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
                    Cursor tempDB = myDB.getFromLogin(email);
                    if (tempDB.getCount() == 0)
                    {
                        Toast.makeText(Login.this, "User doesn't exist", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!tempDB.moveToFirst())
                    tempDB.moveToFirst();
                    checkpassword = tempDB.getString(1);
                    if(checkpassword.equals(password)) {
                        Toast.makeText(Login.this, "Sign-in Successful", Toast.LENGTH_SHORT).show();
                        Cursor Tempdb = myDB.getFromAdmin(email);
                        Intent intentToMainActivity = new Intent(Login.this, MainActivity.class);
//                        intentToMainActivity.putExtra("UserEmail",email);
                        if (!Tempdb.moveToFirst())
                            Tempdb.moveToFirst();
//                        intentToMainActivity.putExtra("PhoneNumber",Tempdb.getString(2));
//                        intentToMainActivity.putExtra("Name",Tempdb.getString(1));

                        preferences.putString("UserEmail", email);
                        preferences.putString("PhoneNumber", Tempdb.getString(2));
                        preferences.putString("Name", Tempdb.getString(1));
                        preferences.apply();
                        startActivity(intentToMainActivity);
                    }
                    else
                    {
                        Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
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
