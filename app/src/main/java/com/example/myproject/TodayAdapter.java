package com.example.myproject;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

//

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.TodayViewHolder> {

    private ArrayList<Lesson> lessons;
    private ArrayList<Lesson> teacherLessons;
    private String teacherPhone;
    private String studentEmail;

    private Context c;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public TodayAdapter(ArrayList<Lesson> lessons, ArrayList<Lesson> teacherLessons, Context c) {
        this.lessons = lessons;
        this.teacherLessons = teacherLessons;
        this.c = c;
    }

    @NonNull
    @Override
    public TodayAdapter.TodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate
        // return the view created
        View lessonView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_today, parent, false);
        //הלייאוט של כל שורה
        return new TodayAdapter.TodayViewHolder(lessonView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayAdapter.TodayViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat timeFormat_month_day_year = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < teacherLessons.size(); i++) {
            Lesson currentLesson = teacherLessons.get(i);
            String startTime = timeFormat.format(currentLesson.getStart());
            String lessonDate = timeFormat_month_day_year.format(currentLesson.getDate());

            if (startTime.equals(timeFormat.format(lesson.getStart())) && lessonDate.equals(timeFormat_month_day_year.format(lesson.getDate()))) {
                // Show lesson details - student name, etc.
                lesson.setLessonId(currentLesson.getLessonId());
                holder.studentName.setVisibility(View.VISIBLE);
                holder.studentName.setText(currentLesson.getStudentName());
                holder.cancel.setVisibility(View.VISIBLE);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Show dialog for requesting the amount paid
                        Dialog dialog = new Dialog(c);
                        dialog.setContentView(R.layout.alert_dialog_layout);
                        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                        Button buttonOk = dialog.findViewById(R.id.button_ok);
                        EditText editTextPaid = dialog.findViewById(R.id.paid);
                        EditText editTextTeacher = dialog.findViewById(R.id.teacherText);

                        buttonOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String price = editTextPaid.getText().toString();
                                String teacherText = editTextTeacher.getText().toString();

                                int amountPaid = Integer.parseInt(price);

                                firebaseFirestore.collection("students")
                                        .whereEqualTo("email", currentLesson.getStudentEmail())
                                        .get()
                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                    String documentId = documentSnapshot.getId();
                                                    Map<String, Object> studentData = documentSnapshot.getData();
                                                    long paid = (long) studentData.get("paid");
                                                    int currentPaid = Math.toIntExact(paid);
                                                    int updatedPaid = currentPaid + amountPaid;

                                                    firebaseFirestore.collection("students")
                                                            .document(documentId)
                                                            .update("paid", updatedPaid);

                                                    firebaseFirestore.collection("students")
                                                            .document(documentId)
                                                            .update("textTeacher", teacherText);
                                                }
                                            }
                                        });
                            }
                        });

                        dialog.show();

                       // dialog.cancel();
                    }
                });
            }
        }
        holder.time.setText(timeFormat.format(lesson.getStart()) + "-" + timeFormat.format(lesson.getFinish()));
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("lessons").document(lesson.getLessonId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            holder.taken.setVisibility(View.VISIBLE);
                            holder.cancel.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });
    }

    //search teacher lessons
    // show time
    // if taken ->
    //    show lesson details-> student name...
    // modify choose to cancel


    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public static class TodayViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public Button cancel;
        public View taken;
        public TextView studentName;

        public TodayViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time2);
            cancel = itemView.findViewById(R.id.cancel_button);
            taken = itemView.findViewById(R.id.taken2);
            studentName = itemView.findViewById(R.id.todayStudentName);
        }
    }
}









