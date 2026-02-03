package edu.aitu.campus.service;

import edu.aitu.campus.model.Student;
import edu.aitu.campus.repository.StudentRepository;

public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor: repository-ді service-ке береміз
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Жаңа студент қосу логикасы
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }

        try {
            studentRepository.save(student);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving student", e);
        }
    }


    // Студентті ID арқылы алу
    public Student getStudentById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid student ID");
        }

        try {
            return studentRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching student", e);
        }
    }
}
