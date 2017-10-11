package repository;

import domain.Student;

import java.util.List;

/**
 * Created by Jatin on 06/10/17.
 */
public interface StudentDAO {
    public List<Student> getStudents();
    public void saveStudent(Student student);
}
