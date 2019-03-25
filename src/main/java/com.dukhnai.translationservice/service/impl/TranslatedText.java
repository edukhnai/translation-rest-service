package com.dukhnai.translationservice.service.impl;

class TranslatedText {

    private StringBuilder result;

    TranslatedText() {
        this.result = new StringBuilder();
    }

    String getValue() {
        return result.toString().trim();
    }

    synchronized void add(String text) {
        result.append(text).append(" ");
    }
}
