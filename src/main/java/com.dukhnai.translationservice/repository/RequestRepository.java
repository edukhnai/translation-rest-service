package com.dukhnai.translationservice.repository;

import com.dukhnai.translationservice.entity.Request;
import com.dukhnai.translationservice.exception.RepositoryException;
import java.util.Optional;

public interface RequestRepository {

    void add(Request request) throws RepositoryException;

    Optional<Request> getById(String id) throws RepositoryException;
}
