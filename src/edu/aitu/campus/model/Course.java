package edu.aitu.campus.model;

public class Course {
    private int id;
    private String title;
    private int capacity;
    private String day;
    private String time;

    public Course(int id, String title, int capacity, String day, String time){
        this.id=id;
        this.title=title;
        this.capacity=capacity;
        this.day=day;
        this.time=time;
    }

    public Course(String title, int capacity, String day, String time){
        this.title=title;
        this.capacity=capacity;
        this.day=day;
        this.time=time;
    }
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public int getCapacity(){
        return capacity;
    }
    public String getDay(){
        return day;
    }
    public String getTime(){
        return time;
    }
}
