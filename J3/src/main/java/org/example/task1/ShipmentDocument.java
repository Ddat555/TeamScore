package org.example.task1;

import java.math.BigDecimal;
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
        // Автоматически определяем тип по классу Order
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
    public BigDecimal totalAmount() {
        return order.getTotalAmount();
    }

    /**
     * Стоимость товара с переданным id.
     */
    public BigDecimal itemAmount(String id) {
        return order.itemAmount(id);
    }

    /**
     * Суммарная стоимость товаров, попадающих в список промо-акции.
     */
    public BigDecimal promoSum(String[] promoArticles) {
        return order.promoSum(promoArticles);
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