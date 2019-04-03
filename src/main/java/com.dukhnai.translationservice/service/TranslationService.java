package com.dukhnai.translationservice.service;

import com.dukhnai.translationservice.exception.ConnectionEstablishingException;

public interface TranslationService {

    String getTextTranslation(String text, String fromLanguage, String toLanguage) throws ConnectionEstablishingException;
}
