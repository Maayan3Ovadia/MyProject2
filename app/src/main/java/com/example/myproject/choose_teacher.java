package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

                            TeacherAdapter teacherAdapter = new TeacherAdapter(teachers,choose_teacher.this);
                            recyclerView.setAdapter(teacherAdapter);

                        } else
                            Toast.makeText(choose_teacher.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void showTeacherDialog(Teacher currentTeacher, int position)
    {

        String title = "Teacher ___";
        String teacherDescription = currentTeacher.getName() + "\\" + currentTeacher.getPrice();

        // alert diaolog
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(teacherDescription);

        builder.setCancelable(false);
        builder.setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // upload to firebase///

                Toast.makeText(choose_teacher.this, currentTeacher.getName(),Toast.LENGTH_SHORT).show();
                dialog.cancel();

                setTeacherInFirebase(currentTeacher);


            }
            }

                );
        builder.setNegativeButton("Other",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                }

        );


        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.show();






    }

    private void setTeacherInFirebase(Teacher currentTeacher) {

        //1 get student from firebase
        // 2 set teacher name and phone
        // 3 update student in firebase

        FirebaseAuth mAuth  = FirebaseAuth.getInstance();
        String email = mAuth.getCurrentUser().getEmail();

        // get Student by email
        FirebaseFirestore fb = FirebaseFirestore.getInstance();

        fb.collection("students").whereEqualTo("email",email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        document.getReference().update("teacherName",currentTeacher.getName());
                        document.getReference().update("teacherPhone",currentTeacher.getPhone()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent i = new Intent(choose_teacher.this,StudentHomePage.class);
                                startActivity(i);
                            }
                        });

                    }

                            }
                }
            });
        //להראות לתלמיד רשימה של המורים רק אוטומוט/ידני
        //אם תלמיד רוצה ללמוד ידני/אוטומט, ברשימת המורים יהיו רק מורים שמלמדים על ידני
        //מורים לפי עיר
        //מורים שמקבלים לפי התיאוריה

    }
}