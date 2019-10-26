package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class show_book extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Book1","B100","Author1","No"));
        books.add(new Book("Book2","B101","Author2","No"));
        books.add(new Book("Book3","B102","Author3","Yes"));
        books.add(new Book("Book4","B103","Author4","Yes"));
        books.add(new Book("Book5","B104","Author5","Yes"));
        books.add(new Book("Book6","B105","Author6","No"));
        books.add(new Book("Book7","B106","Author7","Yes"));
        books.add(new Book("Book8","B107","Author8","No"));
        books.add(new Book("Book9","B108","Author9","No"));

        ListView bookview = (ListView) findViewById(R.id.showBookListView);
        BookAdapter madapter = new BookAdapter(show_book.this,0,books);
        bookview.setAdapter(madapter);

    }
}
