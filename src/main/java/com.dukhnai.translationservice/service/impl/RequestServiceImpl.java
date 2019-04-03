package com.dukhnai.translationservice.service.impl;

import com.dukhnai.translationservice.entity.Request;
import com.dukhnai.translationservice.exception.RepositoryException;
import com.dukhnai.translationservice.exception.RequestDoesNotPresentException;
import com.dukhnai.translationservice.exception.RetrievingRequestException;
import com.dukhnai.translationservice.exception.SavingRequestException;
import com.dukhnai.translationservice.repository.RequestRepository;
import com.dukhnai.translationservice.service.RequestService;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public void saveRequestData(String id, Date dateWithTime, String text, String fromLanguage, String toLanguage, String clientIp) throws SavingRequestException {
        Request request = new Request(id, dateWithTime, text, fromLanguage, toLanguage, clientIp);
        try {
            requestRepository.add(request);

        } catch (RepositoryException e) {
            throw new SavingRequestException(e.getMessage());
        }
    }

    @Override
    public Request getById(String id) throws RequestDoesNotPresentException, RetrievingRequestException {
        try {
            Optional<Request> request = requestRepository.getById(id);

            if (request.isPresent()) {
                return request.get();
            }
            throw new RequestDoesNotPresentException();

        } catch (RepositoryException e) {
            throw new RetrievingRequestException(e.getMessage());
        }
    }
}
