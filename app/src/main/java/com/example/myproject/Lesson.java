package com.example.myproject;

import java.util.Date;
import java.util.UUID;

public class Lesson {
    private String teacherPhone;
    private Date date;
    private Date start;
    private Date finish;
    private int lessonDuration;
    private String studentEmail;
    private String studentName;

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    private String lessonId;

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public Lesson(String teacherPhone, Date date, Date start, int lessonDuration, String studentEmail, String studentName) {
        this.teacherPhone = teacherPhone;
        this.date = date;
        this.start = start;
        this.lessonDuration = lessonDuration;
        this.studentEmail = studentEmail;
        this.studentName = studentName;
    }

    public Lesson() {
        this.lessonId = UUID.randomUUID().toString();
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public Date getDate() {
        return date;
    }

    public Date getStart() {
        return start;
    }

    public int getLessonDuration() {
        return lessonDuration;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setLessonDuration(int lessonDuration) {
        this.lessonDuration = lessonDuration;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
