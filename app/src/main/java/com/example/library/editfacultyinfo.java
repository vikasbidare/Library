package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editfacultyinfo extends AppCompatActivity {

    String fname;
    String mname;
    String lname;
    String fid;
    String department;
    String phone;
    String email;
    boolean iserror = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editfacultyinfo);

        Button submitbtn = (Button) findViewById(R.id.editfacultyinfosubmitbtn);
        Button cancelbtn = (Button) findViewById(R.id.editfacultyinfocancelbtn);

        final EditText Fname = findViewById(R.id.editfacultyfname);
        final EditText Mname = findViewById(R.id.editfacultymname);
        final EditText Lname = findViewById(R.id.editfacultylname);
        final EditText Department = findViewById(R.id.editfacultydepartment);
        final EditText Phone = findViewById(R.id.editfacultyphone);
        final EditText Email = findViewById(R.id.editfacultyemail);
        final EditText ID = findViewById(R.id.editfacultyid);

        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        final String MobilePattern = "[0-9]{10}";

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(editfacultyinfo.this,MainActivity.class);
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
                fid = ID.getText().toString().trim();

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
                if(fid.isEmpty())
                {
                    ID.setError("Enter ID");
                    iserror = true;
                }
                if(!iserror)
                {
                    DatabaseHelperClass db = new DatabaseHelperClass(editfacultyinfo.this);
                    boolean isUpdated;
                    if(mname.isEmpty()) {
                        isUpdated = db.updatefaculty(fid,fname + " " + lname, email, phone, department);
                    }
                    else
                    {
                        isUpdated = db.updatefaculty(fid,fname + " " + mname + " " + lname, email, phone, department);
                    }
                    if(isUpdated)
                    {
                        Toast.makeText(editfacultyinfo.this, "faculty info updated", Toast.LENGTH_SHORT).show();
                        Intent submitIntent = new Intent(editfacultyinfo.this,MainActivity.class);
                        startActivity(submitIntent);
                    }
                    else
                    {
                        Toast.makeText(editfacultyinfo.this, "Sorry, no faculty with mentioned ID", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
