package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class student_single_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_single_info);

        final TextView studentID = (TextView) findViewById(R.id.studentinfoid);
        studentID.setEnabled(false);
        studentID.setText("S101");

        final TextView studentFullName = (TextView) findViewById(R.id.studentinfoname);
        studentFullName.setEnabled(false);
        studentFullName.setText("Ulrich Nielsen");

        final TextView studentDepartment = (TextView) findViewById(R.id.studentinfodepartment);
        studentDepartment.setEnabled(false);
        studentDepartment.setText("Computer Science and Engineering");

        final TextView studentPhone = (TextView) findViewById(R.id.studentinfophone);
        studentPhone.setEnabled(false);
        studentPhone.setText("9449120250");

        final TextView studentemail = (TextView) findViewById(R.id.studentinfoemail);
        studentemail.setEnabled(false);
        studentemail.setText("abc@xyz.com");

        Button Backbtn = (Button) findViewById(R.id.studentinfobackbtn);
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToshowstudents = new Intent(student_single_info.this,show_students.class);
                startActivity(intentToshowstudents);
            }
        });
    }
}
