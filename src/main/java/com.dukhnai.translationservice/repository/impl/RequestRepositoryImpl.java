package com.dukhnai.translationservice.repository.impl;

import com.dukhnai.translationservice.entity.Request;
import com.dukhnai.translationservice.exception.RepositoryException;
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

    private boolean requestTableCreated = false;

    @Override
    public void add(Request request) throws RepositoryException {

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
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public Optional<Request> getById(String id) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from request where id = ?")) {

            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            Request request = null;
            while(resultSet.next()) {
                request = new Request(
                        resultSet.getString(1),
                        new Date(resultSet.getTimestamp(2).getTime()),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            }
            return Optional.ofNullable(request);

        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    private void createRequestTable() throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement createTableStatement = connection.prepareStatement(
                     "create table if not exists request(id char(36), dateTime timestamp, text varchar(2000)," +
                             "fromLang varchar(3),toLang varchar(3),clientIp varchar(50), primary key (id))")) {

            createTableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}