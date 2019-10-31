package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class show_book extends AppCompatActivity {

    DatabaseHelperClass mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);


        mydb = new DatabaseHelperClass(show_book.this);
        ArrayList<Book> books = new ArrayList<>();
        Cursor tempDB = mydb.getFromBooks();

        while (tempDB.moveToNext())
        {
            books.add(new Book(tempDB.getString(1),tempDB.getString(0),tempDB.getString(2),tempDB.getString(6)));
        }

        ListView bookview = (ListView) findViewById(R.id.showBookListView);
        BookAdapter madapter = new BookAdapter(show_book.this,0,books);
        bookview.setAdapter(madapter);

        bookview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(show_book.this, "Selected"+position, Toast.LENGTH_SHORT).show();
                Intent intentToshowbookinfo = new Intent(show_book.this,book_single_info.class);
                startActivity(intentToshowbookinfo);
            }
        });

    }
}
