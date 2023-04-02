package com.example.myproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {

    private ArrayList<Teacher> teachers;
    private Context c;

    public TeacherAdapter(ArrayList<Teacher> teachers,Context c) {
        this.teachers = teachers;
        this.c = c;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View teacherView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_teacher, parent, false);


        return new TeacherAdapter.TeacherViewHolder(teacherView);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        Teacher currentTeacher = teachers.get(position);
        holder.nameTextView.setText(currentTeacher.getName());
        holder.priceTextView.setText("" + currentTeacher.getPrice());





    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    public  class TeacherViewHolder extends RecyclerView.ViewHolder  {

        public TextView nameTextView;
        public TextView priceTextView;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.teacher_name);
            priceTextView = itemView.findViewById(R.id.lesson_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Teacher t = teachers.get(position);

                    // show dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setMessage("Name: " + t.getName() + " city: " + t.getCity()+ " price: " + t.getPrice())
                            .setPositiveButton("register", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // START THE GAME!

                                    ((choose_teacher)c).updateStudentTeacherName(t.getName(),t.getPhone());


                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });
                    // Create the AlertDialog object and return it
                    builder.create().show();



                }
            });
        }


    }

}
