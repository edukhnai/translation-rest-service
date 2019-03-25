package com.dukhnai.translationservice.service;

public interface TranslationService {

    String getTextTranslation(String text, String fromLanguage, String toLanguage);
}
