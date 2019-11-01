package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class edit_account_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_info);

        Button Editusernamebtn = (Button) findViewById(R.id.editnamebtn);
        Button Editnumberbtn = (Button) findViewById(R.id.editnumberbtn);
        Button Editpasswordbtn = (Button) findViewById(R.id.editpasswordbtn);
        Button finishbtn = (Button) findViewById(R.id.finishbtn);
    }
}
