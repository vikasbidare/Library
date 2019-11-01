package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editstudentinfo extends AppCompatActivity {

    String fname;
    String mname;
    String lname;
    String sid;
    String department;
    String phone;
    String email;
    boolean iserror = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editstudentinfo);

        Button submitbtn = (Button) findViewById(R.id.editstudentsubmitbtn);
        Button cancelbtn = (Button) findViewById(R.id.editstudentcancelbtn);

        final EditText Fname = findViewById(R.id.editstudentfname);
        final EditText Mname = findViewById(R.id.editstudentmname);
        final EditText Lname = findViewById(R.id.editstudentlname);
        final EditText Department = findViewById(R.id.editstudentdepartment);
        final EditText Phone = findViewById(R.id.editstudentphone);
        final EditText Email = findViewById(R.id.editstudentemail);
        final EditText ID = findViewById(R.id.editstudentid);

        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        final String MobilePattern = "[0-9]{10}";

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(editstudentinfo.this,MainActivity.class);
                startActivity(cancelIntent);
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fname = Fname.getText().toString().trim();
                mname = Mname.getText().toString().trim();
                lname = Lname.getText().toString().trim();
                department = Department.getText().toString().trim();
                phone = Phone.getText().toString().trim();
                email = Email.getText().toString().trim();
                sid = ID.getText().toString().trim();

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
                if(sid.isEmpty())
                {
                    ID.setError("Enter ID");
                    iserror = true;
                }
                if(!iserror)
                {
                    DatabaseHelperClass db = new DatabaseHelperClass(editstudentinfo.this);
                    boolean isUpdated;
                    if(mname.isEmpty()) {
                        isUpdated = db.updateStudent(sid,fname + " " + lname, email, phone, department);
                    }
                    else
                    {
                        isUpdated = db.updateStudent(sid,fname + " " + mname + " " + lname, email, phone, department);
                    }
                    if(isUpdated)
                    {
                        Toast.makeText(editstudentinfo.this, "Student info updated", Toast.LENGTH_SHORT).show();
                        Intent submitIntent = new Intent(editstudentinfo.this,MainActivity.class);
                        startActivity(submitIntent);
                    }
                    else
                    {
                        Toast.makeText(editstudentinfo.this, "Sorry, no student with mentioned ID", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
