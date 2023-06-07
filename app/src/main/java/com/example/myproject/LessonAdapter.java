package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private ArrayList<Lesson> lessons;
    private ArrayList<Lesson> teacherLessons;
    private String teacherPhone;
    private String studentEmail;
    private String studentName;
    private choose_lesson lessonActivity;

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public LessonAdapter(ArrayList<Lesson> lessons, ArrayList<Lesson> teacherLessons,choose_lesson l) {
        this.lessons = lessons;
        this.teacherLessons = teacherLessons;
        this.lessonActivity = l;
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
        SimpleDateFormat timeFormat_month_day_year = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < teacherLessons.size(); i++) {
            String start = timeFormat.format(teacherLessons.get(i).getStart());
            String date = timeFormat_month_day_year.format(teacherLessons.get(i).getDate());
            if (start.equals(timeFormat.format(lesson.getStart())) && date.equals(timeFormat_month_day_year.format(lesson.getDate()))) {
                holder.taken.setVisibility(View.VISIBLE);
                holder.choose.setVisibility(View.GONE);
            }


        }
        holder.time.setText(timeFormat.format(lesson.getStart()) + "-" + timeFormat.format(lesson.getFinish()));
        holder.choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lesson.setTeacherPhone(teacherPhone);
                lesson.setStudentEmail(studentEmail);
                lesson.setStudentName(studentName);
                firebaseFirestore.collection("lessons").add(lesson).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()){
                            holder.taken.setVisibility(View.VISIBLE);
                            holder.choose.setVisibility(View.GONE);

                            LessonAdapter.this.lessonActivity.setAlarm(lesson);


                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public void setStudentName(String name) {
            this.studentName =name;
    }

    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public Button choose;
        public View taken;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            choose = itemView.findViewById(R.id.choose);
            taken = itemView.findViewById(R.id.taken);
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
