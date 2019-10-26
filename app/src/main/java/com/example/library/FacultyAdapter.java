package com.example.library;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FacultyAdapter extends ArrayAdapter<History> {

    private Context mcontext;
    private List<History> mhistory;

    public FacultyAdapter(@NonNull Context context, int resource, @NonNull List<History> objects) {
        super(context, resource, objects);
        mcontext = context;
        mhistory = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null)
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.show_history_view, parent, false);

        TextView facultyId = convertView.findViewById(R.id.showHistoryStudentId);
        TextView bookId = convertView.findViewById(R.id.showHistoryBookId);
        TextView returndate = convertView.findViewById(R.id.showHistoryStudentReturnDate);
        TextView issueddate = convertView.findViewById(R.id.showHistoryStudentIssueDate);
        TextView fineamount = convertView.findViewById(R.id.showHistoryFineAmt);
        TextView issuertag=convertView.findViewById(R.id.issuerID);
        issuertag.setText("Faculty Id:");

        facultyId.setText(mhistory.get(position).getmIssuerId());
        returndate.setText(mhistory.get(position).getMreturnDate());
        bookId.setText(mhistory.get(position).getmBookId());
        issueddate.setText(mhistory.get(position).getmIssueDate());
        fineamount.setText(mhistory.get(position).getmFineAmount());

        return convertView;
    }
}
