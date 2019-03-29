package com.dukhnai.translationservice.controller;

import com.dukhnai.translationservice.TranslationApplication;
import com.dukhnai.translationservice.dto.TextForTranslation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TranslationApplication.class)
@SpringJUnitWebConfig
public class TranslationControllerTest {

    @Autowired
    private TranslationController translationController;

    @Test
    public void shouldGetTextTranslationWhenClientRequestIsSent() throws Exception {
        String expectedResponse = "кошка собака";
        TextForTranslation requestData = new TextForTranslation("cat dog", "en", "ru");

        String actualResponse = translationController.processTextTranslation(requestData);

        assertEquals(expectedResponse, actualResponse);
    }
}
