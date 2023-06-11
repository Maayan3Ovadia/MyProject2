package com.example.myproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

//

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.TodayViewHolder> {

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
    public TodayAdapter.TodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate
        // return the view created
        View lessonView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_today, parent, false);
        //הלייאוט של כל שורה
        return new TodayAdapter.TodayViewHolder(lessonView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayAdapter.TodayViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat timeFormat_month_day_year = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < teacherLessons.size(); i++) {
            String start = timeFormat.format(teacherLessons.get(i).getStart());
            String date = timeFormat_month_day_year.format(teacherLessons.get(i).getDate());
            if (start.equals(timeFormat.format(lesson.getStart())) && date.equals(timeFormat_month_day_year.format(lesson.getDate()))) {
                //show lesson details-> student name...
                lesson.setLessonId(teacherLessons.get(i).getLessonId());
                holder.studentName.setVisibility(View.VISIBLE);
                holder.studentName.setText(teacherLessons.get(i).getStudentName());

                holder.cancel.setVisibility(View.VISIBLE);


            }
        }
        holder.time.setText(timeFormat.format(lesson.getStart()) + "-" + timeFormat.format(lesson.getFinish()));
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("lessons").document(lesson.getLessonId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            holder.taken.setVisibility(View.VISIBLE);
                            holder.cancel.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });
    }

    //search teacher lessons
    // show time
    // if taken ->
    //    show lesson details-> student name...
    // modify choose to cancel


    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public static class TodayViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public Button cancel;
        public View taken;
        public TextView studentName;

        public TodayViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time2);
            cancel = itemView.findViewById(R.id.cancel_button);
            taken = itemView.findViewById(R.id.taken2);
            studentName = itemView.findViewById(R.id.todayStudentName);
        }
    }
}









