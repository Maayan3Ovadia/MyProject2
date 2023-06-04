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
    public void onBindViewHolder(@NonNull TodayAdapter.TodayViewHolder holder, int position)
    {
        Lesson currentLesson = lessons.get(position);



    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public static class TodayViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public Button choose;
        public View taken;

        public TodayViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            choose = itemView.findViewById(R.id.choose);
            taken = itemView.findViewById(R.id.taken);
        }
    }
}









