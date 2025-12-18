package ru.teamscore.task3;

public abstract class Supplier {
    private final String inn;
    private String name;
    private String address;

    public Supplier(String inn, String name, String address) {
        this.inn = inn;
        this.name = name;
        this.address = address;
    }

    public String getInn() {
        return inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
