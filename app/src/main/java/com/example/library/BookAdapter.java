package com.example.library;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.zip.Inflater;

public class BookAdapter extends ArrayAdapter<Book> {

    private Context mcontext;
    private List<Book> mbook;

    public BookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        mcontext = context;
        mbook = objects;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null)
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.show_book_view, parent, false);

        TextView bookTitle = convertView.findViewById(R.id.showBookTitle);
        TextView bookId = convertView.findViewById(R.id.showBookId);
        TextView bookAuthor = convertView.findViewById(R.id.showBookAuthor);
        TextView bookIssued = convertView.findViewById(R.id.showBookissued);

        bookTitle.setText(mbook.get(position).getMbookTitle());
        bookAuthor.setText(mbook.get(position).getMbookAuthor());
        bookId.setText(mbook.get(position).getMbookId());
        bookIssued.setText(mbook.get(position).getMissued());

        return convertView;
    }
}
