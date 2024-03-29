package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class choose_lesson extends AppCompatActivity {
    private Date date;
    private Date hour;
    ArrayList<Teacher> lessons = new ArrayList<Teacher>();
    private ArrayList<Lesson> lessonList;
    private final int ONE_HOUR = 60 * 60 * 1000;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

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
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                createLessons(calendar.getTime());
            }
        });
    }

    public void recyclerView(ArrayList<Lesson> lessons_array, ArrayList<Lesson> teacherLessons) {
        RecyclerView lessons = findViewById(R.id.recycler_lessons);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        lessons.setLayoutManager(layoutManager);
        LessonAdapter lessonAdapter = new LessonAdapter(lessons_array, teacherLessons, this);
        lessonAdapter.setStudentEmail(MainActivity.student.getEmail());
        lessonAdapter.setTeacherPhone(MainActivity.student.getTeacherPhone());
        lessonAdapter.setStudentName(MainActivity.student.getName());


        lessons.setAdapter(lessonAdapter);
    }

    public void createLessons(Date date) {
        lessonList = new ArrayList<>();
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
            lessonList.add(lesson);
            // Increment start time for the next lesson
            calendar.add(Calendar.MINUTE, 40);
            startTime = calendar.getTime();
            lesson.setFinish(startTime);
        }
        if ((currentDate.getDay() + 1) == 7) {
            clearRecyclerView();
            Toast.makeText(this, "saturday", Toast.LENGTH_SHORT).show();
        } else
            getLessons();
    }


    public void clearRecyclerView() {
        RecyclerView lessons = findViewById(R.id.recycler_lessons);
        lessons.setAdapter(null);
    }


    public void getLessons() {
        firebaseFirestore.collection("lessons")
                .whereEqualTo("teacherPhone", MainActivity.student.getTeacherPhone())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Lesson> lessons = (ArrayList<Lesson>) task.getResult().toObjects(Lesson.class);
                            recyclerView(lessonList, lessons);
                            SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy/HH:mm");

                            //      Toast.makeText(choose_lesson.this, "" + timeFormat.format(lessons.get(0).getStart()), Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(choose_lesson.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void setAlarm(Lesson lesson) {
        // get date from lesson
        // intent with broadcast
        // pending intent -> future use of the intent by system
        // alarm manager -> set time according to lesson

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 123, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Date d = lesson.getDate();
        Date s = lesson.getStart();
        int start = s.getHours();

        String res = d.toString();
        String[] dateInString = res.split(" ");


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());


        calendar.set(Calendar.YEAR, Integer.valueOf(dateInString[dateInString.length - 1]));
        calendar.set(Calendar.MONTH, Integer.valueOf(6));
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dateInString[2]));


        calendar.set(Calendar.HOUR_OF_DAY, s.getHours());
        calendar.set(Calendar.MINUTE, s.getMinutes());


        //  calendar.set(d.getYear(),d.getMonth(),d.getDay(),start,s.getMinutes());


        //    alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
        //          SystemClock.elapsedRealtime() +
        //                5* 1000, pendingIntent);


        alarmManager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME, calendar.getTimeInMillis() - System.currentTimeMillis() - 140 * 60 * 1000, pendingIntent);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() - 1000 * 60 * 170, pendingIntent);

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10 * 1000, pendingIntent);

    }


    //אי אפשר לבחור את יום שבת
    //if((currentDate.getDay()+1)==7)
    //    Toast.makeText(this, "saturday", Toast.LENGTH_SHORT).show();

    //Toast.makeText(this, ""+ (currentDate.getDay()+1) , Toast.LENGTH_SHORT).show();

    //if(calendar.get(calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
    //{

    //Toast.makeText(this, "saturday", Toast.LENGTH_SHORT).show();
    //}



    /*
    public boolean isDateSelectable(Date date) {
        Calendar cal = Calendar.getInstance();
        boolean isSelecteable=true;
        cal.setTime(date);
        int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
        //disable if saturday
        if(dayOfWeek==Calendar.SATURDAY)
            isSelecteable=false;
        return isSelecteable;
    }
     */

    /*
    public void showLessonDialog(Lesson currentLesson, int position)
    {

        // alert dialog
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle(""+currentLesson.getStart());
        builder.setMessage("האם ברצונך לבחור שעה זו?");

        builder.setCancelable(false);
        builder.setPositiveButton("בחר",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // upload to firebase///
                        dialog.cancel();
                    }
                }
        );
        builder.setNegativeButton("בחר שעה אחרת",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                }

        );

}*/
}


// student email -> we have in fb user
// teacher phone we have in sp
// 1. get the date from calendar view
// 2. get lessons for the date using teacher phone..
// 3. set recycler view -> and display lessons with status
