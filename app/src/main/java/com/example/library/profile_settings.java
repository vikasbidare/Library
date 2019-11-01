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


        if (items.size() == 0) {
            items.add(new DataClass(R.drawable.editaccount,"EditAccount",new Intent(profile_settings.this,edit_account_info.class) ));
            items.add(new DataClass(R.drawable.share,"ShareApp",new Intent(profile_settings.this,add_student.class) ));
            items.add(new DataClass(R.drawable.directions,"Directions",new Intent(profile_settings.this,terms.class) ));
            items.add(new DataClass(R.drawable.delete,"DeleteAccount",new Intent(profile_settings.this,Login.class) ));
            items.add(new DataClass(R.drawable.developers,"Developers",new Intent(profile_settings.this,Developers.class) ));
        }
        ViewAdapter adapter = new ViewAdapter(profile_settings.this, items);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

    }
}
