package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class returnstudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returnstudent);

        final LinearLayout studentFineLayout = (LinearLayout) findViewById(R.id.returnStudentLayout);
        if(studentFineLayout.getVisibility()== View.VISIBLE||studentFineLayout.getVisibility()==View.INVISIBLE)
            studentFineLayout.setVisibility(View.GONE);

        final TextView studentFineAmt = (TextView) findViewById(R.id.studentFineAmt);
        studentFineAmt.setEnabled(false);
        studentFineAmt.setText("0.00");

        Button finecalculator = (Button) findViewById(R.id.studentprocessbtn);
        finecalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(studentFineLayout.getVisibility()==View.GONE||studentFineLayout.getVisibility()==View.INVISIBLE)
                {
                    studentFineLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        Button returnstudentcancel = (Button) findViewById(R.id.returnStudentCancelBtn);
        Button returnstudentsubmit = (Button) findViewById(R.id.returnStudentSubmitBtn);

        final Intent intentToMain = new Intent(returnstudent.this,MainActivity.class);
        returnstudentcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(returnstudent.this, "Submission Cancelled", Toast.LENGTH_SHORT).show();
                startActivity(intentToMain);
            }
        });

        returnstudentsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(returnstudent.this, "Submission Sucessfull", Toast.LENGTH_SHORT).show();
                startActivity(intentToMain);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LinearLayout studentFineLayout = (LinearLayout) findViewById(R.id.returnStudentLayout);
        if(studentFineLayout.getVisibility()== View.VISIBLE||studentFineLayout.getVisibility()==View.INVISIBLE)
            studentFineLayout.setVisibility(View.GONE);

    }
}
