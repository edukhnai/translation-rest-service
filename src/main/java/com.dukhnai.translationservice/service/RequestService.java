package com.dukhnai.translationservice.service;

import com.dukhnai.translationservice.entity.Request;
import com.dukhnai.translationservice.exception.RequestDoesNotPresentException;
import com.dukhnai.translationservice.exception.RetrievingRequestException;
import com.dukhnai.translationservice.exception.SavingRequestException;
import java.util.Date;

public interface RequestService {

    void saveRequestData(String id, Date dateWithTime, String text, String from, String to, String clientIp) throws SavingRequestException;

    Request getById(String id) throws RequestDoesNotPresentException, RetrievingRequestException;
}
