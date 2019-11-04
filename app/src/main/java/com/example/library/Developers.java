package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Developers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        TextView dev1 = (TextView) findViewById(R.id.developersLinkedIn1);
        dev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent linkedinIntent = new Intent(Intent.ACTION_VIEW);
                linkedinIntent.setClassName("com.linkedin.android", "com.linkedin.android.profile.ViewProfileActivity");
                linkedinIntent.putExtra("memberId", "dhanuesh-r-c-167478172");
                linkedinIntent.setPackage("com.linkedin.android");
                try {
                    startActivity(linkedinIntent);
                } catch ( ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/dhanuesh-r-c-167478172")));
                }
            }
        });

        TextView dev2 = (TextView) findViewById(R.id.developersLinkedIn2);
        dev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent linkedinIntent2 = new Intent(Intent.ACTION_VIEW);
                linkedinIntent2.setClassName("com.linkedin.android", "com.linkedin.android.profile.ViewProfileActivity");
                linkedinIntent2.putExtra("memberId", "vikas-b-n-11b5a6162/");
                linkedinIntent2.setPackage("com.linkedin.android");
                try {
                    startActivity(linkedinIntent2);
                } catch ( ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/vikas-b-n-11b5a6162/")));
                }
            }
        });
    }
}


