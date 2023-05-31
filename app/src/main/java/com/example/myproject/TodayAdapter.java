package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
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

public class TodayAdapter {



    public static class TodayViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public Button choose;
        public View taken;

        public TodayViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time2);
            choose = itemView.findViewById(R.id.choose2);
            taken = itemView.findViewById(R.id.taken2);
        }
    }





}



















/*
        private ArrayList<Lesson> lessons;
        private ArrayList<Lesson> teacherLessons;
        private String teacherPhone;
        private String studentEmail;
        private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        public void setTeacherPhone(String teacherPhone) {
            this.teacherPhone = teacherPhone;
        }

        public void setStudentEmail(String studentEmail) {
            this.studentEmail = studentEmail;
        }

    public TodayAdapter(ArrayList<Lesson> lessons, ArrayList<Lesson> teacherLessons) {
            this.lessons = lessons;
            this.teacherLessons = teacherLessons;
        }

        @NonNull
        @Override
        public LessonAdapter.LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View lessonView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_lessons, parent, false);
            //הלייאוט של כל שורה
            return new LessonAdapter.LessonViewHolder(lessonView);
        }

        @Override
        public void onBindViewHolder(@NonNull TodayViewHolder holder, int position) {
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
                    firebaseFirestore.collection("lessons").add(lesson).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()){
                                holder.taken.setVisibility(View.VISIBLE);
                                holder.choose.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            });
        }

    */