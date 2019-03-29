package com.dukhnai.translationservice.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TextUtilTest {

    private TextUtil textUtil;

    @Before
    public void setUp() {
        this.textUtil = new TextUtil();
    }

    @Test
    public void shouldGetTextFromReaderWhenReaderWithContentIsPassed() throws IOException {
        String expectedContent = "cat dog";
        Reader reader = new StringReader(expectedContent);

        String actualContent = textUtil.getContent(reader);

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void shouldSplitArrayOfStringsWhenTextWithSpacesIsPassed() {
        String wordsInString = "cat dog     parrot";
        String[] expectedWords = new String[]{"cat", "dog", "parrot"};

        String[] actualWords = textUtil.getWords(wordsInString);

        assertArrayEquals(expectedWords, actualWords);
    }

    @Test
    public void shouldCreateStringWithGivenParametersWhenRequestParametersArePassed() throws UnsupportedEncodingException {
        String text = "cat dog";
        String fromLanguage = "en";
        String toLanguage = "ru";
        String expectedString = "text=" + URLEncoder.encode(text, "UTF-8") + "&lang=en-ru";

        String actualString = textUtil.getJoinedTranslationRequestParameters(text, fromLanguage, toLanguage);

        assertEquals(expectedString, actualString);
    }
}
