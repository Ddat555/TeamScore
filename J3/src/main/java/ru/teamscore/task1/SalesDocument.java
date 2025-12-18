package ru.teamscore.task1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class SalesDocument extends ShipmentDocument {

    private  double discount;

    public SalesDocument(String documentId, Date documentDate, Order order, double discount) {

        super(documentId, documentDate, order);
        this.discount = discount;
    }


    /**
     * Является ли продажа оптовой для переданного минимального объема.
     * Для перемещений неприменимо!
     */
    boolean isWholesale(double minQuantity) {
        SaleOrder saleOrder = (SaleOrder) order;
        return saleOrder.isWholesale(minQuantity);
    }

    @Override
    double promoSum(String[] promoArticles) {
        return order.promoSum(promoArticles,discount).doubleValue();
    }


}
