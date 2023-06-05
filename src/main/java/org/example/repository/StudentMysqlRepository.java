package org.example.repository;

import org.example.domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentMysqlRepository implements StudentRepository {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    private static final String SELECT_FROM_STUDENT = "SELECT * FROM Student";
    private static final String INSERT_STUDENT = "INSERT INTO Student (Age,Firstname,Lastname) VALUES (?, ?, ?)";
    @Override
    public void saveStudent(Student student) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT);
            preparedStatement.setInt(1, student.getAge());
            preparedStatement.setString(2, student.getFirstname());
            preparedStatement.setString(3, student.getLastname());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Student> findAll() {
        ArrayList<Student> resultStudentsList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_FROM_STUDENT);) {
            while (rs.next()) {
                Student student = Student.builder()
                        .Id(rs.getInt("Id"))
                        .Age(rs.getInt("Age"))
                        .Firstname(rs.getString("Firstname"))
                        .Lastname(rs.getString("Lastname"))
                        .build();
                resultStudentsList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultStudentsList;
    }
}