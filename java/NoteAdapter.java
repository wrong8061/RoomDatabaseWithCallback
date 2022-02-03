package com.example.architectureapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    List<Note> notes=new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note cnote=notes.get(position);
        holder.tvTitle.setText(cnote.getTitle());
        holder.tvDescription.setText(cnote.getDescription());
        holder.tvPriority.setText(String.valueOf(cnote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    public void setNotes(List<Note> notes){
        this.notes=notes;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvDescription,tvPriority;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            tvPriority=itemView.findViewById(R.id.tvPriority);
        }
    }
}
