package com.dukhnai.translationservice.repository.impl;

import com.dukhnai.translationservice.entity.Request;
import com.dukhnai.translationservice.repository.RequestRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequestRepositoryImpl implements RequestRepository {

    private final Logger logger = LogManager.getLogger(RequestRepositoryImpl.class);

    @Autowired
    private DataSource dataSource;

    private boolean requestTableCreated = false;

    @Override
    public void add(Request request) {

        if (!requestTableCreated) {
            createRequestTable();
            requestTableCreated = true;
        }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into request values (?,?,?,?,?,?)")) {

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

    private void createRequestTable() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement createTableStatement = connection.prepareStatement(
                     "create table if not exists request(id char(36), dateTime timestamp, text varchar(2000)," +
                             "fromLang varchar(3),toLang varchar(3),clientIp varchar(50), primary key (id))")) {

            createTableStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }
}