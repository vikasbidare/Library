package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete_student extends AppCompatActivity {

    String sid;
    Boolean iserror=false;
    Boolean isdeleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);

        final EditText studentID = findViewById(R.id.deletestudentid);
        final Button deletestudent = findViewById(R.id.deletestudentbtn);
        deletestudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iserror = false;
                sid = studentID.getText().toString().trim();
                if(sid.isEmpty())
                {
                    iserror =true;
                    studentID.setError("Enter student ID");
                }
                if(!iserror)
                {
                    DatabaseHelperClass db = new DatabaseHelperClass(delete_student.this);
                    isdeleted = db.deletefromStudent(sid);

                    if(!isdeleted)
                    {
                        Toast.makeText(delete_student.this,"The Student ID is wrong or the student has an un-returned book.\nEnsure that" +
                                " the user has returned all issued books", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(delete_student.this,"Student deleted.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
