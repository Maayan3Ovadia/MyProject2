/*package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>{

    private ArrayList<Teacher> teachers;

    public TeacherAdapter(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View teacherView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_teacher, parent, false);
        return new TeacherViewHolder(teacherView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        Teacher currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getName());
        holder.NextLTextView.setText(currentTeacher.getNextLesson());

    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    public static class TeacherViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nameTextView;
        public TextView NextLTextView;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.teacher_name);
            NextLTextView = itemView.findViewById(R.id.next_lesson);
        }

    }

}
*/