package edu.aitu.campus.service;

import edu.aitu.campus.exception.ActiveEnrollmentException;
import edu.aitu.campus.repository.CourseRepository;
import edu.aitu.campus.repository.EnrollmentRepository;

import java.sql.SQLException;

public class CourseService {

    private final CourseRepository courseRepository = new CourseRepository();
    private final EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

    public void deleteCourse(int courseId)
            throws SQLException, ActiveEnrollmentException {

        int enrolledCount = enrollmentRepository.countByCourseId(courseId);

        if (enrolledCount > 0) {
            throw new ActiveEnrollmentException(
                    "Cannot delete course with active enrollments"
            );
        }

        courseRepository.deleteById(courseId);
    }
}