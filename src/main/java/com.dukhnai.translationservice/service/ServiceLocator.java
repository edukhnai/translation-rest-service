package com.dukhnai.translationservice.service;

public interface ServiceLocator {

    RequestService getRequestService();

    TranslationService getTranslationService();
}
