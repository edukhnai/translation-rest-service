package com.dukhnai.translationservice.service.impl;

import com.dukhnai.translationservice.exception.ConnectionEstablishingException;
import com.dukhnai.translationservice.service.TranslationService;
import com.dukhnai.translationservice.util.TextUtil;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslationServiceImpl implements TranslationService {

    private static final String urlString = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20190323T115427Z.08af0a3a8ef3f025.5e913fd5983617e32ce6ba3f11388c6829cfa738";

    @Autowired
    private TextUtil textUtil;

    @Override
    public String getTextTranslation(String text, String fromLanguage, String toLanguage) throws ConnectionEstablishingException {
        List<Future<String>> translatingTasks = new ArrayList<>();
        StringBuilder translatedText = new StringBuilder();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (String word : textUtil.getWords(text)) {

            translatingTasks.add(executorService.submit(() -> {
                try {
                    URL urlObject = new URL(urlString);
                    HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);

                    DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                    dataOutputStream.writeBytes(textUtil.getJoinedTranslationRequestParameters(word, fromLanguage, toLanguage));

                    Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

                    JSONObject jsonObject;
                    jsonObject = new JSONObject(textUtil.getContent(in));

                    return (String) jsonObject.getJSONArray("text").get(0);

                } catch (IOException e) {
                    throw new ConnectionEstablishingException(e.getMessage());
                }
            }));
        }

        for (Future<String> taskResult : translatingTasks) {
            try {
                translatedText.append(taskResult.get()).append(" ");

            } catch (InterruptedException | ExecutionException e) {
                throw new ConnectionEstablishingException(e.getMessage());
            }
        }

        return translatedText.toString().trim();
    }
}

