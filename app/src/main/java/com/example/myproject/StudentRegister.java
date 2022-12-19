package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class StudentRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        Toast.makeText(StudentRegister.this, "student", Toast.LENGTH_SHORT).show();



    }

    public void MoveTtudentHome(View view) {
        EditText ID = findViewById(R.id.editTextID);
        String id = ID.getText().toString();
        EditText SAdress = findViewById(R.id.editTextStudentAdress);
        String adress = SAdress.getText().toString();
        RadioGroup p = (RadioGroup)findViewById(R.id.radioGroup);
        int buttonId= p.getCheckedRadioButtonId();
        RadioButton selected=p.findViewById(buttonId);
        String text= (String)selected.getText();

        Toast.makeText(this, "8888"+text, Toast.LENGTH_SHORT).show();
        // read from intent previous data
        Intent i = getIntent();

        String mail = i.getStringExtra("email");


        // create student
        // add student to firebase -- teacher name empty

    }
}