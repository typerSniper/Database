package com.DBProject.repository;

import com.DBProject.domain.Resume;
import lombok.SneakyThrows;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jatin on 04/11/17.
 */
@Repository
public class ResumeDAOImpl implements ResumeDAO {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Resume> getByUsername(String username) {
        try(Connection connection = dataSource.getConnection()) {
            String sql = "select * from resume where sid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while(resultSet.next()) {
                resumes.add(resumeMapper(resultSet));
            }
            return resumes;
        }
        catch (Exception e) {
         e.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public Resume resumeMapper(ResultSet resultSet) {
        String resume = null;
        String type = null;
        if(resultSet.getBytes(3) != null) {
            resume = Base64.getEncoder().encodeToString(resultSet.getBytes(3));
            System.out.println(resume);

        }
        if(resultSet.getBytes(4) != null)
            type = Base64.getEncoder().encodeToString(resultSet.getBytes(4));
        return new Resume(resume, type, resultSet.getString(1), resultSet.getString(2));
    }
}
