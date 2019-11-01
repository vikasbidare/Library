package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class book_single_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_single_info);

        Intent parentIntent = getIntent();

        TextView bookID = (TextView) findViewById(R.id.bookinfoid);
        bookID.setEnabled(false);
        bookID.setText(parentIntent.getStringExtra("BookId"));

         TextView bookTitle = (TextView) findViewById(R.id.bookinfotitle);
        bookTitle.setEnabled(false);
        bookTitle.setText(parentIntent.getStringExtra("Title"));

         TextView bookedition = (TextView) findViewById(R.id.bookinfoedition);
        bookedition.setEnabled(false);
        bookedition.setText(parentIntent.getStringExtra("Edition"));

         TextView bookAuthor = (TextView) findViewById(R.id.bookinfoauthor);
        bookAuthor.setEnabled(false);
        bookAuthor.setText(parentIntent.getStringExtra("Author"));

         TextView bookgenre = (TextView) findViewById(R.id.bookinfogenre);
        bookgenre.setEnabled(false);
        bookgenre.setText(parentIntent.getStringExtra("Genre"));

         TextView bookpublisher = (TextView) findViewById(R.id.bookinfopublisher);
        bookpublisher.setEnabled(false);
        bookpublisher.setText(parentIntent.getStringExtra("Publisher"));

         TextView bookprice = (TextView) findViewById(R.id.bookinfoprice);
        bookprice.setEnabled(false);
        bookprice.setText(parentIntent.getStringExtra("Price"));

         TextView bookyear = (TextView) findViewById(R.id.bookinfoyear);
        bookyear.setEnabled(false);
        bookyear.setText(parentIntent.getStringExtra("Year of Publication"));

         TextView bookissued = (TextView) findViewById(R.id.bookinfoissued);
        bookissued.setEnabled(false);
        bookissued.setText(parentIntent.getStringExtra("Issued"));

        Button Backbtn = (Button) findViewById(R.id.bookinfobackbtn);
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToshowbooks = new Intent(book_single_info.this,show_book.class);
                startActivity(intentToshowbooks);
            }
        });
    }
}
