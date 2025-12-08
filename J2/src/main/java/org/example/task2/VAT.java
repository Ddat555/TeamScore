package org.example.task2;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VAT {

    private BigDecimal basePrice;
    private BigDecimal fullPrice;


    public VAT(BigDecimal price, PriceType priceType) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        BigDecimal vatRate = new BigDecimal("0.20");
        switch (priceType) {
            case WITH_VAT -> {
                fullPrice = price.setScale(2, RoundingMode.HALF_UP);
                basePrice = fullPrice.divide(BigDecimal.ONE.add(vatRate), 10, RoundingMode.HALF_UP)
                        .setScale(2, RoundingMode.HALF_EVEN);
            }
            case WITHOUT_VAT -> {
                basePrice = price.setScale(2, RoundingMode.HALF_UP);
                fullPrice = basePrice.multiply(BigDecimal.ONE.add(vatRate)).setScale(2, RoundingMode.HALF_UP);
            }
        }

    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public BigDecimal getSumVAT() {
        return fullPrice.subtract(basePrice).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSumVATForDeclaration() {
        return fullPrice.subtract(basePrice).setScale(0, RoundingMode.HALF_UP);
    }
}
