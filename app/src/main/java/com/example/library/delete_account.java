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

public class delete_account extends AppCompatActivity {

    Boolean isEmpty = false;
    Boolean isEmpty2 = false;
    Boolean isEmpty3 = false;
    Boolean isDeleted;
    String memailId , mpassword, mconfirmPassword;
    String msecurityAns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        final LinearLayout deleteaccountSecurityLayout = (LinearLayout) findViewById(R.id.deleteaccountlayout);
        if(deleteaccountSecurityLayout.getVisibility()== View.VISIBLE||deleteaccountSecurityLayout.getVisibility()==View.INVISIBLE)
            deleteaccountSecurityLayout.setVisibility(View.GONE);

        final EditText UserEmail = findViewById(R.id.deleteaccountemail);
        final EditText securityQus = findViewById(R.id.deleteaccountsecurityquestion);
        final EditText securityAns = findViewById(R.id.deleteaccountsecurityanswer);

        securityQus.setEnabled(false);


        final Button deletecheck = (Button) findViewById(R.id.deleteaccountcheckbtn);
        final Button deletebtn = (Button) findViewById(R.id.deleteaccountbtn);


        deletecheck.setOnClickListener(new View.OnClickListener() {
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
                    final DatabaseHelperClass db = new DatabaseHelperClass(delete_account.this);
                    final Cursor tempDB = db.getFromLogin(memailId);
                    if (tempDB.getCount() == 0)
                    {
                        Toast.makeText(delete_account.this, "User Doesn't Exist", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {

                        if(deleteaccountSecurityLayout.getVisibility()==View.GONE||deleteaccountSecurityLayout.getVisibility()==View.INVISIBLE)
                        {
                            deleteaccountSecurityLayout.setVisibility(View.VISIBLE);
                        }
                        UserEmail.setEnabled(false);
                        deletecheck.setClickable(false);

                        if (!tempDB.moveToFirst())
                            tempDB.moveToFirst();

                        securityQus.setText(tempDB.getString(2));


                        deletebtn.setOnClickListener(new View.OnClickListener() {
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
                                        isDeleted = db.deletefromAdmin(memailId);
                                        if(isDeleted)
                                        {
                                            Toast.makeText(delete_account.this,"Account deleted. We'll miss you:)",Toast.LENGTH_LONG).show();
                                            Intent intenttoLogin = new Intent(delete_account.this,Login.class);
                                            startActivity(intenttoLogin);
                                        }
                                        else
                                            Toast.makeText(delete_account.this,"Account couldn't be deleted.Sorry.",Toast.LENGTH_LONG).show();

                                    }
                                    else {
                                        Toast.makeText(delete_account.this, "Security Answer incorrect", Toast.LENGTH_SHORT).show();
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
