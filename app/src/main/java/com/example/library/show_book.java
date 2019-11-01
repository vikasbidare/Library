package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class show_book extends AppCompatActivity {

    DatabaseHelperClass mydb;
    ArrayList<Book> books = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);


        mydb = new DatabaseHelperClass(show_book.this);
        final Cursor tempDB = mydb.getFromBooks();

        while (tempDB.moveToNext())
        {
            books.add(new Book(tempDB.getString(1),tempDB.getString(0),tempDB.getString(2),tempDB.getString(6)));
        }
        Collections.reverse(books);

        ListView bookview = (ListView) findViewById(R.id.showBookListView);
        BookAdapter madapter = new BookAdapter(show_book.this,0,books);
        bookview.setAdapter(madapter);

        bookview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Book book = books.get(position);
                String bookid = book.getMbookId();
                Cursor tempdb = mydb.getAboutABook(bookid);

                if(!tempdb.moveToFirst())
                    tempdb.moveToFirst();

                Intent intentToshowbookinfo = new Intent(show_book.this,book_single_info.class);
                intentToshowbookinfo.putExtra("BookId",tempdb.getString(0));
                intentToshowbookinfo.putExtra("Title",tempdb.getString(1));
                intentToshowbookinfo.putExtra("Edition",tempdb.getString(3));
                intentToshowbookinfo.putExtra("Author",tempdb.getString(2));
                intentToshowbookinfo.putExtra("Genre",tempdb.getString(4));
                intentToshowbookinfo.putExtra("Publisher",tempdb.getString(8));
                intentToshowbookinfo.putExtra("Year of Publication",tempdb.getString(7));
                intentToshowbookinfo.putExtra("Price",tempdb.getString(5));
                intentToshowbookinfo.putExtra("Issued",tempdb.getString(6));


                startActivity(intentToshowbookinfo);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        books.clear();

    }

}
