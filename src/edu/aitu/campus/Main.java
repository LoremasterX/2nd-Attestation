package edu.aitu.campus;

import edu.aitu.campus.exception.CourseFullException;
import edu.aitu.campus.exception.TimeConflictException;
import edu.aitu.campus.model.Course;
import edu.aitu.campus.model.Enrollment;
import edu.aitu.campus.model.Student;
import edu.aitu.campus.repository.CourseRepository;
import edu.aitu.campus.repository.EnrollmentRepository;
import edu.aitu.campus.repository.StudentRepository;
import edu.aitu.campus.service.CourseService;
import edu.aitu.campus.service.EnrollmentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        StudentRepository studentRepo = new StudentRepository();
        CourseRepository courseRepo = new CourseRepository();
        EnrollmentRepository enrollmentRepo = new EnrollmentRepository();

        EnrollmentService enrollmentService = new EnrollmentService();
        CourseService courseService = new CourseService();

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Campus Course Registration System ===");

        while (true) {
            printMenu();
            System.out.print("Choose an action: ");

            String input = sc.nextLine().trim();
            if (input.isEmpty()) continue;

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            try {
                switch (choice) {
                    case 0:
                        System.out.println("Exiting application.");
                        return;

                    case 1: // register
                        System.out.print("Enter student id: ");
                        int studentId = readInt(sc);

                        System.out.print("Enter course id: ");
                        int courseId = readInt(sc);

                        try {
                            enrollmentService.register(studentId, courseId);
                            System.out.println("Student successfully registered to the course.");
                        } catch (CourseFullException | TimeConflictException e) {
                            System.out.println("Registration failed: " + e.getMessage());
                        }
                        break;

                    case 2: // drop
                        System.out.print("Enter student id: ");
                        int sId = readInt(sc);

                        System.out.print("Enter course id: ");
                        int cId = readInt(sc);

                        enrollmentService.drop(sId, cId);
                        System.out.println("Student successfully dropped from the course.");
                        break;

                    case 3: // show enrolled students
                        System.out.print("Enter course id: ");
                        int courseForList = readInt(sc);

                        List<Enrollment> enrollments = enrollmentRepo.findByCourseId(courseForList);
                        if (enrollments.isEmpty()) {
                            System.out.println("No students are enrolled in this course.");
                        } else {
                            System.out.println("Students enrolled in course " + courseForList + ":");
                            for (Enrollment e : enrollments) {
                                Student st = studentRepo.findById(e.getStudentId());
                                if (st != null) {
                                    System.out.printf(" - id=%d, name=%s, email=%s%n",
                                            st.getId(), st.getName(), st.getEmail());
                                }
                            }
                        }
                        break;

                    case 4: // list students
                        List<Student> students = studentRepo.findAll();
                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            System.out.println("All students:");
                            for (Student st : students) {
                                System.out.printf(" - id=%d, name=%s, email=%s%n",
                                        st.getId(), st.getName(), st.getEmail());
                            }
                        }
                        break;

                    case 5: // list courses
                        List<Course> courses = courseRepo.findAll();
                        if (courses.isEmpty()) {
                            System.out.println("No courses found.");
                        } else {
                            System.out.println("All courses:");
                            for (Course c : courses) {
                                System.out.printf(
                                        " - id=%d, title=%s, capacity=%d, day=%s, time=%s%n",
                                        c.getId(), c.getTitle(), c.getCapacity(),
                                        c.getDay(), c.getTime()
                                );
                            }
                        }
                        break;

                    case 6: // delete course
                        System.out.print("Enter course id to delete: ");
                        int delId = readInt(sc);

                        try {
                            courseService.deleteCourse(delId);
                            System.out.println("Course successfully deleted.");
                        } catch (Exception e) {
                            System.out.println("Delete failed: " + e.getMessage());
                        }
                        break;

                    default:
                        System.out.println("Unknown command.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Menu:");
        System.out.println(" 0 - Exit");
        System.out.println(" 1 - Register student to course");
        System.out.println(" 2 - Drop student from course");
        System.out.println(" 3 - Show students enrolled in a course");
        System.out.println(" 4 - Show all students");
        System.out.println(" 5 - Show all courses");
        System.out.println(" 6 - Delete course");
    }

    private static int readInt(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
