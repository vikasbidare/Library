package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class issue_to_both extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_to_both);

        Button studentIssueBtn = (Button) findViewById(R.id.studentissuebtn);
        Button facultyIssueBtn = (Button) findViewById(R.id.facultyissuebtn);

        studentIssueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToStudentIssue = new Intent(issue_to_both.this,issuetostudent.class);
                startActivity(intentToStudentIssue);
            }
        });

        facultyIssueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToFacultyIssue = new Intent(issue_to_both.this,issuetofaculty.class);
                startActivity(intentToFacultyIssue);
            }
        });
    }
}
