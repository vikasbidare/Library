package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class show_faculty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_faculty);

        ArrayList<Designation> publics = new ArrayList<>();
        publics.add(new Designation("Prof. Ravi","email@com","1234567890","ME","f101"));
        publics.add(new Designation("Prof. surya","email@com","12345123890","CSE","f102"));
        publics.add(new Designation("alwly","email@cwdom","1245567890","CE","f102"));
        publics.add(new Designation("vzdfzRavi","email@com","6575677890","Cdd","f103"));
        publics.add(new Designation("dFFavi","email@com","46323467890","CSE","f104"));
        publics.add(new Designation("SFEvi","email@com","97887567890","Civil","f105"));
        publics.add(new Designation("SFEeFi","email@com","66434567890","Mech","f106"));



        ListView studentView = (ListView) findViewById(R.id.showFacultyListView);
        ShowPersonAdapter madapter = new ShowPersonAdapter(show_faculty.this,0,publics);
        studentView.setAdapter(madapter);
    }
}
