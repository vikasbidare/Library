package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class faculty_history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_history);

        ArrayList<History> faculty = new ArrayList<>();
        faculty.add(new History("f101","B101","2013-10-19","2019-10-19","20"));
        faculty.add(new History("f102","B102","2019-10-19","2019-10-19","10"));
        faculty.add(new History("f102","B102","2015-10-19","2019-10-19","22"));
        faculty.add(new History("f103","B103","2014-10-19","2019-10-19","24"));
        faculty.add(new History("f102","B104","2019-10-19","2019-10-19","0"));
        faculty.add(new History("f101","B105","2019-10-19","2019-10-19","20"));
        faculty.add(new History("f102","B101","2013-10-19","2019-10-19","24"));
        faculty.add(new History("f103","B101","2019-10-19","2019-10-19","24"));
        faculty.add(new History("f102","B102","2019-10-19","2019-10-19","26"));
        faculty.add(new History("f101","B102","2014-10-19","2019-10-19","0.00"));



        ListView facultyHistory = (ListView) findViewById(R.id.facultyHistoryListView);
        FacultyAdapter adapt = new FacultyAdapter(faculty_history.this,0,faculty);
        facultyHistory.setAdapter(adapt);
    }
}
