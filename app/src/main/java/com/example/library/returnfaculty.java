package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class returnfaculty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returnfaculty);


        final LinearLayout facultyFineLayout = (LinearLayout) findViewById(R.id.returnFacultyLayout);
        if(facultyFineLayout.getVisibility()== View.VISIBLE||facultyFineLayout.getVisibility()==View.INVISIBLE)
            facultyFineLayout.setVisibility(View.GONE);

        final TextView facultyFineAmt = (TextView) findViewById(R.id.facultyFineAmt);
        facultyFineAmt.setEnabled(false);
        facultyFineAmt.setText("0.00");

        Button finecalculator = (Button) findViewById(R.id.facultyProcessbtn);
        finecalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(facultyFineLayout.getVisibility()==View.GONE||facultyFineLayout.getVisibility()==View.INVISIBLE)
                {
                    facultyFineLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        Button returnfacultycancel = (Button) findViewById(R.id.returnFacultyCancelBtn);
        Button returnfacultysubmit = (Button) findViewById(R.id.returnFacultytSubmitBtn);

        final Intent intentToMain = new Intent(returnfaculty.this,MainActivity.class);
        returnfacultycancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(returnfaculty.this, "Submission Cancelled", Toast.LENGTH_SHORT).show();
                startActivity(intentToMain);
            }
        });

        returnfacultysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(returnfaculty.this, "Submission Sucessfull", Toast.LENGTH_SHORT).show();
                startActivity(intentToMain);
            }
        });
    }
}
