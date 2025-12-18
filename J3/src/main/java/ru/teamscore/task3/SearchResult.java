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

    public SearchResult(Product product){
        this.article = product.getArticle();
        this.nameProduct = product.getName();
        this.finalPrice = product.getFinalPrice();
        this.supplierName = product.getSupplier().getName();
        this.address = product.getSupplier().getAddress();
        this.manufacturerName = product.getManufacturer().getName();
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

    @Override
    public String toString() {
        return "SearchResult{" +
                "article='" + article + '\'' +
                ", nameProduct='" + nameProduct + '\'' +
                ", finalPrice=" + finalPrice +
                ", supplierName='" + supplierName + '\'' +
                ", address='" + address + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                '}';
    }
}
