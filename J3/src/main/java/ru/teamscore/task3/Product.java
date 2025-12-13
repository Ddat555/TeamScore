package ru.teamscore.task3;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private final String article;
    private String name;
    private BigDecimal basePrice;
    private Manufacturer manufacturer;
    private Supplier supplier;

    public Product(String article, String name, BigDecimal price, Manufacturer manufacturer, Supplier supplier) {
        this.article = article;
        this.name = name;
        this.basePrice = price;
        this.manufacturer = manufacturer;
        this.supplier = supplier;
    }

    public BigDecimal getFinalPrice() {
        if (supplier instanceof Dealer dealer) {
            return basePrice.multiply(
                    BigDecimal.ONE.add(
                            BigDecimal.valueOf(dealer.getPercent() / 100.0)
                    )
            ).setScale(2, RoundingMode.HALF_UP);
        }
        return basePrice;
    }

    public String getArticle() {
        return article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
