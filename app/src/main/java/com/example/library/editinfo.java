package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class editinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinfo);

        Button editstudent = (Button) findViewById(R.id.editstudentInfobtn);
        editstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(editinfo.this,editstudentinfo.class);
                startActivity(in1);
            }
        });

        Button deletestudent = (Button) findViewById(R.id.deletestudentbtn);
        deletestudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(editinfo.this,delete_student.class);
                startActivity(in1);
            }
        });

        Button editfaculty = (Button) findViewById(R.id.editfacultyInfobtn);
        editfaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2 = new Intent(editinfo.this,editfacultyinfo.class);
                startActivity(in2);
            }
        });

        Button deletefaculty = (Button) findViewById(R.id.deletefacultybtn);
        deletefaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(editinfo.this,delete_faculty.class);
                startActivity(in1);
            }
        });
  }




}
