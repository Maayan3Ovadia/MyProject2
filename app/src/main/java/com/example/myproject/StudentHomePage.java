package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StudentHomePage extends AppCompatActivity {

    Student me;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    //String s = me.getTeacherName();
    //DocumentReference docRef = firebaseFirestore.collection("students").document("SF");
    //docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);
        me = (Student) (getIntent().getSerializableExtra("student"));
//כל התכונות מועברות באקטרה ליצור עצם מסוג תלמיד באמצעות כל התכונות
    }


    public void moveToChooseLesson(View view) {
        Intent intent = new Intent(this, choose_lesson.class);
        startActivity(intent);
    }






    /*

        Student s = (Student) getIntent().getSerializableExtra("student"); //Obtaining data

        TextView tName = findViewById(R.id.myTeacher);
        tName.setText(tName.getText()+" "+s.getName());

        TextView lessonsNum = findViewById(R.id.numLessons);
        lessonsNum.setText(lessonsNum.getText()+ " " +s.getCurrentLesson());

        TextView nextLessons = findViewById(R.id.nextLessons);
        nextLessons.setText(nextLessons.getText()+" 5/4/2022");
        //nextLessons.setText(nextLessons.getText()+" "+s.getNextLesson());
     */





/*
        public TextView nameTextView;
        public TextView priceTextView;
        public TextView lessonTime;
        public Button choose;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.teacher_name);
            priceTextView = itemView.findViewById(R.id.lesson_price);
            lessonTime = itemView.findViewById(R.id.lesson_time);
            choose = itemView.findViewById(R.id.choose_button);
        }

*/

/*
    private void getMyDetails() {
        String city =getIntent().getStringExtra("address");// me.getAdress();
        Toast.makeText(this, ""+city, Toast.LENGTH_SHORT).show();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("teachers").whereEqualTo("city", city).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            me = task.getResult().getDocuments().get(0).toObject(Student.class);
                            getTeachers();

                        }
                    }
                });
    }*/


}




/*
        public TextView nameTextView;
        public TextView priceTextView;
        public TextView lessonTime;
        public Button choose;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.teacher_name);
            priceTextView = itemView.findViewById(R.id.lesson_price);
            lessonTime = itemView.findViewById(R.id.lesson_time);
            choose = itemView.findViewById(R.id.choose_button);
        }

*/

/*
    private void getMyDetails() {
        String city =getIntent().getStringExtra("address");// me.getAdress();
        Toast.makeText(this, ""+city, Toast.LENGTH_SHORT).show();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("teachers").whereEqualTo("city", city).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            me = task.getResult().getDocuments().get(0).toObject(Student.class);
                            getTeachers();

                        }
                    }
                });
    }*/
