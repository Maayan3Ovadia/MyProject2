package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TeacherHomePage extends AppCompatActivity
{
    ArrayList<Student> students = new ArrayList<Student>();
    Teacher me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_page);

        // display recyclerview of students...

        /// futre - will get from firebase


        getMyDetails();



    }

    private void getMyDetails() {
        // EmAIL
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("teachers").whereEqualTo("email",email).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            me = task.getResult().getDocuments().get(0).toObject(Teacher.class);
                            getMyStudents();

                        }
                    }
                });

    }

    private void getMyStudents() {
     // get my students from firebase!

        String phoneNumber = me.getPhone();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("students").whereEqualTo("teacherPhone",phoneNumber).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                students.add(document.toObject(Student.class));
                            }

                            RecyclerView recyclerView = findViewById(R.id.recyclerView1);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TeacherHomePage.this);
                            recyclerView.setLayoutManager(layoutManager);

                            StudentAdapter studentAdapter = new StudentAdapter(students);
                            recyclerView.setAdapter(studentAdapter);



                        }

                        else
                            Toast.makeText(TeacherHomePage.this,"FAILED",Toast.LENGTH_SHORT).show();
                    }
                });





    }


}