package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<DataClass> items = new ArrayList<>();

        if (items.size() == 0) {
            items.add(new DataClass(R.drawable.add_book,"Add Book",new Intent(MainActivity.this,add_book.class) ));
            items.add(new DataClass(R.drawable.student,"Add Student",new Intent(MainActivity.this,add_student.class) ));
            items.add(new DataClass(R.drawable.teacher,"Add Faculty",new Intent(MainActivity.this,add_faculty.class) ));
            items.add(new DataClass(R.drawable.issue_book,"Issue Book",new Intent(MainActivity.this,issue_to_both.class) ));
            items.add(new DataClass(R.drawable.return_book,"Return Book",new Intent(MainActivity.this,return_from_both.class)));
            items.add(new DataClass(R.drawable.show_book,"Show Books",new Intent(MainActivity.this,show_book.class) ));
            items.add(new DataClass(R.drawable.show_student,"Show Students",new Intent(MainActivity.this,show_students.class)));
            items.add(new DataClass(R.drawable.show_faculty,"Show Faculties",new Intent(MainActivity.this,show_faculty.class)));
            items.add(new DataClass(R.drawable.edit_info,"Edit Info",new Intent(MainActivity.this,editinfo.class)));
            items.add(new DataClass(R.drawable.history,"Show History",new Intent(MainActivity.this,history_for.class) ));


        }
        ViewAdapter adapter = new ViewAdapter(MainActivity.this, items);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        final Intent intentToLogin = new Intent(MainActivity.this,Login.class);
        TextView logoutText = (TextView) findViewById(R.id.mainActivityLogoutText);
        ImageView logoutImage = (ImageView)findViewById(R.id.mainActivityLogoutIcon);

        logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentToLogin);
            }
        });

        logoutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentToLogin);
            }
        });
    }
}