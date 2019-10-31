package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class show_faculty extends AppCompatActivity {

    DatabaseHelperClass mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_faculty);

        mydb = new DatabaseHelperClass(show_faculty.this);
        ArrayList<Designation> publics = new ArrayList<>();
        Cursor tempDB = mydb.getFromFaculty();

        while (tempDB.moveToNext())
        {
            publics.add(new Designation(tempDB.getString(1),tempDB.getString(2),tempDB.getString(3),tempDB.getString(4)
                    ,tempDB.getString(0)));
        }

        ListView studentView = (ListView) findViewById(R.id.showFacultyListView);
        ShowPersonAdapter madapter = new ShowPersonAdapter(show_faculty.this,0,publics);
        studentView.setAdapter(madapter);
    }
}
