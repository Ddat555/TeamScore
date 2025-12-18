package ru.teamscore.task1;

import java.math.BigDecimal;
import java.util.Date;

public class MovingDocument extends ShipmentDocument{
    public MovingDocument(String documentId, Date documentDate, Order order) {
        super(documentId, documentDate, order);
    }

    @Override
    double promoSum(String[] promoArticles) {
        return order.promoSum(promoArticles,0.0).doubleValue();
    }

    /**
     * Является ли перемещение внутренним (между складами одного владельца).
     * Для продаж неприменимо!
     */
    boolean isInternalMovement() {
        if (documentType == DocumentType.MOVING) {
            MovingOrder movingOrder = (MovingOrder) order;
            return movingOrder.isInternalMovement();
        }
        return false;
    }
}
