package edu.aitu.campus.repository;

import edu.aitu.campus.db.DatabaseConnection;
import edu.aitu.campus.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    public void save(Course course) throws SQLException {
        String sql = "INSERT INTO courses (title, capacity, day, time) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, course.getTitle());
            ps.setInt(2, course.getCapacity());
            ps.setString(3, course.getDay());
            ps.setString(4, course.getTime());
            ps.executeUpdate();
        }
    }

    public Course findById(int id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("capacity"),
                        rs.getString("day"),
                        rs.getString("time")
                );
            }
        }
        return null;
    }

    public List<Course> findAll() throws SQLException {
        String sql = "SELECT * FROM courses";
        List<Course> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("capacity"),
                        rs.getString("day"),
                        rs.getString("time")
                ));
            }
        }
        return list;
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM courses WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}