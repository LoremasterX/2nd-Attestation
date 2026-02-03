package edu.aitu.campus.builder;

import edu.aitu.campus.model.Course;
import edu.aitu.campus.model.Student;
import edu.aitu.campus.model.StudentSchedule;

import java.util.ArrayList;
import java.util.List;

public class StudentScheduleBuilder {

    private Student student;
    private final List<Course> courses = new ArrayList<>();

    public StudentScheduleBuilder setStudent(Student student) {
        this.student = student;
        return this;
    }

    public StudentScheduleBuilder addCourse(Course course) {
        this.courses.add(course);
        return this;
    }

    public StudentSchedule build() {
        if (student == null) {
            throw new IllegalStateException("Student must be set");
        }
        return new StudentSchedule(student, courses);
    }
}