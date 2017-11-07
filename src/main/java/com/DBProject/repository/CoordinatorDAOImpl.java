package com.DBProject.repository;

import javax.sql.DataSource;

import com.DBProject.Controller.ajax.CoordinatorController;
import com.DBProject.domain.Coordinator;
import com.DBProject.domain.Resume;
import com.DBProject.domain.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository

public class CoordinatorDAOImpl implements CoordinatorDAO {
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public static Coordinator icMapper(ResultSet rs) throws SQLException {
        return new Coordinator(rs.getString(1), rs.getString(2), rs.getString(3));
    }

	@Override
	public Coordinator getAFreeIc() {
		String sql = "select *\r\n" + 
				"from ic\r\n" + 
				"where ic_id =\r\n" + 
				"(select ic.ic_id\r\n" + 
				"from ic LEFT OUTER JOIN ic_student\r\n" + 
				"on ic.ic_id = ic_student.ic_id\r\n" + 
				"group by ic.ic_id\r\n" + 
				"order by count(ic_student.sid)\r\n" + 
				"limit 1\r\n" + 
				")";
		// String sql = "select * from ic where ic_id ='coordinator1';";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
				return icMapper(rs);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Student> getStudentsWithStage(String ic_id, String stage) {
	    System.out.println(ic_id+ stage);
		String sql = "select sid, name, did, pid, cpi, stage\r\n" + 
				"from student NATURAL JOIN ic_student\r\n" + 
				"where ic_student.ic_id=? and\r\n" + 
				"student.stage = ? ";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ic_id);
			preparedStatement.setString(2, stage);
			ResultSet rs = preparedStatement.executeQuery();
			List<Student> ret = new ArrayList<Student>();
			while(rs.next()) {
				ret.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
			preparedStatement.close();
			return ret;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CoordinatorController.ResumeStudents> getResumeStudents(String ic_id) {
		String sql = "select sid, rtype from resume where resume.sid in (select sid from ic_student where ic_student.ic_id = ?);";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ic_id);
			ResultSet rs = preparedStatement.executeQuery();
			List<CoordinatorController.ResumeStudents> resumeStudents = new ArrayList<>();
			while(rs.next()) {
				resumeStudents.add(new CoordinatorController.ResumeStudents(rs.getString(1), rs.getString(2)));
			}
			return resumeStudents;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean advanceHerStudents(String ic_id, String stage, List<Student> students) {
		String sql ="update student set stage = ? where sid =? ;";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for(int j=0;j<students.size();j++) {
				preparedStatement.setString(1, stage);
				preparedStatement.setString(2, students.get(j).getUsername());
				preparedStatement.addBatch();
			}
			if(students.size()!=0)
				preparedStatement.executeBatch();
			preparedStatement.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
