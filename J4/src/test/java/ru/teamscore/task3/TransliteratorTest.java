package ru.teamscore.task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransliteratorTest {

    private final Transliterator translator = new Transliterator();

    @Test
    public void testBasicTransliteration() {
        String result = translator.transliteration("чаша", TranslitType.TO_LATIN);
        assertEquals("chasha", result);
    }

    @Test
    public void testReverseTransliteration() {
        String result = translator.transliteration("chasha", TranslitType.FROM_LATIN);
        assertEquals("чаша", result);
    }
}