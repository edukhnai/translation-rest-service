package com.dukhnai.translationservice.service.impl;

import com.dukhnai.translationservice.service.RequestService;
import com.dukhnai.translationservice.service.ServiceLocator;
import com.dukhnai.translationservice.service.TranslationService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ServiceLocatorImpl implements ServiceLocator {

    @Autowired
    RequestService requestService;

    @Autowired
    TranslationService translationService;
}
