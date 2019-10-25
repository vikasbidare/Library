package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.DataClassViewHolder> {

    private Context context;
    private ArrayList<DataClass> items;

    public ViewAdapter(Context context, ArrayList<DataClass> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public ViewAdapter.DataClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View DataClassViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new DataClassViewHolder(DataClassViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.DataClassViewHolder holder, final int position) {

        holder.imageView.setImageResource(items.get(position).getImage());
        holder.textview.setText(items.get(position).getTitle());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = items.get(position).getIntent();
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class DataClassViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textview;
        CardView cardview;
        public DataClassViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImageHolder);
            textview=itemView.findViewById(R.id.itemTextHolder);
            cardview = itemView.findViewById(R.id.cardView);
        }
    }
}
