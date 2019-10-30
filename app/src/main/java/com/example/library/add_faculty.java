package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class add_faculty extends AppCompatActivity {

    String fname;
    String mname;
    String lname;
    String department;
    String phone;
    String email;
    String id;
    boolean iserror = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        Button submitbtn = (Button) findViewById(R.id.submitbtn);
        Button cancelbtn = (Button) findViewById(R.id.CancelHost);

        final EditText Fname = findViewById(R.id.Addfacultyfname);
        final EditText Mname = findViewById(R.id.Addfacultymname);
        final EditText Lname = findViewById(R.id.Addfacultylname);
        final EditText Department = findViewById(R.id.Addfacultydepartment);
        final EditText Phone = findViewById(R.id.Addfacultyphone);
        final EditText Email = findViewById(R.id.Addfacultyemail);
        final EditText ID = findViewById(R.id.Addfacultyid);



        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        final String MobilePattern = "[0-9]{10}";

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(add_faculty.this,MainActivity.class);
                startActivity(cancelIntent);
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = Fname.getText().toString().trim();
                mname = Mname.getText().toString().trim();
                lname = Lname.getText().toString().trim();
                department = Department.getText().toString().trim();
                phone = Phone.getText().toString().trim();
                email = Email.getText().toString().trim();
                id = ID.getText().toString().trim();
                iserror = false;
                if(TextUtils.isEmpty(email)){
                    iserror = true;
                    Email.setError("Enter e-mail ID");
                }
                if(!email.matches(emailPattern)){
                    iserror = true;
                    Email.setError("Enter correct e-mail ID");
                }
                if(fname.isEmpty())
                {
                    Fname.setError("Enter First name");
                    iserror = true;
                }
                if(lname.isEmpty())
                {
                    Lname.setError("Enter Last name");
                    iserror = true;
                }
                if(phone.isEmpty())
                {
                    Phone.setError("Enter Phone Number");
                    iserror = true;
                }
                if(!phone.matches(MobilePattern))
                {
                    Phone.setError("Enter Valid Phone Number");
                    iserror = true;
                }
                if(department.isEmpty())
                {
                    Department.setError("Enter department");
                    iserror = true;
                }
                if(id.isEmpty())
                {
                    ID.setError("Enter ID");
                    iserror = true;
                }
                if(!iserror)
                {
                    DatabaseHelperClass db = new DatabaseHelperClass(add_faculty.this);
                    boolean isInserted;
                    if(mname.isEmpty()) {
                        isInserted = db.insertIntoFaculty(fname + " " + lname, email, phone, department, id);
                    }
                    else
                    {
                        isInserted = db.insertIntoFaculty(fname + " " + mname + " " + lname, email, phone, department, id);
                    }

                    if(isInserted)
                    {
                        Toast.makeText(add_faculty.this, "Faculty added", Toast.LENGTH_SHORT).show();
                        Intent submitIntent = new Intent(add_faculty.this,MainActivity.class);
                        startActivity(submitIntent);
                    }
                    else
                    {
                        Toast.makeText(add_faculty.this, "Faculty already exists", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
