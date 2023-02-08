/* package com.example.myproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeachersList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_list);

        ArrayList<Teacher> teachers = new ArrayList<Teacher>();

        for (int i = 0; i < 50; i++)
        {

            teachers.add(new Teacher("name","userName","password", "email", "phone", true, 100, 40 ,true, "city");
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView_suudents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        TeacherAdapter teacherAdapter = new TeacherAdapter(teachers);

    }

}
*/