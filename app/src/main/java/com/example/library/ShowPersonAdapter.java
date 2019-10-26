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

public class ShowPersonAdapter extends ArrayAdapter<Designation> {

    private Context mcontext;
    private List<Designation> people;

    public ShowPersonAdapter(@NonNull Context context, int resource, @NonNull List<Designation> objects) {
        super(context, resource, objects);
        mcontext= context;
        people = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null)
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.show_student_view, parent, false);

        TextView personId = convertView.findViewById(R.id.showStudentId);
        TextView personName = convertView.findViewById(R.id.showStudentName);
        TextView personEmail = convertView.findViewById(R.id.showStudentMailId);
        TextView personNumber = convertView.findViewById(R.id.showStudentPhoneNumber);
        TextView personDepartment = convertView.findViewById(R.id.showStudentDepartment);
        TextView personDesignation = convertView.findViewById(R.id.designationHolder);

        personId.setText(people.get(position).getmId());
        personEmail.setText(people.get(position).getmEmail());
        personName.setText(people.get(position).getmName());
        personNumber.setText(people.get(position).getMphoneNumber());
        personDepartment.setText(people.get(position).getmDepartment());
        personDesignation.setText("Faculty Id:");

        return convertView;
    }
}
