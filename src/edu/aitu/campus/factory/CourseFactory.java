package edu.aitu.campus.factory;

import edu.aitu.campus.model.*;

public class CourseFactory {

    public static Course createCourse(
            String type,
            String title,
            int capacity,
            String day,
            String time
    ) {
        switch (type.toUpperCase()) {
            case "LECTURE":
                return new LectureCourse(title, capacity, day, time);

            case "LAB":
                return new LabCourse(title, capacity, day, time);

            case "ONLINE":
                return new OnlineCourse(title, capacity, day, time);

            default:
                throw new IllegalArgumentException("Unknown course type");
        }
    }
}
