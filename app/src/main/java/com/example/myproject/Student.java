package com.example.myproject;

public class Student
{
    private String name;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String id;
    private boolean teoria; //נכון- עשה תיאוריה, לא נכון- לא עשה תיאוריה
    private String adress;
    private int currentLesson;
    private String teacherName;
    private String teacherPhone;
    private int nextLesson;

    public Student(String name, String userName, String password, String email, String phone, String id, boolean teoria, String adress, int currentLesson, int nextLesson)
    {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.teoria = teoria;
        this.adress = adress;
        this.currentLesson = currentLesson;
        this.nextLesson = nextLesson;
        this.teacherName="";
        this.teacherPhone="";
    }

    public Student()
    {

    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isTeoria() {
        return teoria;
    }

    public void setTeoria(boolean teoria) {
        this.teoria = teoria;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getCurrentLesson() {
        return currentLesson;
    }

    public void setCurrentLesson(int currentLesson) {
        this.currentLesson = currentLesson;
    }

    public int getNextLesson() {
        return nextLesson;
    }

    public void setNextLesson(int nextLesson) {
        this.nextLesson = nextLesson;
    }
}
