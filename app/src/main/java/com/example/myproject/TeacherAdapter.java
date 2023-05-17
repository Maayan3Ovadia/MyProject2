package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {

    private ArrayList<Teacher> teachers;

    private choose_teacher cTeacher;

    public TeacherAdapter(ArrayList<Teacher> teachers,choose_teacher cteacher) {
        this.teachers = teachers;
        cTeacher = cteacher;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View teacherView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_teacher, parent, false);
        return new TeacherAdapter.TeacherViewHolder(teacherView);
    }


    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder,int position) {
        Teacher currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getName());
        holder.priceTextView.setText("" + currentTeacher.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cTeacher.showTeacherDialog(currentTeacher,position);
            }
        });
    }
    /*
    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        Teacher currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getName());
        holder.priceTextView.setText("" + currentTeacher.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cTeacher.showTeacherDialog(currentTeacher,position);
            }
        });
    }

     */

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    public static class TeacherViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView priceTextView;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.teacher_name);
            priceTextView = itemView.findViewById(R.id.lesson_price);
        }
    }
}
