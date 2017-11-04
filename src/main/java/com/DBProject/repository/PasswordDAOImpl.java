package com.DBProject.repository;

import com.DBProject.domain.Password;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Jatin on 01/11/17.
 */
@Repository

public class PasswordDAOImpl implements PasswordDAO {
    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SneakyThrows
    public Password passwordMapper (ResultSet rs){
        return new Password(rs.getString(1), rs.getString(2), rs.getString(3));
    }

    @Override
    public Password getByUsername(String username) {
        try(Connection connection = dataSource.getConnection()) {
            String sql = "select * from password where id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            Password password = null;
            while(resultSet.next()) {
                password = passwordMapper(resultSet);
            }
            return password;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
