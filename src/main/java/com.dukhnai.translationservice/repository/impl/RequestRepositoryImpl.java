package com.dukhnai.translationservice.repository.impl;

import com.dukhnai.translationservice.entity.Request;
import com.dukhnai.translationservice.repository.RequestRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequestRepositoryImpl implements RequestRepository {
    @Autowired
    private DataSource dataSource;

    @Override
    public boolean add(Request request) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement createTableStatement = connection.prepareStatement(
                     "create table if not exists request(id char(36), dateTime timestamp, text varchar(1000)," +
                             "fromLang varchar(3),toLang varchar(3),clientIp varchar(50), primary key (id)) engine = InnoDB");
             PreparedStatement statement = connection.prepareStatement("insert into request values (?,?,?,?,?,?)")) {

            createTableStatement.executeUpdate();

            statement.setString(1, request.getId());
            statement.setTimestamp(2, new Timestamp(request.getDateWithTime().getTime()));
            statement.setString(3, request.getText());
            statement.setString(4, request.getFromLanguage());
            statement.setString(5, request.getToLanguage());
            statement.setString(6, request.getClientIp());
            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}