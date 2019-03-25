package com.dukhnai.translationservice.repository.impl;

import com.dukhnai.translationservice.entity.Request;
import com.dukhnai.translationservice.repository.RequestRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequestRepositoryImpl implements RequestRepository {

    @Autowired
    private DataSource dataSource;

    private final Logger logger = LogManager.getLogger(RequestRepositoryImpl.class);

    @Override
    public void add(Request request) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement createTableStatement = connection.prepareStatement(
                     "create table if not exists request(id char(36), dateTime timestamp, text varchar(2000)," +
                             "fromLang varchar(3),toLang varchar(3),clientIp varchar(50), primary key (id))");
             PreparedStatement statement = connection.prepareStatement("insert into request values (?,?,?,?,?,?)")) {

            createTableStatement.executeUpdate();

            statement.setString(1, request.getId());
            statement.setTimestamp(2, new Timestamp(request.getDateWithTime().getTime()));
            statement.setString(3, request.getText());
            statement.setString(4, request.getFromLanguage());
            statement.setString(5, request.getToLanguage());
            statement.setString(6, request.getClientIp());

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }
}