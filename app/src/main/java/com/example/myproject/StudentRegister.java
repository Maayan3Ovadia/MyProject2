package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

    public void MoveToStudentHome(View view) {
        EditText ID = findViewById(R.id.editTextID);
        String id = ID.getText().toString();

        EditText SAddress = findViewById(R.id.editTextStudentAddress);
        String address = SAddress.getText().toString();

        RadioGroup p = (RadioGroup)findViewById(R.id.radioGroup);
        int buttonId= p.getCheckedRadioButtonId();
        RadioButton selected =p.findViewById(buttonId);
        String text= (String)selected.getText();


        boolean teoria = false;
        if(text.equalsIgnoreCase("עשיתי תיאוריה"))
            teoria = true;
        Toast.makeText(StudentRegister.this, ""+address, Toast.LENGTH_SHORT).show();


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

        Toast.makeText(this, "88888 "+text, Toast.LENGTH_SHORT).show();
        // read from intent previous data

        // create student
        // add student to firebase -- teacher name empty

        Student s = new Student(name, userName, password, email, phone, id, teoria, address ,0, 4);

        FirebaseFirestore db  = FirebaseFirestore.getInstance();

        db.collection("students").add(s).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful())
                {
                    String ref = task.getResult().getPath();

                    SharedPreferences sp =StudentRegister.this.getSharedPreferences("details",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();

                    editor.putString("reference",ref);
                    editor.apply();


                    Toast.makeText(StudentRegister.this,"upload success",Toast.LENGTH_SHORT).show();

                    MainActivity.student = s;
                    Intent i = new Intent(StudentRegister.this,choose_teacher.class);
                    i.putExtra("address",address);
                    startActivity(i);
                }
                else
                    Toast.makeText(StudentRegister.this,"upload failed",Toast.LENGTH_SHORT).show();

            }
        });






    }
}