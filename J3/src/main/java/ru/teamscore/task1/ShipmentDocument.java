package ru.teamscore.task1;

import java.util.*;

/**
 * Документ отгрузки со склада.
 * Бывает двух типов: перемещение (на другой склад) и продажа (покупателю).
 *
 */
abstract class ShipmentDocument {
    protected final String documentId; // GUID документа
    protected final Date documentDate; // дата документа
    protected final DocumentType documentType; // тип отгрузки: sale - продажа, moving - перемещение
    protected final Order order;

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
    abstract double promoSum(String[] promoArticles);
}