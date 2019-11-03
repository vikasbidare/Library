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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class returnstudent extends AppCompatActivity {

    DatabaseHelperClass myDB;
    String bookid, studentid, returndate ,fineamt;
    Boolean isEmpty = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returnstudent);


        final EditText StudentID, BookID, ReturnDate, FineAmt;
        final Button Process, Cancel, Submit;

        StudentID = (EditText) findViewById(R.id.studentreturnstudentid);
        BookID = (EditText) findViewById(R.id.studentreturnbookid);
        ReturnDate = (EditText) findViewById(R.id.studentreturndate);
        FineAmt = (EditText) findViewById(R.id.studentreturnFineAmt);

        Process = (Button) findViewById(R.id.studentreturnprocessbtn);
        Cancel = (Button) findViewById(R.id.returnStudentCancelBtn);
        Submit = (Button) findViewById(R.id.returnStudentSubmitBtn);

        myDB = new DatabaseHelperClass(returnstudent.this);

        final LinearLayout studentFineLayout = (LinearLayout) findViewById(R.id.returnStudentLayout);
        if (studentFineLayout.getVisibility() == View.VISIBLE || studentFineLayout.getVisibility() == View.INVISIBLE)
            studentFineLayout.setVisibility(View.GONE);

        FineAmt.setEnabled(false);


        Process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bookid = BookID.getText().toString().trim();
                studentid = StudentID.getText().toString().trim();
                returndate = ReturnDate.getText().toString().trim();

                isEmpty = false;

                if (bookid.isEmpty()) {
                    BookID.setError("Enter Book-ID");
                    isEmpty = true;
                }

                if (studentid.isEmpty()) {
                    StudentID.setError("Enter Student-ID");
                    isEmpty = true;
                }

                if (returndate.isEmpty()) {
                    ReturnDate.setError("Enter ReturnDate");
                    isEmpty = true;
                }

                if (!isEmpty) {

                    final Cursor tempdb = myDB.getAboutABook(bookid);
                    if (tempdb.getCount() == 0) {
                        Toast.makeText(returnstudent.this, "Wrong Book-ID", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        if (!tempdb.moveToFirst())
                            tempdb.moveToFirst();

                        Cursor studentDB = myDB.getAboutAStudent(studentid);
                        if (studentDB.getCount() == 0) {
                            Toast.makeText(returnstudent.this, "Wrong Student-ID", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            String isIssued = tempdb.getString(6);
                            if (isIssued.equals("NO")) {
                                Toast.makeText(returnstudent.this, "Sorry!!\n Book Already Returned", Toast.LENGTH_SHORT).show();
                                return;
                            } else {

                                final Cursor historyDB = myDB.getFromStudentHistory(studentid, bookid);
                                if (!historyDB.moveToFirst())
                                    historyDB.moveToFirst();

                                if (historyDB.getCount() == 0) {
                                    Toast.makeText(returnstudent.this, "Incorrect Details", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if (studentFineLayout.getVisibility() == View.GONE || studentFineLayout.getVisibility() == View.INVISIBLE) {
                                    studentFineLayout.setVisibility(View.VISIBLE);
                                }

                                Process.setClickable(false);

                                Date IssueDate = null, ReturnDates = null;

                                try {
                                    IssueDate = new SimpleDateFormat("DD-MM-YYYY").parse(historyDB.getString(2));
                                    ReturnDates = new SimpleDateFormat("DD-MM-YYYY").parse(returndate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                long res = ReturnDates.getTime() - IssueDate.getTime();

                                if (res / (1000 * 60 * 60 * 24) - 14 > 0) {
                                    res = (res / (1000 * 60 * 60 * 24) - 14);
                                    fineamt = res + "/-";
                                } else {
                                    fineamt = "0.00/-";
                                }
                                FineAmt.setText(fineamt);

                                Cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Process.setClickable(true);
                                        BookID.setText(" ");
                                        StudentID.setText(" ");
                                        ReturnDate.setText(" ");

                                        if (studentFineLayout.getVisibility() == View.VISIBLE || studentFineLayout.getVisibility() == View.INVISIBLE)
                                            studentFineLayout.setVisibility(View.GONE);

                                        Toast.makeText(returnstudent.this, "Submission Cancelled", Toast.LENGTH_SHORT).show();
                                        Intent intentToMainActivity = new Intent(returnstudent.this, MainActivity.class);
                                        startActivity(intentToMainActivity);

                                    }
                                });

                                Submit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Boolean isInserted = myDB.updateStudentHistoryTable(bookid, studentid, historyDB.getString(2),
                                                returndate, fineamt);
                                        Boolean isUpdated = myDB.updateIssuedOfBook(tempdb.getString(1), tempdb.getString(2), bookid,
                                                tempdb.getString(5), tempdb.getString(3), tempdb.getString(4),
                                                tempdb.getString(7), tempdb.getString(8), "NO");
                                        if (isInserted && isUpdated) {
                                            Toast.makeText(returnstudent.this, "Book Returned:)\nThanks", Toast.LENGTH_SHORT).show();
                                            Intent intentToMainActivity = new Intent(returnstudent.this, MainActivity.class);
                                            startActivity(intentToMainActivity);
                                        } else {
                                            Toast.makeText(returnstudent.this, "Error Occurred,\nTry Again", Toast.LENGTH_SHORT).show();
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
