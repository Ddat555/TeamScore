package ru.teamscore.task3;

public class Dealer extends Supplier {

    private Manufacturer manufacturer;
    private double percent;

    public Dealer(String inn, String name, String address, Manufacturer manufacturer, double percent) {
        super(inn, name, address);
        this.manufacturer = manufacturer;
        this.percent = percent;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
