package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Today extends AppCompatActivity {
    private Date date;
    private Date hour;
    ArrayList<Teacher> lessons = new ArrayList<Teacher>();
    private ArrayList<Lesson> lessonsList;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
            getDate();
            CalendarView calendarView = findViewById(R.id.calendarView2);
            date = new Date(calendarView.getDate());
            calendarView.setMinDate(calendarView.getDate());
        }

    public void getDate() {
        CalendarView calendarView = findViewById(R.id.calendarView2);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                createLessons(calendar.getTime());
            }
        });

        calendarView.setDate(System.currentTimeMillis());
    }
    public void recyclerView(ArrayList<Lesson> lessons_array, ArrayList<Lesson> teacherLessons) {
        RecyclerView lessons = findViewById(R.id.RecyclerTodayLesson);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        lessons.setLayoutManager(layoutManager);
        TodayAdapter todayAdapter = new TodayAdapter(lessons_array, teacherLessons);
        //todayAdapter.setStudentEmail(MainActivity.student.getEmail());
       //todayAdapter.setTeacherPhone(MainActivity.student.getTeacherPhone());
        lessons.setAdapter(todayAdapter);
    }

    public void createLessons(Date date) {
        lessonsList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");


        // Get current date and time
        Calendar calendar = Calendar.getInstance();
        Date currentDate = date;



        // Set initial start time
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 00);
        Date startTime = calendar.getTime();

        // Generate 19 lessons
        for (int i = 0; i < 19; i++) {
            // Create Lesson object and add to the list
            Lesson lesson = new Lesson();
            lesson.setStart(startTime);
            lesson.setLessonDuration(40);
            lesson.setDate(currentDate);
            lessonsList.add(lesson);
            // Increment start time for the next lesson
            calendar.add(Calendar.MINUTE, 40);
            startTime = calendar.getTime();
            lesson.setFinish(startTime);
        }
        if ((currentDate.getDay() + 1) == 7) {
//            clearRecyclerView();
            Toast.makeText(this, "saturday", Toast.LENGTH_SHORT).show();
        }
        else
            getTeacherDetails();
    }

    private void getTeacherDetails() {
        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = fbUser.getEmail();
        firebaseFirestore.collection("teachers").whereEqualTo("email",email).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Teacher teacher = queryDocumentSnapshots.getDocuments().get(0).toObject(Teacher.class);
                        getLessons(teacher);

                    }
                });

    }

//
//    public void clearRecyclerView()
//    {
//        RecyclerView lessons = findViewById(R.id.RecyclerTodayLesson);
//        lessons.setAdapter(null);
//    }


    public void getLessons(Teacher teacher)
    {
        firebaseFirestore.collection("lessons")
                .whereEqualTo("teacherPhone", teacher.getPhone())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Lesson> lessons = (ArrayList<Lesson>) task.getResult().toObjects(Lesson.class);
                            recyclerView(lessonsList, lessons);
                            SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy/HH:mm");

                            //      Toast.makeText(choose_lesson.this, "" + timeFormat.format(lessons.get(0).getStart()), Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(Today.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}