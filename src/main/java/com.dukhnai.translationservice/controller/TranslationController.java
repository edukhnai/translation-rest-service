package com.dukhnai.translationservice.controller;

import com.dukhnai.translationservice.dto.TextForTranslation;
import com.dukhnai.translationservice.service.ServiceLocator;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslationController {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private HttpServletRequest request;

    @PostMapping(value = "/translate")
    public String processTextTranslation(@RequestBody TextForTranslation textForTranslation) {

        serviceLocator.getRequestService().saveRequestData(
                UUID.randomUUID().toString(),
                new Date(),
                textForTranslation.getText(),
                textForTranslation.getFrom(),
                textForTranslation.getTo(),
                request.getRemoteAddr());

        String result = serviceLocator.getTranslationService().getTextTranslation(
                textForTranslation.getText(),
                textForTranslation.getFrom(),
                textForTranslation.getTo());

        return result;
    }
}