package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class student_history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_history);

        ArrayList<History> students = new ArrayList<>();
        students.add(new History("s101","B101","2013-10-19","2019-10-19","20"));
        students.add(new History("s102","B102","2019-10-19","2019-10-19","10"));
        students.add(new History("s102","B102","2015-10-19","2019-10-19","22"));
        students.add(new History("s103","B103","2014-10-19","2019-10-19","24"));
        students.add(new History("s102","B104","2019-10-19","2019-10-19","0"));
        students.add(new History("s101","B105","2019-10-19","2019-10-19","20"));
        students.add(new History("s102","B101","2013-10-19","2019-10-19","24"));
        students.add(new History("s103","B101","2019-10-19","2019-10-19","24"));
        students.add(new History("s102","B102","2019-10-19","2019-10-19","26"));
        students.add(new History("s101","B102","2014-10-19","2019-10-19","0.00"));



        ListView studenthistory = (ListView) findViewById(R.id.studentHistoryListView);
        StudentAdapter adapt = new StudentAdapter(student_history.this,0,students);
        studenthistory.setAdapter(adapt);
    }
}
