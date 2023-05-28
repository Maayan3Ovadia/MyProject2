package com.example.myproject;

import java.util.Date;

public class Lesson
{
    private String teacherPhone;
    private Date date;
    private Date start;
    private Date finish;
    private int lessonDuration;
    private String studentEmail;

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public Lesson(String teacherPhone, Date date, Date start, int lessonDuration, String studentEmail) {
        this.teacherPhone = teacherPhone;
        this.date = date;
        this.start = start;
        this.lessonDuration = lessonDuration;
        this.studentEmail = studentEmail;
    }
    public Lesson(){}
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
}
