package com.dukhnai.translationservice.service.impl;

import com.dukhnai.translationservice.exception.ConnectionEstablishingException;
import com.dukhnai.translationservice.service.TranslationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TranslationServiceTest {

    @Autowired
    private TranslationService translationService;

    @Test
    public void shouldReturnTextTranslationWhenTextIsPassed() throws ConnectionEstablishingException {
        String text = "cat dog";
        String fromLanguage = "en";
        String toLanguage = "ru";
        String expectedTranslation = "кошка собака";

        String actualTranslation = translationService.getTextTranslation(text, fromLanguage, toLanguage);

        assertEquals(expectedTranslation, actualTranslation);
    }
}
