package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{

    private ArrayList<Student> students;

    public StudentAdapter(ArrayList<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View studentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_student, parent, false);
        return new StudentViewHolder(studentView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student currentStudent = students.get(position);
        holder.nameTextView.setText(currentStudent.getName());
        holder.NextLTextView.setText(currentStudent.getNextLesson());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nameTextView;
        public TextView NextLTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.student_name);
            NextLTextView = itemView.findViewById(R.id.next_lesson);
        }

    }

}
