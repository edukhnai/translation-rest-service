package com.dukhnai.translationservice.service.impl;

import com.dukhnai.translationservice.entity.Request;
import com.dukhnai.translationservice.repository.RequestRepository;
import com.dukhnai.translationservice.service.RequestService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public void saveRequestData(String id, Date dateWithTime, String text, String from, String to, String clientIp) {
        Request request = new Request(id, dateWithTime, text, from, to, clientIp);
        requestRepository.add(request);
    }
}
