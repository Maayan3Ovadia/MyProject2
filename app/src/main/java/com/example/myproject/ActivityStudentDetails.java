package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityStudentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Student s = (Student) getIntent().getSerializableExtra("student"); //Obtaining data

            Toast.makeText(this, "" + s.getEmail() + "," + s.getPhone(), Toast.LENGTH_SHORT).show();


            TextView tName = findViewById(R.id.textname);
            tName.setText(tName.getText()+" "+s.getName());

            TextView tNext = findViewById(R.id.Textnextlesson);
            tNext.setText(tNext.getText()+" "+s.getNextLesson());

            TextView tNum = findViewById(R.id.textnumlessons);
            tNum.setText(tNum.getText()+" "+s.getCurrentLesson());

            TextView tAdress = findViewById(R.id.textadress);
            tAdress.setText(tAdress.getText()+" "+s.getAddress());

            TextView tPhone = findViewById(R.id.textPhone);
            tPhone.setText(tPhone.getText()+" "+s.getPhone());
        }
    }
}