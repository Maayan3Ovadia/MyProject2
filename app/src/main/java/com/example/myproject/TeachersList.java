package com.example.myproject;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeachersList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_teacher);

        ArrayList<Teacher> teachers = new ArrayList<Teacher>();

        for (int i = 0; i < 50; i++)
        {
            teachers.add(new Teacher("name","userName","password", "email", "phone", true, 100, 40 ,true, "city"));
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_choose_teacher);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       // TeacherAdapter teacherAdapter = new TeacherAdapter(teachers);

    }

}