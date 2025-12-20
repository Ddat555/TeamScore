package ru.teamscore.task1;

public class Main {
//    UnicodeCharInfo unicodeCharInfo = new UnicodeCharInfo('0');

    public static void main(String[] args) {
        UnicodeCharInfo unicodeCharInfo = new UnicodeCharInfo('0');
        System.out.println(unicodeCharInfo.getUnicodeInt());
        System.out.println(unicodeCharInfo.getUnicodeHex());
        System.out.println(unicodeCharInfo.getNextChar());
    }
}
