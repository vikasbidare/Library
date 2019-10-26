package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class history_for extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_for);

        Button studentHistory = (Button) findViewById(R.id.historyForStudent);
        studentHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToStudentHistory = new Intent(history_for.this,student_history.class);
                startActivity(intentToStudentHistory);
            }
        });

        Button facultyHistory = (Button) findViewById(R.id.historyForFaculty);
        facultyHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToFacultyHistory = new Intent(history_for.this,faculty_history.class);
                startActivity(intentToFacultyHistory);
            }
        });
    }
}
