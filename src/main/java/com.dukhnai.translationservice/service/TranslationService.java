package com.dukhnai.translationservice.service;

import com.dukhnai.translationservice.exception.TranslationException;

public interface TranslationService {

    String getTextTranslation(String text, String fromLanguage, String toLanguage) throws TranslationException;
}
