package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class add_student extends AppCompatActivity {

    String fname;
    String mname;
    String lname;
    String department;
    String phone;
    String email;
    boolean iserror = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Button submitbtn = (Button) findViewById(R.id.submitbtn);
        Button cancelbtn = (Button) findViewById(R.id.cancelbtn);

        final EditText Fname = findViewById(R.id.Addstudentfname);
        final EditText Mname = findViewById(R.id.Addstudentmname);
        final EditText Lname = findViewById(R.id.Addstudentlname);
        final EditText Department = findViewById(R.id.Addstudentdepartment);
        final EditText Phone = findViewById(R.id.Addstudentphone);
        final EditText Email = findViewById(R.id.Addstudentemail);

        fname = Fname.getText().toString();
        mname = Mname.getText().toString();
        lname = Lname.getText().toString();
        department = Department.getText().toString();
        phone = Phone.getText().toString();
        email = Email.getText().toString();

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(add_student.this,MainActivity.class);
                startActivity(cancelIntent);
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iserror = false;
                if(TextUtils.isEmpty(email)){
                    iserror = true;
                    Email.setError("Enter e-mail ID");
                }




            }
        });



    }
}
