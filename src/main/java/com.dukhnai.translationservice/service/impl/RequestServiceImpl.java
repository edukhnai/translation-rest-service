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

    @Override
    public void saveRequestData(String id, Date dateWithTime, String text, String fromLanguage, String toLanguage, String clientIp) {
        Request request = new Request(id, dateWithTime, text, fromLanguage, toLanguage, clientIp);
        requestRepository.add(request);
    }
}
