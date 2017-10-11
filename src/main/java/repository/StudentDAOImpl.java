package repository;

import domain.Student;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jatin on 06/10/17.
 */
@Repository
public class StudentDAOImpl extends SimpleJdbcDaoSupport implements StudentDAO  {

    public void saveStudent(Student student) {

    }

    public List<Student> getStudents() {
        return null;
    }

}
