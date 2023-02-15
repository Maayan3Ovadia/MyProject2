package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StudentHomePage extends AppCompatActivity {

    ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    Student ne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        getMyDetails();

    }

    private void getMyDetails() {
        /*String city = FirebaseAuth.getInstance().getCurrentUser().get

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("teachers").whereEqualTo("city",city).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            me = task.getResult().getDocuments().get(0).toObject(Teacher.class);
                            getMyStudent();

                        }
                    }
                });*/

    }


}