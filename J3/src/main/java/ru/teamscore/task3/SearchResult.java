package ru.teamscore.task3;

import java.math.BigDecimal;

public class SearchResult {
    private final String article;
    private final String nameProduct;
    private final BigDecimal finalPrice;
    private final String supplierName;
    private final String address;
    private final String manufacturerName;

    public SearchResult(String article, String nameProduct, BigDecimal finalPrice, String supplierName, String address, String manufacturerName) {
        this.article = article;
        this.nameProduct = nameProduct;
        this.finalPrice = finalPrice;
        this.supplierName = supplierName;
        this.address = address;
        this.manufacturerName = manufacturerName;
    }

    public String getArticle() {
        return article;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getAddress() {
        return address;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }
}
