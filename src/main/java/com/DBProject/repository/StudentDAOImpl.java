package com.DBProject.repository;

import com.DBProject.domain.Student;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jatin on 06/10/17.
 */
@Repository

public class StudentDAOImpl  implements StudentDAO  {
    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public static Student studentMapper(ResultSet rs) throws SQLException {
        return new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
    }


    public void saveStudent(Student student) {
        String insertQuery = "insert into table student values (?, ?, ?, ?);";
    }


    public Student getStudent(String username, String password) {
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)) {
            return null;
        }
        try(Connection connection = dataSource.getConnection()) {
            String sql = "select * from student as S natural join password as P where S.id = ? and P.password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            Student student  = null;
            while(resultSet.next()) {
                student = studentMapper(resultSet);
            }
            System.out.println(student);
            return student;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Student> getStudents() {
        String sql  = "select * from student";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println("got connection");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while(resultSet.next()) {
                students.add(studentMapper(resultSet));
            }
            return students;
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }

}
