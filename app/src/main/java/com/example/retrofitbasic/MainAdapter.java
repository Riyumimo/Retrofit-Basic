package com.example.retrofitbasic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>   {
    private List<MainModel.Result> results;
    private OnAdapterListener listener ;

    public MainAdapter(List<MainModel.Result> result, OnAdapterListener listener){
        this.results = result;
        this.listener = listener;

    }
    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       MainModel.Result result = results.get(position);
       holder.textView.setText(result.getTitle());
        Picasso.get().load(result.getImage())
                .fit().centerCrop()
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onclick(result);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);

        }
    }

    public void setData(List<MainModel.Result> data){
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }

    interface OnAdapterListener{
        void onclick(MainModel.Result result);
        }
    }

