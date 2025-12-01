package org.example.task5;

public enum TimeForm {
    DAY("день", "дня", "дней"),
    HOUR("час", "часа", "часов"),
    MINUTE("минута", "минуты", "минут");

    private final String form1;
    private final String form2;
    private final String form3;

    TimeForm(String form1, String form2, String form3) {
        this.form1 = form1;
        this.form2 = form2;
        this.form3 = form3;
    }

    public String getForm1() {
        return form1;
    }

    public String getForm2() {
        return form2;
    }

    public String getForm3() {
        return form3;
    }
}
