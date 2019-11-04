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

public class issuetofaculty extends AppCompatActivity {

    String bid,fid,returndate;
    Boolean isEmpty = true;
    DatabaseHelperClass myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuetofaculty);


        final EditText editbid = (EditText) findViewById(R.id.facultyissuebookid);
        final EditText editfid = (EditText) findViewById(R.id.facultyissueid);
        final EditText editdate = (EditText) findViewById(R.id.facultyissuedate);

        myDB = new DatabaseHelperClass(issuetofaculty.this);

        Button submitbtn = (Button) findViewById(R.id.facultyissuesubmitdata);
        Button clearbtn = (Button) findViewById(R.id.facultyissuecleardata);

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editbid.setText(" ");
                editfid.setText(" ");
                editdate.setText(" ");

            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bid = editbid.getText().toString().trim();
                fid = editfid.getText().toString().trim();
                returndate = editdate.getText().toString().trim();

                isEmpty = false;

                if(bid.isEmpty())
                {
                    editbid.setError("Enter Book-ID");
                    isEmpty = true;
                }

                if(fid.isEmpty())
                {
                    editfid.setError("Enter Faculty-ID");
                    isEmpty = true;
                }

                if(returndate.isEmpty())
                {
                    editdate.setError("Enter ReturnDate");
                    isEmpty = true;
                }

                if(!isEmpty) {
                    Cursor tempdb = myDB.getAboutABook(bid);
                    if (tempdb.getCount() == 0) {
                        Toast.makeText(issuetofaculty.this, "Wrong Book-ID", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        if (!tempdb.moveToFirst())
                            tempdb.moveToFirst();

                        Cursor facultyDB = myDB.getAboutAFaculty(fid);
                        if (facultyDB.getCount() == 0) {
                            Toast.makeText(issuetofaculty.this, "Wrong Faculty-ID", Toast.LENGTH_SHORT).show();
                        } else {
                            String isIssued = tempdb.getString(6);
                            if (isIssued.equals("YES")) {
                                Toast.makeText(issuetofaculty.this, "Sorry!!\n Book Already Issued", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                Boolean isInserted = myDB.insertIntoFacultyHistory(bid, fid, returndate, null, "0.00");
                                Boolean isUpdated = myDB.updateIssuedOfBook(tempdb.getString(1), tempdb.getString(2), bid,
                                        tempdb.getString(5), tempdb.getString(3), tempdb.getString(4),
                                        tempdb.getString(7), tempdb.getString(8), "YES");
                                if (isInserted && isUpdated) {
                                    Toast.makeText(issuetofaculty.this, "Book Issued:)\nThanks", Toast.LENGTH_SHORT).show();
                                    Intent intentToMainActivity = new Intent(issuetofaculty.this, MainActivity.class);
                                    startActivity(intentToMainActivity);
                                } else {
                                    Toast.makeText(issuetofaculty.this, "Error Occurred,\nTry Again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }

            }
        });
    }
}
