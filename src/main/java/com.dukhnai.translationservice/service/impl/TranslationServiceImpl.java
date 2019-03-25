package com.dukhnai.translationservice.service.impl;

import com.dukhnai.translationservice.exception.TranslationException;
import com.dukhnai.translationservice.service.TranslationService;
import com.dukhnai.translationservice.util.TextUtil;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private TextUtil textUtil;

    @Override
    public String getTextTranslation(String text, String fromLanguage, String toLanguage) throws TranslationException {
        try {
            TranslatedText translatedText = new TranslatedText();

            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

            for (String word : textUtil.getWords(text)) {

                ScheduledFuture<String> result = executorService.schedule(new TranslationTask(word, fromLanguage, toLanguage, textUtil), 1, TimeUnit.MICROSECONDS);

                translatedText.add(result.get());
            }

            return translatedText.getValue();

        } catch (InterruptedException | ExecutionException e) {
            throw new TranslationException(e.getMessage());
        }
    }

    @AllArgsConstructor
    private static class TranslationTask implements Callable<String> {
        private static final String urlString = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20190323T115427Z.08af0a3a8ef3f025.5e913fd5983617e32ce6ba3f11388c6829cfa738";
        private String word;
        private String fromLanguage;
        private String toLanguage;
        private TextUtil textUtil;

        @Override
        public String call() throws Exception {
            URL urlObject = new URL(urlString);

            HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());

            dataOutputStream.writeBytes(textUtil.getJoinedTranslationParameters(word, fromLanguage, toLanguage));

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(textUtil.getContent(in));

            } catch (JSONException e) {
                throw new TranslationException(e.getMessage());
            }

            return (String) jsonObject.getJSONArray("text").get(0);
        }
    }
}

