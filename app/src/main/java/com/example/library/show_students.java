package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class show_students extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intenttoMain = new Intent(show_students.this,MainActivity.class);
        startActivity(intenttoMain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_students);

        ArrayList<Designation> publics = new ArrayList<>();
        publics.add(new Designation("Student Ravi","email@com","1234567890","CSE","s101"));
        publics.add(new Designation("Student surya","email@com","12345123890","SSE","s102"));
        publics.add(new Designation("Ravidsd","email@cwdom","1245567890","CE","s102"));
        publics.add(new Designation("vzdfzRavi","email@com","6575677890","Cdd","s103"));
        publics.add(new Designation("dFFavi","email@com","46323467890","CSE","s104"));
        publics.add(new Designation("SFEvi","email@com","97887567890","Civil","s105"));
        publics.add(new Designation("SFEeFi","email@com","66434567890","Mech","s106"));
        ListView studentView = (ListView) findViewById(R.id.showStudentsListView);
        PersonAdapter madapter = new PersonAdapter(show_students.this,0,publics);
        studentView.setAdapter(madapter);

    }
}
