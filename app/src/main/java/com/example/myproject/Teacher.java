package com.example.myproject;

public class Teacher
{

    private String name;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private boolean Needteoria;
    private int price;
    private int minutes; //כמה דקות כל שיעור
    private boolean isAuto; //true- the car is automatic, false- הרכב ידני
    private String city;


    public Teacher(String name, String userName, String password, String email, String phone, boolean needteoria, int price, int minutes, boolean isAuto, String city) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.Needteoria = needteoria;
        this.price = price;
        this.minutes = minutes;
        this.isAuto = isAuto;
        this.city = city;
    }


    public Teacher() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public boolean isNeedteoria() {
        return Needteoria;
    }

    public void setNeedteoria(boolean needteoria) {
        Needteoria = needteoria;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void setAuto(boolean auto) {
        isAuto = auto;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
