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

public class conformation extends AppCompatActivity {

    Boolean isEmpty = false;
    Boolean isEmpty2 = false;
    Boolean isEmpty3 = false;
    String memailId , mpassword, mconfirmPassword;
    String msecurityAns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conformation);

        final LinearLayout confirmSecurityLayout = (LinearLayout) findViewById(R.id.ConformSecurityLayout);
        if(confirmSecurityLayout.getVisibility()== View.VISIBLE||confirmSecurityLayout.getVisibility()==View.INVISIBLE)
            confirmSecurityLayout.setVisibility(View.GONE);

        final EditText UserEmail = findViewById(R.id.ConfirmEmail);
        final EditText securityQus = findViewById(R.id.conformsecurityquestion);
        final EditText securityAns = findViewById(R.id.conformsecurityanswer);

        securityQus.setEnabled(false);


        final Button confirmCheck = (Button) findViewById(R.id.conformCheck);
        final Button validatebtn = (Button) findViewById(R.id.conformvalidatebtn);


        confirmCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isEmpty = false;
                memailId = UserEmail.getText().toString().trim();


                if(memailId.isEmpty())
                {
                    UserEmail.setError("Enter Email");
                    isEmpty = true;
                }

                if(!isEmpty) {
                    final DatabaseHelperClass db = new DatabaseHelperClass(conformation.this);
                    final Cursor tempDB = db.getFromLogin(memailId);
                    if (tempDB.getCount() == 0)
                    {
                        Toast.makeText(conformation.this, "User Doesnt Exist", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {

                        if(confirmSecurityLayout.getVisibility()==View.GONE||confirmSecurityLayout.getVisibility()==View.INVISIBLE)
                        {
                            confirmSecurityLayout.setVisibility(View.VISIBLE);
                        }
                        UserEmail.setEnabled(false);
                        confirmCheck.setClickable(false);

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
                                        Cursor admin = db.getFromAdmin(memailId);

                                        if(!admin.moveToFirst())
                                            admin.moveToFirst();

                                        Toast.makeText(conformation.this, "Welcome "+admin.getString(1), Toast.LENGTH_SHORT).show();
                                        Intent intentToEditInfo = new Intent(conformation.this,edit_account_info.class);
                                        intentToEditInfo.putExtra("UserID",memailId);
                                        intentToEditInfo.putExtra("UserName",admin.getString(1));
                                        intentToEditInfo.putExtra("UserPhone",admin.getString(2));
                                        startActivity(intentToEditInfo);

                                    }
                                    else {
                                        Toast.makeText(conformation.this, "Security Answer incorrect", Toast.LENGTH_SHORT).show();
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
