package edu.aitu.campus.model;

import java.util.List;

public class StudentSchedule {

    private final Student student;
    private final List<Course> courses;

    public StudentSchedule(Student student, List<Course> courses) {
        this.student = student;
        this.courses = courses;
    }

    public Student getStudent() {
        return student;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
