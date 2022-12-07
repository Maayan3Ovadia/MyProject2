package com.example.myproject;

public class Teacher {

    private String name;
    private int price;
    private int minutes; //כמה דקות כל שיעור
    private boolean isAuto; //true- the car is automatic, false- הרכב ידני

    public Teacher()
    {

    }

    public Teacher(String name, int price, int minutes) {
        this.name = name;
        this.price = price;
        this.minutes = minutes;
        this.isAuto = true;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setIsAuto(boolean isAuto) {
        this.isAuto = isAuto;
    }
}
