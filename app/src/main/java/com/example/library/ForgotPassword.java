package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    Boolean isEmpty = false;
    Boolean isEmpty2 = false;
    Boolean isEmpty3 = false;
    String memailId , mpassword, mconfirmPassword;
    String msecurityAns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final LinearLayout resetPasswordSecurityLayout = (LinearLayout) findViewById(R.id.resetPasswordSecurityLayout);
        if(resetPasswordSecurityLayout.getVisibility()== View.VISIBLE||resetPasswordSecurityLayout.getVisibility()==View.INVISIBLE)
            resetPasswordSecurityLayout.setVisibility(View.GONE);

        final LinearLayout resetPasswordMain = (LinearLayout) findViewById(R.id.resetpasswordMain);
        if(resetPasswordMain.getVisibility()== View.VISIBLE||resetPasswordMain.getVisibility()==View.INVISIBLE)
            resetPasswordMain.setVisibility(View.GONE);

        final EditText Username = findViewById(R.id.resetEmail);
        final EditText Password = findViewById(R.id.resetPassword);
        final EditText PasswordConfirm = findViewById(R.id.resetPasswordConfirm);
        final EditText securityAns = findViewById(R.id.resetPasswordsecurityanswer);
        final EditText securityQus = findViewById(R.id.resetpasswordsecurityquestion);

        securityQus.setEnabled(false);


        final Button resetpasswordCheck = (Button) findViewById(R.id.resetpasswordCheck);
        final Button passwordResetbtn = (Button) findViewById(R.id.resetPasswordBtn);
        final Button validatebtn = (Button) findViewById(R.id.resetPasswordvalidatebtn);


        resetpasswordCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isEmpty = false;
                memailId = Username.getText().toString().trim();


                if(memailId.isEmpty())
                {
                    Username.setError("Enter Email");
                    isEmpty = true;
                }

                if(!isEmpty) {
                    final DatabaseHelperClass db = new DatabaseHelperClass(ForgotPassword.this);
                    final Cursor tempDB = db.getFromLogin(memailId);
                    if (tempDB.getCount() == 0)
                    {
                        Toast.makeText(ForgotPassword.this, "User Doesnt Exist", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {

                        if(resetPasswordSecurityLayout.getVisibility()==View.GONE||resetPasswordSecurityLayout.getVisibility()==View.INVISIBLE)
                        {
                            resetPasswordSecurityLayout.setVisibility(View.VISIBLE);
                        }
                        Username.setEnabled(false);
                        resetpasswordCheck.setClickable(false);

                        if (!tempDB.moveToFirst())
                            tempDB.moveToFirst();

                        securityQus.setText(tempDB.getString(2));


                        validatebtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                isEmpty2 = false;
                                msecurityAns = securityAns.getText().toString().trim();

                                if(msecurityAns.isEmpty())
                                {
                                    securityAns.setError("Enter SecurityAnswer");
                                    isEmpty2 = true;
                                }

                                if(!isEmpty2)
                                {
                                    if(msecurityAns.equals(tempDB.getString(3)))
                                    {
                                        if(resetPasswordMain.getVisibility()==View.GONE||resetPasswordMain.getVisibility()==View.INVISIBLE)
                                        {
                                            resetPasswordMain.setVisibility(View.VISIBLE);
                                        }
                                        securityAns.setEnabled(false);
                                        validatebtn.setClickable(false);

                                        passwordResetbtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                mpassword = Password.getText().toString().trim();
                                                mconfirmPassword = PasswordConfirm.getText().toString().trim();
                                                isEmpty3 = false;
                                                if(mpassword.isEmpty())
                                                    {
                                                       Password.setError("Enter Password");
                                                       isEmpty3 = true;
                                                   }

                                                   if(mconfirmPassword.isEmpty())
                                                   {
                                                       PasswordConfirm.setError("Confirm Password");
                                                       isEmpty3 = true;
                                                   }

                                                   if(!mconfirmPassword.equals(mpassword))
                                                   {
                                                       Toast.makeText(ForgotPassword.this, "Check your password again", Toast.LENGTH_SHORT).show();
                                                       isEmpty3 = true;
                                                   }

                                                   if(!isEmpty3)
                                                   {
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
                                            }
                                        });


                                    }
                                    else {
                                        Toast.makeText(ForgotPassword.this, "Security Answer incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });


                    }


                }

            }
        });
    }
}
