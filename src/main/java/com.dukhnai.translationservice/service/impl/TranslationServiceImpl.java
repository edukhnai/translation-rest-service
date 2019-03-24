package com.dukhnai.translationservice.service.impl;

import com.dukhnai.translationservice.exception.TranslationException;
import com.dukhnai.translationservice.service.TranslationService;
import com.dukhnai.translationservice.util.TextUtil;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslationServiceImpl implements TranslationService {

    private static final String urlString = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20190323T115427Z.08af0a3a8ef3f025.5e913fd5983617e32ce6ba3f11388c6829cfa738";

    @Autowired
    private TextUtil textUtil;

    @Override
    public String getTextTranslation(String textForTranslation, String fromLanguage, String toLanguage) throws TranslationException {
        try {
            TranslatedText translatedText = new TranslatedText();

            for (String part : textUtil.getParts(textForTranslation)) {
                URL urlObject = new URL(urlString);

                HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.writeBytes(textUtil.getJoinedTranslationParameters(part, fromLanguage, toLanguage));

                Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(textUtil.getContent(in));
                    translatedText.add((String) jsonObject.getJSONArray("text").get(0));

                } catch (JSONException e) {
                    throw new TranslationException(e.getMessage());
                }
            }

            return translatedText.getValue();

        } catch (IOException e) {
            throw new TranslationException("Error during input/output");
        }
    }
}

