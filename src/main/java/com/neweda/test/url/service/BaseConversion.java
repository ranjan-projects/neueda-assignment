package com.neweda.test.url.service;

import org.springframework.stereotype.Component;

@Component
public class BaseConversion {

    private static final String ALLOWED_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final char[] allowedCharacters = ALLOWED_STRING.toCharArray();
    private static final int BASE = allowedCharacters.length;

    public String encode(long input) {
        StringBuilder encodedString = new StringBuilder();

        if (input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }

        while (input > 0) {
            encodedString.append(allowedCharacters[(int) (input % BASE)]);
            input = input / BASE;
        }

        return encodedString.reverse().toString();
    }

    public long decode(String input) {
        char[] characters = input.toCharArray();
        int length = characters.length;

        int decoded = 0;
        double counter = 1;
        for (char character : characters) {
            decoded += ALLOWED_STRING.indexOf(character) * Math.pow(BASE, length - counter);
            counter++;
        }
        return decoded;
    }
}
