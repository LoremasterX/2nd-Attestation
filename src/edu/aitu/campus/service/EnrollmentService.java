package edu.aitu.campus.service;

import edu.aitu.campus.exception.CourseFullException;
import edu.aitu.campus.exception.TimeConflictException;
import edu.aitu.campus.model.Course;
import edu.aitu.campus.model.Enrollment;
import edu.aitu.campus.repository.CourseRepository;
import edu.aitu.campus.repository.EnrollmentRepository;

import java.sql.SQLException;
import java.util.List;

public class EnrollmentService {

    private final CourseRepository courseRepository = new CourseRepository();
    private final EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

    public void register(int studentId, int courseId)
            throws SQLException, CourseFullException, TimeConflictException {

        // course must exist
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found");
        }

        //  capacity check
        int enrolledCount = enrollmentRepository.countByCourseId(courseId);
        if (enrolledCount >= course.getCapacity()) {
            throw new CourseFullException("Course is full");
        }

        //  time conflict check
        List<Enrollment> studentEnrollments =
                enrollmentRepository.findByStudentId(studentId);

        for (Enrollment e : studentEnrollments) {
            Course other = courseRepository.findById(e.getCourseId());

            if (other.getDay().equals(course.getDay())
                    && other.getTime().equals(course.getTime())) {
                throw new TimeConflictException(
                        "Time conflict with another course"
                );
            }
        }

        //  register
        enrollmentRepository.save(studentId, courseId);
    }

    public void drop(int studentId, int courseId) throws SQLException {
        enrollmentRepository.delete(studentId, courseId);
    }
}