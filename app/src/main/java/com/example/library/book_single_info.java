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

        final TextView bookID = (TextView) findViewById(R.id.bookinfoid);
        bookID.setEnabled(false);
        bookID.setText("B101");

        final TextView bookTitle = (TextView) findViewById(R.id.bookinfotitle);
        bookTitle.setEnabled(false);
        bookTitle.setText("Not a penny more,Not a penny less");

        final TextView bookedition = (TextView) findViewById(R.id.bookinfoedition);
        bookedition.setEnabled(false);
        bookedition.setText("1");

        final TextView bookAuthor = (TextView) findViewById(R.id.bookinfoauthor);
        bookAuthor.setEnabled(false);
        bookAuthor.setText("Jeffrey Archer");

        final TextView bookgenre = (TextView) findViewById(R.id.bookinfogenre);
        bookgenre.setEnabled(false);
        bookgenre.setText("Fiction");

        final TextView bookpublisher = (TextView) findViewById(R.id.bookinfopublisher);
        bookpublisher.setEnabled(false);
        bookpublisher.setText("Penguin");

        final TextView bookprice = (TextView) findViewById(R.id.bookinfoprice);
        bookprice.setEnabled(false);
        bookprice.setText("350");

        final TextView bookyear = (TextView) findViewById(R.id.bookinfoyear);
        bookyear.setEnabled(false);
        bookyear.setText("2012");

        final TextView bookissued = (TextView) findViewById(R.id.bookinfoissued);
        bookissued.setEnabled(false);
        bookissued.setText("Yes");

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
