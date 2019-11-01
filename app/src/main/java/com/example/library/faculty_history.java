package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class faculty_history extends AppCompatActivity {

    DatabaseHelperClass mydb;
    ArrayList<History> faculty = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_history);


        mydb = new DatabaseHelperClass(faculty_history.this);
         Cursor tempDB = mydb.getAllFromFacultyHistory();


         while (tempDB.moveToNext())
        {
            faculty.add(new History(tempDB.getString(1),tempDB.getString(0),tempDB.getString(2),
                    tempDB.getString(3),tempDB.getString(4)));
        }

        ListView facultyHistory = (ListView) findViewById(R.id.facultyHistoryListView);
        FacultyAdapter adapt = new FacultyAdapter(faculty_history.this,0,faculty);
        facultyHistory.setAdapter(adapt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        faculty.clear();
    }
}
