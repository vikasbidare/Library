package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class return_from_both extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_from_both);

        Button studentReturnBtn = (Button) findViewById(R.id.studentreturnbtn);
        Button facultyReturnBtn = (Button) findViewById(R.id.facultyreturnbtn);

        studentReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToStudentReturn = new Intent(return_from_both.this,returnstudent.class);
                startActivity(intentToStudentReturn);
            }
        });

        facultyReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToFacultyReturn = new Intent(return_from_both.this,returnfaculty.class);
                startActivity(intentToFacultyReturn);
            }
        });
    }
}
