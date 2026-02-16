package ru.teamscore.task3;

import com.opencsv.bean.CsvBindByName;

public class Security {
    @CsvBindByName(column = "secid")
    private String secid;
    @CsvBindByName(column = "shortname")
    private String shortname;
    @CsvBindByName(column = "regnumber")
    private String regnumber;
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "emitent_title")
    private String emitentTitle;
    @CsvBindByName(column = "emitent_inn")
    private String emitentInn;
    @CsvBindByName(column = "emitent_okpo")
    private String emitentOkpo;

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getRegnumber() {
        return regnumber;
    }

    public void setRegnumber(String regnumber) {
        this.regnumber = regnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmitentTitle() {
        return emitentTitle;
    }

    public void setEmitentTitle(String emitentTitle) {
        this.emitentTitle = emitentTitle;
    }

    public String getEmitentInn() {
        return emitentInn;
    }

    public void setEmitentInn(String emitentInn) {
        this.emitentInn = emitentInn;
    }

    public String getEmitentOkpo() {
        return emitentOkpo;
    }

    public void setEmitentOkpo(String emitentOkpo) {
        this.emitentOkpo = emitentOkpo;
    }
}
