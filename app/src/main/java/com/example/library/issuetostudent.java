package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class issuetostudent extends AppCompatActivity {

    String bid,sid,issuedate;
    Boolean isEmpty = true;
    DatabaseHelperClass myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuetostudent);

        final EditText editbid = (EditText) findViewById(R.id.studentissuebookid);
        final EditText editsid = (EditText) findViewById(R.id.studentissuestudentid);
        final EditText editdate = (EditText) findViewById(R.id.studentissuedate);


        myDB = new DatabaseHelperClass(issuetostudent.this);

        Button submitbtn = (Button) findViewById(R.id.studentissuebookbtn);
        Button clearbtn = (Button) findViewById(R.id.studentissuecleardata);

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editbid.setText(" ");
                editsid.setText(" ");
                editdate.setText(" ");

            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bid = editbid.getText().toString().trim();
                sid = editsid.getText().toString().trim();
                issuedate = editdate.getText().toString().trim();

                isEmpty = false;

                if(bid.isEmpty())
                {
                    editbid.setError("Enter Book-ID");
                    isEmpty = true;
                }

                if(sid.isEmpty())
                {
                    editsid.setError("Enter Student-ID");
                    isEmpty = true;
                }

                if(issuedate.isEmpty())
                {
                    editdate.setError("Enter IssueDate");
                    isEmpty = true;
                }
                if(!isEmpty)
                {
                    Cursor tempdb = myDB.getAboutABook(bid);
                    if(tempdb.getCount() == 0)
                    {
                        Toast.makeText(issuetostudent.this, "Wrong Book-ID", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        if (!tempdb.moveToFirst())
                            tempdb.moveToFirst();
                        Cursor studentDB = myDB.getAboutAStudent(sid);
                        if (studentDB.getCount() == 0) {
                            Toast.makeText(issuetostudent.this, "Wrong Student-ID", Toast.LENGTH_SHORT).show();
                        } else
                            {
                            String isIssued = tempdb.getString(6);
                            if (isIssued.equals("YES")) {
                                Toast.makeText(issuetostudent.this, "Sorry!!\n Book Already Issued", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                Boolean isInserted = myDB.insertIntoStudentHistory(bid, sid, issuedate, null, "0.00");
                                Boolean isUpdated = myDB.updateIssuedOfBook(tempdb.getString(1), tempdb.getString(2), bid,
                                        tempdb.getString(5), tempdb.getString(3), tempdb.getString(4),
                                        tempdb.getString(7), tempdb.getString(8), "YES");
                                if (isInserted && isUpdated) {
                                    Toast.makeText(issuetostudent.this, "Book Issued:)\nThanks", Toast.LENGTH_SHORT).show();
                                    Intent intentToMainActivity = new Intent(issuetostudent.this, MainActivity.class);
                                    startActivity(intentToMainActivity);
                                } else {
                                    Toast.makeText(issuetostudent.this, "Error Occurred,\nTry Again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }


            }
        });
    }
}
