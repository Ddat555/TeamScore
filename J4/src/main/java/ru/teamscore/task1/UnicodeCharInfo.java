package ru.teamscore.task1;

public class UnicodeCharInfo {

    private final char aChar;

    public UnicodeCharInfo(char aChar) {
        this.aChar = aChar;
    }

    public int getUnicodeInt() {
        return aChar;
    }

    public String getUnicodeHex() {
        return String.format("U+%04X", (int) aChar);
    }

    public char getNextChar() {
        return (char) (aChar + 1);
    }

    public char getPrevChar() {
        return (char) (aChar - 1);
    }

    public CharType getCharType() {
        if (Character.isDigit(aChar)) {
            return CharType.DIGIT;
        }
        if (Character.isWhitespace(aChar)) {
            return CharType.SPACE;
        }

        if (Character.isLetter(aChar)) {
            if ((aChar >= 'A' && aChar <= 'Z') || (aChar >= 'a' && aChar <= 'z')) {
                return Character.isLowerCase(aChar) ? CharType.LETTER_LOWER : CharType.LETTER_UPPER;
            }
            if ((aChar >= 'А' && aChar <= 'Я') || (aChar >= 'а' && aChar <= 'я') || aChar == 'Ё' || aChar == 'ё') {
                return Character.isLowerCase(aChar) ? CharType.LETTER_LOWER : CharType.LETTER_UPPER;
            }
        }

        return CharType.OTHER;
    }

    public int getAlphabetNumber() {
        if (Character.toUpperCase(aChar) >= 65 && Character.toUpperCase(aChar) <= 90) {
            return Character.toUpperCase(aChar) - 64;
        }
        return -1;
    }


}
