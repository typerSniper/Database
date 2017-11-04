package com.DBProject.repository;

import com.DBProject.Controller.ajax.StudentController.SaveDetailsRequest;
import com.DBProject.domain.Coordinator;
import com.DBProject.domain.Student;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository

public class StudentDAOImpl  implements StudentDAO  {
    @Autowired
    private DataSource dataSource;
    private CoordinatorDAO icDAO;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public static Student studentMapper(ResultSet rs) throws SQLException {
        return new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
    }


    public void saveStudent(Student student) {
        try(Connection connection = dataSource.getConnection()) {
            String sql = "insert into table student values (?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getUsername());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getDeptId());
            preparedStatement.setString(4, student.getProgId());
            preparedStatement.setString(5, student.getCpi());
            preparedStatement.setString(6, student.getStage());
            preparedStatement.executeUpdate();
			preparedStatement.close();
		}
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Student getStudent(String username) {
        if(StringUtils.isBlank(username)) {
        	System.out.println("Username Can't Be Empty");
            return null;
        }
        try(Connection connection = dataSource.getConnection()) {
            String sql = "select * from student where student.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            Student student  = null;
            while(resultSet.next()) {
                student = studentMapper(resultSet);
            }
			preparedStatement.close();
			return student;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean saveResume(Student student, String unicode, String type, String stage) {
    	try(Connection connection = dataSource.getConnection()) {
            String sql = "insert into table resume values (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getUsername());
            preparedStatement.setString(2, unicode);
            preparedStatement.setString(3, type);
            preparedStatement.executeUpdate();
			preparedStatement.close();
			updateStage(connection, stage, student.getUsername());
			return true;
		}
        catch (Exception e) {
            e.printStackTrace();
        }
    	return false;
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
			preparedStatement.close();
			return students;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getValueOrDefault(String value, String defaultValue) {
        return isNotNullOrEmpty(value) ? value : defaultValue;
    }
    
    private static boolean isNotNullOrEmpty(String str) {
        return str != null && !str.isEmpty();
    }
    
	@Override
	public boolean saveDetails(String username, SaveDetailsRequest saveDetailsRequest, String stage) {
		String sql = "update student set stage = ? where sid=?";
		String sql_details = "update studentDetails set "
				+ "univemail=?,"
				+ "peremail=?,"
				+ "dob=?,"
				+ "sex=?,"
				+ "category=?,"
				+ "nationality=?,"
				+ "hosteladdress=?,"
				+ "contact1=?,"
				+ "contact2=?,"
				+ "skypeid=?,"
				+ "homeaddress=(?, ?, ?, ?, ?),"
				+ "collegedetails=(?, ?, ?, ?),"
				+ "detail12th=(?, ?, ?, ?),"
				+ "detail10th=(?, ?, ?, ?),"
				+ "others=(?, ?, ?, ?)"
				+ "where sid=?";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			PreparedStatement preparedStatement_details = connection.prepareStatement(sql_details);
			System.out.println("saving these details" + username + " : stage " + stage + " \n" + saveDetailsRequest);
			preparedStatement.setString(1, stage);
			preparedStatement.setString(2, username);

			preparedStatement_details.setString(1, getValueOrDefault(saveDetailsRequest.getUnivemail(), "null"));
			preparedStatement_details.setString(2, getValueOrDefault(saveDetailsRequest.getPeremail(), "null"));
			preparedStatement_details.setString(3, getValueOrDefault(saveDetailsRequest.getDob(), "null"));
			preparedStatement_details.setString(4, getValueOrDefault(saveDetailsRequest.getSex(), "null"));
			preparedStatement_details.setString(5, getValueOrDefault(saveDetailsRequest.getCategory(), "null"));
			preparedStatement_details.setString(6, getValueOrDefault(saveDetailsRequest.getNationality(), "null"));
			preparedStatement_details.setString(7, getValueOrDefault(saveDetailsRequest.getHosteladdress(), "null"));
			preparedStatement_details.setString(8, getValueOrDefault(saveDetailsRequest.getContact1(), "null"));
			preparedStatement_details.setString(9, getValueOrDefault(saveDetailsRequest.getContact2(), "null"));
			preparedStatement_details.setString(10, getValueOrDefault(saveDetailsRequest.getSkypeid(), "null"));
			preparedStatement_details.setString(11, getValueOrDefault(saveDetailsRequest.getHomeaddress().getState(), "null"));
			preparedStatement_details.setString(12, getValueOrDefault(saveDetailsRequest.getHomeaddress().getCity(), "null"));
			preparedStatement_details.setString(13, getValueOrDefault(saveDetailsRequest.getHomeaddress().getPin(), "null"));
			preparedStatement_details.setString(14, getValueOrDefault(saveDetailsRequest.getHomeaddress().getLocality(), "null"));
			preparedStatement_details.setString(15, getValueOrDefault(saveDetailsRequest.getHomeaddress().getCountry(), "null"));
			preparedStatement_details.setString(16, getValueOrDefault(saveDetailsRequest.getCollegeDetails().getUniversity(), "null"));
			preparedStatement_details.setString(17, getValueOrDefault(saveDetailsRequest.getCollegeDetails().getInstitute(), "null"));
			preparedStatement_details.setString(18, getValueOrDefault(saveDetailsRequest.getCollegeDetails().getYear(), "null"));
			preparedStatement_details.setString(19, getValueOrDefault(saveDetailsRequest.getCollegeDetails().getCpi(), "null"));
			preparedStatement_details.setString(20, getValueOrDefault(saveDetailsRequest.getDetails12th().getUniversity(), "null"));
			preparedStatement_details.setString(21, getValueOrDefault(saveDetailsRequest.getDetails12th().getInstitute(), "null"));
			preparedStatement_details.setString(22, getValueOrDefault(saveDetailsRequest.getDetails12th().getYear(), "null"));
			preparedStatement_details.setString(23, getValueOrDefault(saveDetailsRequest.getDetails12th().getCpi(), "null"));
			preparedStatement_details.setString(24, getValueOrDefault(saveDetailsRequest.getDetails10th().getUniversity(), "null"));
			preparedStatement_details.setString(25, getValueOrDefault(saveDetailsRequest.getDetails10th().getInstitute(), "null"));
			preparedStatement_details.setString(26, getValueOrDefault(saveDetailsRequest.getDetails10th().getYear(), "null"));
			preparedStatement_details.setString(27, getValueOrDefault(saveDetailsRequest.getDetails10th().getCpi(), "null"));
			preparedStatement_details.setString(28, getValueOrDefault(saveDetailsRequest.getOther().getUniversity(), "null"));
			preparedStatement_details.setString(29, getValueOrDefault(saveDetailsRequest.getOther().getInstitute(), "null"));
			preparedStatement_details.setString(30, getValueOrDefault(saveDetailsRequest.getOther().getYear(), "null"));
			preparedStatement_details.setString(31, getValueOrDefault(saveDetailsRequest.getOther().getCpi(), "null"));
			preparedStatement_details.setString(32, getValueOrDefault(username, "null"));
			
			preparedStatement.executeUpdate();
			preparedStatement_details.executeUpdate();
			preparedStatement.close();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public void updateStage(Connection conn, String stage, String username) {
		String sql = "update student set stage = ? where sid = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, stage);
			preparedStatement.setString(2, username);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public Coordinator allocateIc(String username, String stage) {
		Coordinator freeIc = icDAO.getAFreeIc();
		String sql = "insert into ic_student(ic_id, sid)\n" + 
				"values (?,?);";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, freeIc.getIc_id());
			preparedStatement.setString(2, username);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			updateStage(connection, stage, username);
			return freeIc;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
