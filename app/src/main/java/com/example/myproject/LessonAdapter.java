package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private ArrayList<Lesson> lessons;

    public LessonAdapter(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lessonView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_lessons, parent, false);
        //הלייאוט של כל שורה
        return new LessonViewHolder(lessonView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        holder.time.setText(timeFormat.format(lesson.getStart()) + "-" + timeFormat.format(lesson.getFinish()));
        holder.choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public Button choose;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            choose = itemView.findViewById(R.id.choose);
        }
    }


}














/*
    public LessonAdapter.LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lessonView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_lesson, parent, false);
        return new LessonAdapter.LessonViewHolder(lessonView);
    }

    public void onBindViewHolder(@NonNull LessonAdapter.LessonViewHolder holder, int position) {
        Lesson currentStudent = lessons.get(position);
        //holder.nameTextView.setText(currentStudent.getName());
        //holder.NextLTextView.setText("" + currentStudent.getNextLesson());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tHomePage.showStudentDetails(currentStudent,position);
            }
        });
    }*/
