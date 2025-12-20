package ru.teamscore.task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnicodeCharInfoTest {

    UnicodeCharInfo charInfo1 = new UnicodeCharInfo('0');
    UnicodeCharInfo charInfo2 = new UnicodeCharInfo('N');
    UnicodeCharInfo charInfo3 = new UnicodeCharInfo('z');
    UnicodeCharInfo charInfo4 = new UnicodeCharInfo('Ж');
    UnicodeCharInfo charInfo5 = new UnicodeCharInfo(' ');
    UnicodeCharInfo charInfo6 = new UnicodeCharInfo('\t');
    UnicodeCharInfo charInfo7 = new UnicodeCharInfo('&');
    UnicodeCharInfo charInfo8 = new UnicodeCharInfo('λ');

    @Test
    void getUnicodeInt() {
        assertEquals(48, charInfo1.getUnicodeInt());
        assertEquals(78, charInfo2.getUnicodeInt());
        assertEquals(122, charInfo3.getUnicodeInt());
        assertEquals(1046, charInfo4.getUnicodeInt());
        assertEquals(32, charInfo5.getUnicodeInt());
        assertEquals(9, charInfo6.getUnicodeInt());
        assertEquals(38, charInfo7.getUnicodeInt());
        assertEquals(955, charInfo8.getUnicodeInt());
    }

    @Test
    void getUnicodeHex() {

        assertEquals("U+0030", charInfo1.getUnicodeHex());
        assertEquals("U+004E", charInfo2.getUnicodeHex());
        assertEquals("U+007A", charInfo3.getUnicodeHex());
        assertEquals("U+0416", charInfo4.getUnicodeHex());
        assertEquals("U+0020", charInfo5.getUnicodeHex());
        assertEquals("U+0009", charInfo6.getUnicodeHex());
        assertEquals("U+0026", charInfo7.getUnicodeHex());
        assertEquals("U+03BB", charInfo8.getUnicodeHex());
    }

    @Test
    void getNextChar() {
        assertEquals('1', charInfo1.getNextChar());
        assertEquals('O', charInfo2.getNextChar());
        assertEquals('{', charInfo3.getNextChar());
        assertEquals('З', charInfo4.getNextChar());
        assertEquals('!', charInfo5.getNextChar());
        assertEquals('\n', charInfo6.getNextChar());
        assertEquals('\'', charInfo7.getNextChar());
        assertEquals('μ', charInfo8.getNextChar());
    }

    @Test
    void getPrevChar() {
        assertEquals('/', charInfo1.getPrevChar());
        assertEquals('M', charInfo2.getPrevChar());
        assertEquals('y', charInfo3.getPrevChar());
        assertEquals('Е', charInfo4.getPrevChar());
        assertEquals('\u001F', charInfo5.getPrevChar());
        assertEquals('\b', charInfo6.getPrevChar());
        assertEquals('%', charInfo7.getPrevChar());
        assertEquals('κ', charInfo8.getPrevChar());
    }

    @Test
    void getCharType() {
        assertEquals(CharType.DIGIT, charInfo1.getCharType());
        assertEquals(CharType.LETTER_UPPER, charInfo2.getCharType());
        assertEquals(CharType.LETTER_LOWER, charInfo3.getCharType());
        assertEquals(CharType.LETTER_UPPER, charInfo4.getCharType());
        assertEquals(CharType.SPACE, charInfo5.getCharType());
        assertEquals(CharType.SPACE, charInfo6.getCharType());
        assertEquals(CharType.OTHER, charInfo7.getCharType());
        assertEquals(CharType.OTHER, charInfo8.getCharType());
    }

    @Test
    void isLatinChar() {
        assertEquals(-1, charInfo1.getAlphabetNumber());
        assertEquals(14, charInfo2.getAlphabetNumber());
        assertEquals(26, charInfo3.getAlphabetNumber());
        assertEquals(-1, charInfo4.getAlphabetNumber());
        assertEquals(-1, charInfo5.getAlphabetNumber());
        assertEquals(-1, charInfo6.getAlphabetNumber());
        assertEquals(-1, charInfo7.getAlphabetNumber());
        assertEquals(-1, charInfo8.getAlphabetNumber());
    }
}