package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete_faculty extends AppCompatActivity {

    String fid;
    Boolean iserror=false;
    Boolean isdeleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_faculty);

        final EditText facultyID = findViewById(R.id.deletefacultyid);
        final Button deletefaculty = findViewById(R.id.deleteFacultybtn);
        deletefaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iserror = false;
                fid = facultyID.getText().toString().trim();
                if(fid.isEmpty())
                {
                    iserror =true;
                    facultyID.setError("Enter Faculty ID");
                }
                if(!iserror)
                {
                    DatabaseHelperClass db = new DatabaseHelperClass(delete_faculty.this);
                    isdeleted = db.deletefromFaculty(fid);

                    if(!isdeleted)
                    {
                        Toast.makeText(delete_faculty.this,"The faculty ID is wrong or the faculty has an unreturned book.\nEnsure that" +
                                " the user has returned all issue books", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(delete_faculty.this,"Faculty deleted.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
