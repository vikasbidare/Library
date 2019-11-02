package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class edit_account_info extends AppCompatActivity {

    String newUserName, newUserNumber , newUserPassword , newUserPasswordConfirm,userid,oldname,oldphone;
    Boolean nameempty = false;
    DatabaseHelperClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_info);

        final SharedPreferences.Editor settingsEditor = getSharedPreferences("MyFiles",MODE_PRIVATE).edit();
        Button Editusernamebtn = (Button) findViewById(R.id.editnamebtn);
        Button Editnumberbtn = (Button) findViewById(R.id.editnumberbtn);
        Button Editpasswordbtn = (Button) findViewById(R.id.editpasswordbtn);
        Button finishbtn = (Button) findViewById(R.id.finishbtn);

        Intent fromintent = getIntent();
          userid = fromintent.getStringExtra("UserID");
         oldname = fromintent.getStringExtra("UserName");
          oldphone = fromintent.getStringExtra("UserPhone");

        mydb = new DatabaseHelperClass(edit_account_info.this);

        Editusernamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(edit_account_info.this);

                final TextView editusername = new TextView(edit_account_info.this);
                editusername.setText("New UserName");
                editusername.setTextAppearance(edit_account_info.this, android.R.style.TextAppearance_Large);
                editusername.setTextColor(getResources().getColor(R.color.blue300));
                editusername.setPadding(20,20,20,20);
                editusername.setGravity(Gravity.CENTER);
                editusername.setTextSize(20);
                editusername.setTypeface(editusername.getTypeface(), Typeface.BOLD);
                editusername.setWidth(-1);
                builder.setCustomTitle(editusername);

                LinearLayout container = new LinearLayout(edit_account_info.this);
                container.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(40,20,40,20);
                final EditText input = new EditText(edit_account_info.this);
                input.setLayoutParams(lp);
                input.setGravity(android.view.Gravity.TOP|android.view.Gravity.LEFT);
                input.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES|InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                input.setLines(1);
                input.setMaxLines(1);
                container.addView(input, lp);

                builder.setView(container);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         newUserName = input.getText().toString().trim();
                         nameempty = false;
                         if(newUserName.isEmpty())
                         {
                             nameempty = true;
                         }

                         if(!nameempty)
                         {
                             boolean isUpdated = mydb.updateAdminTable(newUserName,userid,oldphone);

                             if(isUpdated)
                             {
                                 Toast.makeText(edit_account_info.this, "UserName Updated", Toast.LENGTH_SHORT).show();
                                 oldname = newUserName;
                                 settingsEditor.putString("Name",newUserName);
                                 settingsEditor.apply();
                                 dialog.cancel();

                             }
                             else
                             {
                                 Toast.makeText(edit_account_info.this, "Try Again!!!", Toast.LENGTH_SHORT).show();
                             }
                         }
                         else
                         {
                             Toast.makeText(edit_account_info.this, "Field Cant be Empty\n Try Again!!!", Toast.LENGTH_SHORT).show();
                         }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }
}
