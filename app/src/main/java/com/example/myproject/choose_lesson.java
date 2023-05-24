package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class choose_lesson extends AppCompatActivity {
    private Date date;
    private Date hour;
    ArrayList<Teacher> lessons = new ArrayList<Teacher>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lesson);
        getDate();
        CalendarView calendarView = findViewById(R.id.calendarView);
        date = new Date(calendarView.getDate());
        calendarView.setMinDate(calendarView.getDate());
    }

    public void getDate() {
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                date = new Date(dayOfMonth, month, year);
                createLessons();
            }
        });
    }

    public void recyclerView(ArrayList<Lesson>lessons_array) {
        RecyclerView lessons = findViewById(R.id.recycler_lessons);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        lessons.setLayoutManager(layoutManager);
        LessonAdapter lessonAdapter = new LessonAdapter(lessons_array);
        lessons.setAdapter(lessonAdapter);
    }

    public void createLessons() {
            ArrayList<Lesson> lessonList = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            // Get current date and time
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();

            // Set initial start time
            calendar.set(Calendar.HOUR_OF_DAY, 7);
            calendar.set(Calendar.MINUTE, 30);
            Date startTime = calendar.getTime();

            // Generate 10 lessons
            for (int i = 0; i < 10; i++) {
                // Create Lesson object and add to the list
                Lesson lesson = new Lesson();
                lesson.setStart(startTime);
                lesson.setLessonDuration(40);
                lesson.setDate(currentDate);
                lessonList.add(lesson);
                // Increment start time for the next lesson
                calendar.add(Calendar.MINUTE, 40);
                startTime = calendar.getTime();
                lesson.setFinish(startTime);

            }
        recyclerView(lessonList);


    }
    }


    // student email -> we have in fb user
    // teacher phone we have in sp
    // 1. get the date from calendar view
    // 2. get lessons for the date using teacher phone..
    // 3. set recycler view -> and display lessons with status












/*
    private void getLessons() {

        String date = getIntent().getStringExtra("address");
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("lessons").whereEqualTo("date", date).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                lessons.add(document.toObject(Lesson.class));
                            }
                            RecyclerView recyclerView = findViewById(R.id.recycler_choose_lesson);
                            recyclerView.setLayoutManager(new LinearLayoutManager(choose_lesson.this));

                            LessonAdapter lessonAdapter = new TeacherAdapter(lessons,choose_lesson.this);
                            recyclerView.setAdapter(lessonAdapter);

                        } else
                            Toast.makeText(choose_lesson.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                });

    }*/


    //

