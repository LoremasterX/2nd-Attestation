Galym Arsen
Zhumadilla Saniya

Report:
Course name:
Object Oriented Programming (Java)

Course instructor:
Traxel Xeniya Alexandrovna

Course trimester / year:
2 / 1

Assignment number:
4

Assignment name:
Project defense

Project title:
Campus Course Registration System

Team members:
– Galym Arsen
– Zhumadilla Saniya

1. Project Topic & Overview

Project topic:
Campus Course Registration System

Description:
This project is a console-based Java application that allows students to register for university courses.
The system manages students, courses, and enrollments, ensuring business rules such as course capacity and time conflict validation.

2. Main User Flows
2.1 Register student to course

User selects “Register student”.

Enters student ID and course ID.

System checks:

course capacity

time conflicts

Enrollment is saved to database.

2.2 Drop student from course

User selects “Drop student”.

Enrollment is removed from database.

2.3 View data

View all students

View all courses (sorted)

View students enrolled in a course


3. Database Schema
Entities / Tables:

students (id, name, email)

courses (id, title, capacity, day, time)

enrollments (id, student_id, course_id)

Relationships:

Student ↔ Course → many-to-many

Implemented via enrollments table

4. Architecture Overview
Package structure:

model — entities (Student, Course, Enrollment)

repository — data access (JDBC)

service — business logic

exception — domain-specific errors

db — database configuration (Singleton)

ui / Main — console interface

Used patterns:

Singleton — DatabaseConnection

Repository<T> — generic data access

Builder — StudentScheduleBuilder

Factory — CourseFactory (OnlineCourse, LabCourse, etc.)

Lambda — sorting courses

5. Components & Principles (REP / CCP / CRP)
Components:

Domain Component

Entities, interfaces, business rules

Persistence Component

JDBC, repositories, SQL

Business Component

Services, workflows

Reporting Component

Sorting, builders

UI Component

Console menu

Principles:

REP: classes reused together are grouped

CCP: classes that change together are in same component

CRP: components are reused as a whole

6. SOLID & Extensibility

SRP: each class has one responsibility

OCP: new course types added without changing existing logic

Low coupling via repositories and services

7. Conclusion

The system is modular, extendable, and follows OOP principles and design patterns.
It can be easily extended with new course types, reports, or UI layers.

