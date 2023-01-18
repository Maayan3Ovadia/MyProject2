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
        setContentView(R.layout.activity_students_list);

        ArrayList<Student> students = new ArrayList<Student>();

        for (int i = 0; i < 50; i++)
        {
            students.add(new Student("name", "userName", "password", "email", "phone", "id", true, "adress", 2 ));
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView_suudents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
       // RecyclerView.setLayoutManager(layoutManager);

       // ChatAdapter chatAdaptert = new ChatAdapter(students);

    }

}