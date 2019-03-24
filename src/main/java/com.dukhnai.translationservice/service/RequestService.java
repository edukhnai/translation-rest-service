package com.dukhnai.translationservice.service;

import java.util.Date;

public interface RequestService {

    void saveRequestData(String id, Date dateWithTime, String text, String from, String to, String clientIp);
}
