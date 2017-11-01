package com.DBProject.repository;

import javax.sql.DataSource;
import com.DBProject.domain.Ic;
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

public class IcDAOImpl implements IcDAO {
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public static Ic icMapper(ResultSet rs) throws SQLException {
        return new Ic(rs.getString(1), rs.getString(2));
    }

	@Override
	public Ic getAFreeIc() {
		String sql = "select ic.ic_id, count(ic_student.sid) as c\r\n" + 
				"from ic LEFT OUTER JOIN ic_student\r\n" + 
				"on ic.ic_id = ic_student.ic_id\r\n" + 
				"group by ic.ic_id\r\n" + 
				"order by c\r\n" + 
				"limit 1";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			return icMapper(rs);
		}
		catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Student> getHerStudentsLessThanAStage(String ic_id, String stage) {
		String sql = "select sid, name, did, pid, cpi, stage\r\n" + 
				"from student NATURAL JOIN ic_student\r\n" + 
				"where ic_student.ic_id=? and\r\n" + 
				"student.stage < ?";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ic_id);
			preparedStatement.setString(2, stage);
			ResultSet rs = preparedStatement.executeQuery();
			List<Student> ret = new ArrayList<Student>();
			while(rs.next()) {
				ret.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
			return ret;
		}
		catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void advanceHerStudents(String ic_id, String stage) {
		String sql = "update student\r\n" + 
				"set stage = ?\r\n" + 
				"from ic_student\r\n" + 
				"where student.sid=ic_student.sid and\r\n" + 
				"ic_student.ic_id = ?";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, stage);
			preparedStatement.setString(2, ic_id);
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
