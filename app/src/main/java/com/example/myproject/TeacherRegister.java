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

public class TeacherRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
    }

    public void moveToTeacherRegister(View view)
    {
        EditText TCity = findViewById(R.id.editTextTCity);
        String city = TCity.getText().toString();
        RadioGroup rg = (RadioGroup)findViewById(R.id.EditRadioGroup);
        int buttonId = rg.getCheckedRadioButtonId();
        RadioButton selected = rg.findViewById(buttonId);
        String text = selected.getText().toString();
        EditText TPrice = findViewById(R.id.editTextTCity);
        int price = Integer.parseInt(TPrice.getText().toString());
        EditText TMinuts = findViewById(R.id.editTMinuts);
        int minuts = Integer.parseInt(TMinuts.getText().toString());


        // info from previous
        Intent i = getIntent();
        String email = i.getStringExtra("mail");
        String phone = i.getStringExtra("phone");
        String password = i.getStringExtra("password");
        String userName = i.getStringExtra("userName");
        String name = i.getStringExtra("name");


        // create teacher
        // add teacher to firebase

        Teacher t = new Teacher(name,userName,password, email, phone, true, price, minuts ,true, city);

        FirebaseFirestore db  = FirebaseFirestore.getInstance();

        db.collection("teachers").add(t).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(TeacherRegister.this,"upload success",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(TeacherRegister.this,TeacherHomePage.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(TeacherRegister.this,"upload failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
