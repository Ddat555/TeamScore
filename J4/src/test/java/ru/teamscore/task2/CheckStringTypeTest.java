package ru.teamscore.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckStringTypeTest {

    @Test
    void checkNone() {
        assertEquals(StringType.NONE, CheckStringType.check("@example.com"));
        assertEquals(StringType.NONE, CheckStringType.check("user.name@example"));
        assertEquals(StringType.NONE, CheckStringType.check("71234567890"));
        assertEquals(StringType.NONE, CheckStringType.check("7777-8888-9999"));
        assertEquals(StringType.NONE, CheckStringType.check("a"));
        assertEquals(StringType.NONE, CheckStringType.check("qwerty 456"));
        assertEquals(StringType.NONE, CheckStringType.check("4abc"));
        assertEquals(StringType.NONE, CheckStringType.check("$asdfghjk"));
        assertEquals(StringType.NONE, CheckStringType.check(""));
    }

    @Test
    void checkPhoneType(){
        assertEquals(StringType.PHONE, CheckStringType.check("+7-(123)-456-78-90"));
        assertEquals(StringType.PHONE, CheckStringType.check("+7(123)456-78-90"));
        assertEquals(StringType.PHONE, CheckStringType.check("+7-123-456-78-90"));
        assertEquals(StringType.PHONE, CheckStringType.check("  +71234567890  "));
    }

    @Test
    void checkUserNameType(){
        assertEquals(StringType.USERNAME, CheckStringType.check("user.name.example.com"));
        assertEquals(StringType.USERNAME, CheckStringType.check("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.$1234_"));
        assertEquals(StringType.USERNAME, CheckStringType.check("\taaaa1111\t"));
    }

    @Test
    void checkINNType(){
        assertEquals(StringType.INN, CheckStringType.check("1234567890"));
        assertEquals(StringType.INN, CheckStringType.check("000000000000"));
    }

    @Test
    void checkEmailType(){
        assertEquals(StringType.EMAIL, CheckStringType.check("user.name@example.com"));
        assertEquals(StringType.EMAIL, CheckStringType.check("user_name1@some.example.com"));
    }
}