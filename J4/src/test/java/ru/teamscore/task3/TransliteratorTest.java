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

    @Test
    public void testCasePreservation() {
        assertEquals("Yuliya Shheglova",
                translator.transliteration("Юлия Щеглова", TranslitType.TO_LATIN));
    }

    @Test
    public void testReverseCasePreservation() {
        assertEquals("Юлия Щеглова",
                translator.transliteration("Yuliya Shheglova", TranslitType.FROM_LATIN));
    }

    @Test
    public void testSpecialCharacters() {
        assertEquals("Joshkar-Ola, moj yozh",
                translator.transliteration("Йошкар-Ола, мой ёж", TranslitType.TO_LATIN));
    }

    @Test
    public void testReverseSpecialCharacters() {
        assertEquals("Йошкар-Ола, мой ёж",
                translator.transliteration("Joshkar-Ola, moj yozh", TranslitType.FROM_LATIN));
    }

    @Test
    public void testMixedText() {
        assertEquals("Hello, mir! 123 test",
                translator.transliteration("Hello, мир! 123 test", TranslitType.TO_LATIN));
    }

    @Test
    public void testReverseMixedText() {
        assertEquals("Привет, мир! 123 тест",
                translator.transliteration("Privet, мир! 123 test", TranslitType.FROM_LATIN));
    }

}