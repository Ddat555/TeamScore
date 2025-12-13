package ru.teamscore.task1;

import java.math.BigDecimal;

public class OrderItem {
    private final String id;
    private final String article;
    private String title;
    private double quantity;
    private BigDecimal price;

    public OrderItem(String id, String article, String title, double quantity, BigDecimal price) {
        this.id = id;
        this.article = article;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getArticle() {
        return article;
    }

    public String getTitle() {
        return title;
    }

    public double getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
