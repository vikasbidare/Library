package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();

    private String UserEmail,UserName,UserNumber;
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent s = new Intent(Intent.ACTION_MAIN);
            s.addCategory(Intent.CATEGORY_HOME);
            s.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(s);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        mHandler.postDelayed(mRunnable, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

        Intent intentFrom = getIntent();
        UserEmail = intentFrom.getStringExtra("UserEmail");
        UserName = intentFrom.getStringExtra("Name");
        UserNumber =intentFrom.getStringExtra("PhoneNumber");

        TextView email = (TextView) findViewById(R.id.profileemail);
        TextView name = (TextView) findViewById(R.id.profilename);
        TextView phone = (TextView) findViewById(R.id.profilephno);


        email.setText(UserEmail);
        name.setText(UserName);
        phone.setText(UserNumber);

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

        CircleImageView profileSettings = (CircleImageView) findViewById(R.id.profileSetting);
        profileSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToProfileSettings = new Intent(MainActivity.this,profile_settings.class);
                startActivity(intentToProfileSettings);
            }
        });


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