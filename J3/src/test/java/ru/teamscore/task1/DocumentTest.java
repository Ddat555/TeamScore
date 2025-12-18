package ru.teamscore.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {

    private SalesDocument salesDocument;
    private MovingDocument movingDocument;

    @BeforeEach
    void initDocument(){
        List<OrderItem> orderItemList = List.of(new OrderItem("1", "1", "1", 1.0, BigDecimal.valueOf(100)),
                new OrderItem("2", "2", "2", 2.0, BigDecimal.valueOf(200)),
                new OrderItem("3", "3", "3", 3.0, BigDecimal.valueOf(300)));
        salesDocument = new SalesDocument("1",
                new Date(),
                new SaleOrder("C1", new Storage("N1", "O1"), orderItemList),
                25);


        movingDocument = new MovingDocument("1",
                new Date(),
                new SaleOrder("C1", new Storage("N1", "O1"), orderItemList));
    }


    @Test
    void totalAmountSales() {
        assertEquals(1400.0, salesDocument.totalAmount());
    }

    @Test
    void itemAmountSales() {
        assertEquals(400.0, salesDocument.itemAmount("2"));
    }

    @Test
    void promoSumSales() {
        assertEquals(300.0, salesDocument.promoSum(new String[]{"2"}));
    }

    @Test
    void isWholesale() {
        assertTrue(salesDocument.isWholesale(2));
        assertFalse(salesDocument.isWholesale(10));
    }

    @Test
    void totalAmountMoving() {
        assertEquals(1400.0, movingDocument.totalAmount());
    }

    @Test
    void itemAmountMoving() {
        assertEquals(100.0, movingDocument.itemAmount("1"));
    }

    @Test
    void promoSumMoving() {
        assertEquals(400.0, movingDocument.promoSum(new String[]{"2"}));
    }

    @Test
    void isInternalMovement() {
        assertFalse(movingDocument.isInternalMovement());
    }
}