package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        Toast.makeText(StudentRegister.this, "student", Toast.LENGTH_SHORT).show();

    }

    public void MoveToStudentRegister(View view) {
        EditText ID = findViewById(R.id.editTextID);
        String id = ID.getText().toString();
        EditText SAdress = findViewById(R.id.editTextStudentAdress);
        String adress = SAdress.getText().toString();
        RadioGroup p = (RadioGroup)findViewById(R.id.radioGroup);
        int buttonId= p.getCheckedRadioButtonId();
        RadioButton selected=p.findViewById(buttonId);
        String text= (String)selected.getText();

        // info from previous
        Intent i = getIntent();
        String email = i.getStringExtra("mail");
        String phone = i.getStringExtra("phone");
        String password = i.getStringExtra("password");
        String userName = i.getStringExtra("userName");
        String name = i.getStringExtra("name");

        /*
             intent.putExtra("phone",phone);
                    intent.putExtra("name",name);
                    intent.putExtra("password",password);
                    intent.putExtra("userName",userName);
         */

        Toast.makeText(this, "8888"+text, Toast.LENGTH_SHORT).show();
        // read from intent previous data


        // create student
        // add student to firebase -- teacher name empty

        Student s = new Student(name, userName, password, email, phone, id, true, adress ,0, 4);

        FirebaseFirestore db  = FirebaseFirestore.getInstance();

        db.collection("students").add(s).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(StudentRegister.this,"upload success",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(StudentRegister.this,StudentHomePage.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(StudentRegister.this,"upload failed",Toast.LENGTH_SHORT).show();


            }
        });






    }
}