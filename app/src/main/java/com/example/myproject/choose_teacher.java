package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class choose_teacher extends AppCompatActivity {

    ArrayList<Teacher> teachers = new ArrayList<Teacher>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_teacher);
        getTeachers();
    }


    private void getTeachers() {
        // get teachers from firebase

        String mycity = getIntent().getStringExtra("address");//me.getAdress(); //העיר של התלמיד שנכנס
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("teachers").whereEqualTo("city", mycity).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                teachers.add(document.toObject(Teacher.class));
                            }
                            RecyclerView recyclerView = findViewById(R.id.recycler_choose_teacher);
                        //    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(choose_teacher.this);
                            recyclerView.setLayoutManager(new LinearLayoutManager(choose_teacher.this));

                            TeacherAdapter teacherAdapter = new TeacherAdapter(teachers);
                            recyclerView.setAdapter(teacherAdapter);

                        } else
                            Toast.makeText(choose_teacher.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}