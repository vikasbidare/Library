package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class profile_settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);


        ArrayList<DataClass> items = new ArrayList<>();

        Intent toShareApp = new Intent();
        toShareApp.setAction(Intent.ACTION_SEND);
        toShareApp.putExtra(Intent.EXTRA_TEXT,
                "Try out the new LibraryApp for easing the functionality of librarian \n Download soon from the given link \n " +
                        "https://drive.google.com/file/d/1wxD4O1aIuxjL2jSkj3Uk5xyiZfxZbXGX/view?usp=sharing");
        toShareApp.setType("text/plain");
        Intent.createChooser(toShareApp, "Share app via");

        if (items.size() == 0) {
            items.add(new DataClass(R.drawable.editaccount,"EditAccount",new Intent(profile_settings.this,add_book.class) ));
            items.add(new DataClass(R.drawable.share,"ShareApp",toShareApp));
            items.add(new DataClass(R.drawable.terms,"Directions",new Intent(profile_settings.this,add_faculty.class) ));
            items.add(new DataClass(R.drawable.developers,"Developers",new Intent(profile_settings.this,issue_to_both.class) ));
            items.add(new DataClass(R.drawable.delete,"DeleteAccount",new Intent(profile_settings.this,issue_to_both.class) ));
        }
        ViewAdapter adapter = new ViewAdapter(profile_settings.this, items);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

    }
}
