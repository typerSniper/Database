package com.DBProject.repository;

import com.DBProject.domain.Password;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Jatin on 01/11/17.
 */
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
            String sql = "select * from password where username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            Password password = null;
            while(resultSet.next()) {
                password = passwordMapper(resultSet);
            }
            System.out.println(password);
            return password;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
