package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class StudentsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_page);

        ArrayList<Student> students = new ArrayList<Student>();

        for (int i = 0; i < 50; i++)
        {
            students.add(new Student("name", "userName", "password", "email", "phone", "id", true, "adress", 0, 3));
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       StudentAdapter studentAdapter = new StudentAdapter(students);

    }

}