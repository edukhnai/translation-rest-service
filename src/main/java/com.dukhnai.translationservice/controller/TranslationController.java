package com.dukhnai.translationservice.controller;

import com.dukhnai.translationservice.dto.TextForTranslation;
import com.dukhnai.translationservice.exception.ConnectionEstablishingException;
import com.dukhnai.translationservice.exception.SavingRequestException;
import com.dukhnai.translationservice.service.ServiceLocator;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private final Logger logger = LogManager.getLogger(TranslationController.class);

    @PostMapping(value = "/translate")
    public String processTextTranslation(@RequestBody TextForTranslation textForTranslation) {

        try {
            serviceLocator.getRequestService().saveRequestData(
                    UUID.randomUUID().toString(),
                    new Date(),
                    textForTranslation.getText(),
                    textForTranslation.getFrom(),
                    textForTranslation.getTo(),
                    request.getRemoteAddr());

        } catch (SavingRequestException e) {
            logger.error(e);
        }

        String result;
        try {
            result = serviceLocator.getTranslationService().getTextTranslation(
                    textForTranslation.getText(),
                    textForTranslation.getFrom(),
                    textForTranslation.getTo());

        } catch (ConnectionEstablishingException e) {
            logger.error("Error during establishing connection to the server");
            result = "error";
        }

        return result;
    }
}