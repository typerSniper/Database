package com.DBProject.repository;

import com.DBProject.Controller.ajax.StudentController.SaveDetailsRequest;
import com.DBProject.domain.Coordinator;
import com.DBProject.domain.Student;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.DBProject.repository.CoordinatorDAOImpl.icMapper;

@Repository

public class StudentDAOImpl  implements StudentDAO  {
    @Autowired
    private DataSource dataSource;

    @Autowired
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
            String sql = "select * from student where sid = ?";
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
            String sql = "insert into resume(sid, resume, rtype) values (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(StringUtils.isEmpty(unicode))
            	return false;
			String partSeparator = ",";
			String encodedImg = unicode.split(partSeparator)[1];

			byte[] resume = Base64.getDecoder().decode(encodedImg.getBytes(StandardCharsets.UTF_8));
//			Path destinationFile = Paths.get(".", "myImage.pdf");
//			Files.write(destinationFile, resume);

			preparedStatement.setString(1, student.getUsername());
            preparedStatement.setBytes(2, resume);
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
		String sql_details = "insert into studentdetails(univemail, peremail, dob, sex, category, nationality, hosteladdress,"
				+ "contact1, contact2, skypeid, homeaddress, detail12th, detail10th, others, sid) "
				+ "values ("
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "(?, ?, ?, ?, ?),"
				+ "(?, ?, ?, ?),"
				+ "(?, ?, ?, ?),"
				+ "(?, ?, ?, ?),"
				+ "?)";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement_details = connection.prepareStatement(sql_details);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dob = getValueOrDefault(saveDetailsRequest.getDob(), null);
			java.util.Date date = sdf.parse(dob);
			Date sqldate = new Date(date.getTime());
			preparedStatement_details.setString(1, getValueOrDefault(saveDetailsRequest.getUnivemail(), null));
			preparedStatement_details.setString(2, getValueOrDefault(saveDetailsRequest.getPeremail(), null));
			preparedStatement_details.setDate(3, sqldate);
			preparedStatement_details.setString(4, getValueOrDefault(saveDetailsRequest.getSex(), null));
			preparedStatement_details.setString(5, getValueOrDefault(saveDetailsRequest.getCategory(), null));
			preparedStatement_details.setString(6, getValueOrDefault(saveDetailsRequest.getNationality(), null));
			preparedStatement_details.setString(7, getValueOrDefault(saveDetailsRequest.getHosteladdress(), null));
			preparedStatement_details.setString(8, getValueOrDefault(saveDetailsRequest.getContact1(), null));
			preparedStatement_details.setString(9, getValueOrDefault(saveDetailsRequest.getContact2(), null));
			preparedStatement_details.setString(10, getValueOrDefault(saveDetailsRequest.getSkypeid(), null));
			preparedStatement_details.setString(11, getValueOrDefault(saveDetailsRequest.getHomeaddress()!=null ? saveDetailsRequest.getHomeaddress().getState() : null, null));
			preparedStatement_details.setString(12, getValueOrDefault(saveDetailsRequest.getHomeaddress()!=null ? saveDetailsRequest.getHomeaddress().getCity() : null, null));
			preparedStatement_details.setString(13, getValueOrDefault(saveDetailsRequest.getHomeaddress()!=null ? saveDetailsRequest.getHomeaddress().getPin() : null, null));
			preparedStatement_details.setString(14, getValueOrDefault(saveDetailsRequest.getHomeaddress()!=null ? saveDetailsRequest.getHomeaddress().getLocality() : null, null));
			preparedStatement_details.setString(15, getValueOrDefault(saveDetailsRequest.getHomeaddress()!=null ? saveDetailsRequest.getHomeaddress().getCountry() : null, null));
			preparedStatement_details.setString(16, getValueOrDefault(saveDetailsRequest.getDetail12th()!=null ? saveDetailsRequest.getDetail12th().getUniversity() : null, null));
			preparedStatement_details.setString(17, getValueOrDefault(saveDetailsRequest.getDetail12th()!=null ? saveDetailsRequest.getDetail12th().getInstitute() : null, null));
			preparedStatement_details.setString(18, getValueOrDefault(saveDetailsRequest.getDetail12th()!=null ? saveDetailsRequest.getDetail12th().getYear() : null, null));
			preparedStatement_details.setString(19, getValueOrDefault(saveDetailsRequest.getDetail12th()!=null ? saveDetailsRequest.getDetail12th().getCpi() : null, null));
			preparedStatement_details.setString(20, getValueOrDefault(saveDetailsRequest.getDetail10th()!=null ? saveDetailsRequest.getDetail10th().getUniversity() : null, null));
			preparedStatement_details.setString(21, getValueOrDefault(saveDetailsRequest.getDetail10th()!=null ? saveDetailsRequest.getDetail10th().getInstitute() : null, null));
			preparedStatement_details.setString(22, getValueOrDefault(saveDetailsRequest.getDetail10th()!=null ? saveDetailsRequest.getDetail10th().getYear() : null, null));
			preparedStatement_details.setString(23, getValueOrDefault(saveDetailsRequest.getDetail10th()!=null ? saveDetailsRequest.getDetail10th().getCpi() : null, null));
			preparedStatement_details.setString(24, getValueOrDefault(saveDetailsRequest.getOthers()!=null ? saveDetailsRequest.getOthers().getUniversity() : null, null));
			preparedStatement_details.setString(25, getValueOrDefault(saveDetailsRequest.getOthers()!=null ? saveDetailsRequest.getOthers().getInstitute() : null, null));
			preparedStatement_details.setString(26, getValueOrDefault(saveDetailsRequest.getOthers()!=null ? saveDetailsRequest.getOthers().getYear() : null, null));
			preparedStatement_details.setString(27, getValueOrDefault(saveDetailsRequest.getOthers()!=null ? saveDetailsRequest.getOthers().getCpi() : null, null));
			preparedStatement_details.setString(28, getValueOrDefault(username, null));
			System.out.println(preparedStatement_details);
			preparedStatement_details.executeUpdate();
			updateStage(connection, stage, username);
			preparedStatement_details.close();
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
	public Coordinator getAllocatedIc(String username) {
    	try (Connection connection = dataSource.getConnection()) {
			String sql = "select ic_id, name,phone_number from ic_student natural join ic where ic_student.sid = ?;";
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setString(1, username);
    		ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Coordinator coordinator = icMapper(resultSet);
				return coordinator;
			}
    	}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
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
