package repository;

import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Jatin on 06/10/17.
 */
@Repository
public class StudentDAOImpl extends SimpleJdbcDaoSupport implements StudentDAO  {
    @Autowired
    DataSource dataSource;

    public static Student studentMapper(ResultSet rs) throws SQLException {
        return new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
    }


    public void saveStudent(Student student) {
        String insertQuery = "insert into table student values (?, ?, ?, ?);";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(insertQuery, new Object[]{student.getId(), student.getName(), student.getDeptName(), student.getTotalCredits()});
    }

    public List<Student> getStudents() {
        String sql  = "select * from student";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while(resultSet.next()) {
                students.add(studentMapper(resultSet));
            }
            return students;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
