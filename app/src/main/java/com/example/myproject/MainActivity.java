package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // register user with Firebase
        // authentication
        FirebaseUser fbUser = auth.getCurrentUser();
        if(fbUser != null )
        {
            moveToActivity();
        }



    }

    //String listview = findViewById<ListView>(R.id.myListView)




    public void MoveToHome(View view)
    {
        // reach here only if first time registered
        EditText etEmail = findViewById(R.id.editEmailText);
        String mail = etEmail.getText().toString();
        EditText etPhone = findViewById(R.id.editPhone);
        String phone = etPhone.getText().toString();
     //   EditText etID = findViewById(R.id.editTextID);
       // String textID = etID.getText().toString();
        EditText etName = findViewById(R.id.editName);
        String name = etEmail.getText().toString();
        EditText etPassword = findViewById(R.id.editPassword);
        String password = etPassword.getText().toString();
        EditText etUserName = findViewById(R.id.editUserName);
        String userName = etUserName.getText().toString();
        Toast.makeText(this,mail,Toast.LENGTH_SHORT).show();

        SharedPreferences sp = this.getSharedPreferences("details",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if(view.getId() == R.id.TeacherBotton)
        {

            editor.putBoolean("isTeacher",true);

        }
        else {
            editor.putBoolean("isTeacher", false);
        }
        editor.apply();





        // this means we need to register
        // stage 1 - register with Authentication of firebase
        auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
    {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
                    // stage 2
                    // create profile
                    Intent intent = new Intent();
                    intent.putExtra("mail",mail);
                    intent.putExtra("phone",phone);
                    intent.putExtra("name",name);
                    intent.putExtra("password",password);
                    intent.putExtra("userName",userName);

                    if(view.getId() == R.id.TeacherBotton)
                    {
                        intent.setClass(MainActivity.this,TeacherHomePage.class);
                    }
                    else
                    {
                        intent.setClass(MainActivity.this, StudentRegister.class);
                    }
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this,"fail " + task.getException(),Toast.LENGTH_SHORT).show();

            }
        });

        }

    private void moveToActivity()
    {
        // if regsitered -> this means that Sharedpref contains value for isTeracher
        SharedPreferences sp = this.getSharedPreferences("details",MODE_PRIVATE);
        boolean isTeacher = sp.getBoolean("isTeacher",true);


        if(isTeacher)
        {

        }
        else
        {

        }

    }

    public void register(View view)
    {

    }


    public void registerAsTeacher(View view) {


    }
}