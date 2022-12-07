package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void MoveToHome(View view)
    {
        EditText etEmail = findViewById(R.id.editEmailText);
        String mail = etEmail.getText().toString();
        EditText etPhone = findViewById(R.id.editPhone);
        String phone = etPhone.getText().toString();
        EditText etID = findViewById(R.id.editTextID);
        String textID = etID.getText().toString();
        EditText etName = findViewById(R.id.editName);
        String name = etEmail.getText().toString();
        EditText etPassword = findViewById(R.id.editPassword);
        String password = etPassword.getText().toString();
        EditText etUserName = findViewById(R.id.editUserName);
        String userName = etUserName.getText().toString();
        Toast.makeText(this,mail,Toast.LENGTH_SHORT).show();
        if(view.getId()==R.id.TeacherBotton)
        {
        Intent intent = new Intent(this, TeacherHomePage.class);
        startActivity(intent);
        }
        else {Intent intent = new Intent(this, StudentHomePage.class);
            startActivity(intent);}

    }

    public void register(View view)
    {

    }



}