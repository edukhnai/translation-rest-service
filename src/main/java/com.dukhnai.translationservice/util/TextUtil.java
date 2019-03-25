package com.dukhnai.translationservice.util;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.springframework.stereotype.Component;

@Component
public class TextUtil {

    public String getContent(Reader in) throws IOException {
        StringBuilder sb = new StringBuilder();

        for (int c; (c = in.read()) >= 0; ) {
            sb.append((char) c);
        }
        return sb.toString();
    }

    public String[] getWords(String text) {
        return text.split("\\s+");
    }

    public String getJoinedTranslationParameters(String textForTranslation, String fromLanguage, String toLanguage) throws UnsupportedEncodingException {
        return "text=" + URLEncoder.encode(textForTranslation, "UTF-8") + "&lang=" + fromLanguage + "-" + toLanguage;
    }
}
