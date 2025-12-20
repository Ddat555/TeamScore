package ru.teamscore.task3;

public class Transliterator {

    private static final String[][] TRANSLIT_PAIRS = {
            {"щ", "shh"}, {"ш", "sh"}, {"ч", "ch"}, {"ж", "zh"}, {"ю", "yu"}, {"я", "ya"},
            {"ё", "yo"}, {"й", "j"}, {"ъ", "``"}, {"ь", "`"}, {"ц", "c"}, {"а", "a"}, {"б", "b"},
            {"в", "v"}, {"г", "g"}, {"д", "d"}, {"е", "e"}, {"з", "z"}, {"и", "i"}, {"к", "k"},
            {"л", "l"}, {"м", "m"}, {"н", "n"}, {"о", "o"}, {"п", "p"}, {"р", "r"}, {"с", "s"},
            {"т", "t"}, {"у", "u"}, {"ф", "f"}, {"х", "h"}, {"ы", "y"}, {"э", "e`"}
    };

    public String transliteration(String text, TranslitType translitType) {
        switch (translitType) {
            case TO_LATIN -> {
                return toLatin(text);
            }
            case FROM_LATIN -> {
                return fromLatin(text);
            }
            default -> {
                return null;
            }
        }
    }

    private static String fromLatin(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            boolean found = false;
            for (int len = 4; len >= 1; len--) {
                if (i + len <= text.length()) {
                    String substr = text.substring(i, i + len).toLowerCase();
                    for (String[] rule : TRANSLIT_PAIRS) {
                        String lat = rule[1];
                        String cyr = rule[0];

                        if (lat.equals(substr)) {
                            char firstChar = text.charAt(i);
                            if (Character.isUpperCase(firstChar)) {
                                result.append(cyr.substring(0, 1).toUpperCase());
                                if (cyr.length() > 1) {
                                    result.append(cyr.substring(1));
                                }
                            } else {
                                result.append(cyr);
                            }
                            i += len - 1;
                            found = true;
                            break;
                        }
                    }
                    if (found) break;
                }
            }
            if (!found) {
                result.append(text.charAt(i));
            }
        }
        return result.toString();
    }

    private static String toLatin(String text) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            boolean found = false;
            for (String[] rule : TRANSLIT_PAIRS) {
                String cyr = rule[0];
                String lat = rule[1];

                if (text.substring(i).toLowerCase().startsWith(cyr)) {
                    if (Character.isUpperCase(currentChar)) {
                        result.append(Character.toUpperCase(lat.charAt(0)));
                        if (lat.length() > 1) {
                            result.append(lat.substring(1));
                        }
                    } else {
                        result.append(lat);
                    }
                    i += cyr.length() - 1;
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.append(currentChar);
            }
        }
        return result.toString();
    }
}
