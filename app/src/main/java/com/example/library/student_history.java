package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class student_history extends AppCompatActivity {

    DatabaseHelperClass mydb;
    ArrayList<History> students = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_history);


        mydb = new DatabaseHelperClass(student_history.this);
         Cursor tempDB = mydb.getAllFromStudentHistory();

        while (tempDB.moveToNext())
        {
            students.add(new History(tempDB.getString(1),tempDB.getString(0),tempDB.getString(2),
                    tempDB.getString(3),tempDB.getString(4)));
        }

        Collections.reverse(students);

        ListView studenthistory = (ListView) findViewById(R.id.studentHistoryListView);
        StudentAdapter adapt = new StudentAdapter(student_history.this,0,students);
        studenthistory.setAdapter(adapt);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        students.clear();
    }
}
