package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class show_students extends AppCompatActivity {

    DatabaseHelperClass mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_students);

        mydb = new DatabaseHelperClass(show_students.this);
        ArrayList<Designation> publics = new ArrayList<>();

        Cursor tempDB = mydb.getFromStudents();

        while (tempDB.moveToNext())
        {
            publics.add(new Designation(tempDB.getString(1),tempDB.getString(2),tempDB.getString(3),tempDB.getString(4)
                    ,tempDB.getString(0)));
        }
        Collections.reverse(publics);

        ListView studentView = (ListView) findViewById(R.id.showStudentsListView);
        PersonAdapter madapter = new PersonAdapter(show_students.this,0,publics);
        studentView.setAdapter(madapter);

    }
}
