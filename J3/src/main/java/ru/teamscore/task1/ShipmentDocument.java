package ru.teamscore.task1;

import java.util.*;

/**
 * Документ отгрузки со склада.
 * Бывает двух типов: перемещение (на другой склад) и продажа (покупателю).
 * 
 */
class ShipmentDocument {
    private final String documentId; // GUID документа
    private final Date documentDate; // дата документа
    private final DocumentType documentType; // тип отгрузки: sale - продажа, moving - перемещение
    private final Order order;

    public ShipmentDocument(String documentId, Date documentDate, Order order) {
        this.documentId = documentId;
        this.documentDate = documentDate;
        this.order = order;
        this.documentType = (order instanceof SaleOrder) ?
                DocumentType.SALE : DocumentType.MOVING;
    }

    public String getDocumentId() {
        return documentId;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public Order getOrder() {
        return order;
    }

    /**
     * Суммарная стоимость товаров в документе.
     */
    double totalAmount() {
        return order.getTotalAmount().doubleValue();
    }

    /**
     * Стоимость товара с переданным id.
     */
    double itemAmount(String id) {
        return order.itemAmount(id).doubleValue();
    }

    /**
     * Суммарная стоимость товаров, попадающих в список промо-акции.
     */
    double promoSum(String[] promoArticles) {
        return order.promoSum(promoArticles, 0.0).doubleValue();
    }

    double promoSum(String[] promoArticles, double discount) {
        return order.promoSum(promoArticles, discount).doubleValue();
    }

    /**
     * Является ли продажа оптовой для переданного минимального объема.
     * Для перемещений неприменимо!
     */
    boolean isWholesale(double minQuantity) {
        if(documentType == DocumentType.SALE){
            SaleOrder saleOrder = (SaleOrder) order;
            return saleOrder.isWholesale(minQuantity);
        }
        return false;

    }

    /**
     * Является ли перемещение внутренним (между складами одного владельца).
     * Для продаж неприменимо!
     */
    boolean isInternalMovement() {
        if(documentType == DocumentType.MOVING){
            MovingOrder movingOrder = (MovingOrder) order;
            return movingOrder.isInternalMovement();
        }
        return false;
    }
}