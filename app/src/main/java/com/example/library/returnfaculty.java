package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class returnfaculty extends AppCompatActivity {

    DatabaseHelperClass myDB;
    String bookid, facultyid , returndate ,fineamt;
    Boolean isEmpty = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returnfaculty);


        final EditText FacultyID,BookID,ReturnDate,FineAmt;
        final Button Process , Cancel , Submit;

        FacultyID = (EditText) findViewById(R.id.facultyreturnfacultyid);
        BookID = (EditText) findViewById(R.id.facultyReturnbookid);
        ReturnDate = (EditText) findViewById(R.id.facultyreturdate);
        FineAmt = (EditText) findViewById(R.id.facultyreturnFineAmt);

        Process = (Button) findViewById(R.id.facultyProcessbtn);
        Cancel = (Button) findViewById(R.id.facultyReturnCancelBtn);
        Submit = (Button) findViewById(R.id.facultyReturnSubmitBtn);

        myDB = new DatabaseHelperClass(returnfaculty.this);

        final LinearLayout facultyFineLayout = (LinearLayout) findViewById(R.id.returnFacultyLayout);
        if(facultyFineLayout.getVisibility()== View.VISIBLE||facultyFineLayout.getVisibility()==View.INVISIBLE)
            facultyFineLayout.setVisibility(View.GONE);

        FineAmt.setEnabled(false);
        FineAmt.setText("0.00");

        Process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bookid = BookID.getText().toString().trim();
                facultyid = FacultyID.getText().toString().trim();
                returndate = ReturnDate.getText().toString().trim();

                isEmpty = false;

                if(bookid.isEmpty())
                {
                    BookID.setError("Enter Book-ID");
                    isEmpty = true;
                }

                if(facultyid.isEmpty())
                {
                    FacultyID.setError("Enter Faculty-ID");
                    isEmpty = true;
                }

                if(returndate.isEmpty())
                {
                    ReturnDate.setError("Enter ReturnDate");
                    isEmpty = true;
                }

                if(!isEmpty) {

                    final Cursor tempdb = myDB.getAboutABook(bookid);
                    if (tempdb.getCount() == 0) {
                        Toast.makeText(returnfaculty.this, "Wrong Book-ID", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        if (!tempdb.moveToFirst())
                            tempdb.moveToFirst();

                        Cursor facultyDB = myDB.getAboutAFaculty(facultyid);
                        if (facultyDB.getCount() == 0) {
                            Toast.makeText(returnfaculty.this, "Wrong Faculty-ID", Toast.LENGTH_SHORT).show();
                        } else {
                            String isIssued = tempdb.getString(6);
                            if (isIssued.equals("NO")) {
                                Toast.makeText(returnfaculty.this, "Sorry!!\n Book Already Returned", Toast.LENGTH_SHORT).show();
                                return;
                            } else {

                                final Cursor historyDB = myDB.getFromFacultyHistory(facultyid,bookid);
                                if (!historyDB.moveToFirst())
                                    historyDB.moveToFirst();

                                if(historyDB.getCount() == 0)
                                {
                                    Toast.makeText(returnfaculty.this, "Incorrect Details", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if(facultyFineLayout.getVisibility()==View.GONE||facultyFineLayout.getVisibility()==View.INVISIBLE)
                                {
                                    facultyFineLayout.setVisibility(View.VISIBLE);
                                }

                                Process.setClickable(false);
                                FineAmt.setText("10.00");

                                Cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Process.setClickable(true);
                                        BookID.setText(" ");
                                        FacultyID.setText(" ");
                                        ReturnDate.setText(" ");

                                        if(facultyFineLayout.getVisibility()== View.VISIBLE||facultyFineLayout.getVisibility()==View.INVISIBLE)
                                            facultyFineLayout.setVisibility(View.GONE);

                                        Toast.makeText(returnfaculty.this, "Submission Cancelled", Toast.LENGTH_SHORT).show();
                                        Intent intentToMainActivity = new Intent(returnfaculty.this, MainActivity.class);
                                        startActivity(intentToMainActivity);

                                    }
                                });

                                Submit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Boolean isInserted = myDB.updateFacultyHistoryTable(bookid, facultyid, historyDB.getString(2),
                                                returndate, "10.00");
                                        Boolean isUpdated = myDB.updateIssuedOfBook(tempdb.getString(1), tempdb.getString(2), bookid,
                                                tempdb.getString(5), tempdb.getString(3), tempdb.getString(4),
                                                tempdb.getString(7), tempdb.getString(8), "NO");
                                        if (isInserted && isUpdated) {
                                            Toast.makeText(returnfaculty.this, "Book Returned:)\nThanks", Toast.LENGTH_SHORT).show();
                                            Intent intentToMainActivity = new Intent(returnfaculty.this, MainActivity.class);
                                            startActivity(intentToMainActivity);
                                        } else {
                                            Toast.makeText(returnfaculty.this, "Error Occurred,\nTry Again", Toast.LENGTH_SHORT).show();
                                            Process.setClickable(true);
                                        }
                                    }
                                });

                            }
                        }
                    }

                }




            }
        });

    }
}
