package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_book extends AppCompatActivity {

    String btitle;
    String bid;
    String genre;
    String author;
    String edition;
    String publisher;
    String year;
    String price;
    boolean iserror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Button submitbtn = (Button) findViewById(R.id.submitbtn);
        Button cancelbtn = (Button) findViewById(R.id.CancelHost);

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(add_book.this,MainActivity.class);
                startActivity(cancelIntent);
            }
        });

        final EditText BID = findViewById(R.id.Addbookid);
        final EditText Btitle = findViewById(R.id.Addbooktitle);
        final EditText Bgenre = findViewById(R.id.Addbookgenre);
        final EditText Bauthor = findViewById(R.id.Addauthorname);
        final EditText Bedition = findViewById(R.id.addbookedition);
        final EditText Bpublisher = findViewById(R.id.addbookpublication);
        final EditText Byear = findViewById(R.id.addbookyear);
        final EditText Bprice = findViewById(R.id.addbookprice);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(add_book.this,MainActivity.class);
                startActivity(cancelIntent);
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btitle = Btitle.getText().toString().trim();
                bid = BID.getText().toString().trim();
                genre = Bgenre.getText().toString().trim();
                author = Bauthor.getText().toString().trim();
                edition = Bedition.getText().toString().trim();
                publisher = Bpublisher.getText().toString().trim();
                year = Byear.getText().toString().trim();
                price = Bprice.getText().toString().trim();
                iserror = false;
                if(TextUtils.isEmpty(bid)){
                    iserror = true;
                    BID.setError("Enter book ID");
                }
                if(TextUtils.isEmpty(btitle)){
                    iserror = true;
                    Btitle.setError("Enter book title");
                }
                if(TextUtils.isEmpty(genre)){
                    iserror = true;
                    Bgenre.setError("Enter book genre/subject");
                }
                if(edition.isEmpty()){
                    iserror = true;
                    Bedition.setError("Enter book edition");
                }
                if(year.isEmpty()){
                    iserror = true;
                    Byear.setError("Enter the year of publication");
                }
                if(TextUtils.isEmpty(author)){
                    iserror = true;
                    Bauthor.setError("Enter author's name");
                }
                if(TextUtils.isEmpty(publisher)){
                    iserror = true;
                    Bpublisher.setError("Enter book publisher's name");
                }
                if(year.isEmpty()){
                    iserror = true;
                    Byear.setError("Enter year of publication");
                }
                if(price.isEmpty()){
                    iserror = true;
                    Bprice.setError("Enter price");
                }
                if(!iserror){
                    DatabaseHelperClass db = new DatabaseHelperClass(add_book.this);
                    boolean isInserted;

                    isInserted = db.insertIntoBook(btitle,author,bid,price,edition,genre,publisher,year);

                    if(isInserted){
                        Toast.makeText(add_book.this,"Book added",Toast.LENGTH_SHORT).show();
                        Intent submitIntent = new Intent(add_book.this,MainActivity.class);
                        startActivity(submitIntent);
                    }
                    else{
                        Toast.makeText(add_book.this,"BookID already given",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
