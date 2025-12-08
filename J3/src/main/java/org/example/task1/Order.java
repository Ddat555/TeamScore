package org.example.task1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    protected final List<OrderItem> orderItemList;
    protected final Storage fromStorage;

    public Order(Storage fromStorage) {
        this.orderItemList = new ArrayList<>();
        this.fromStorage = fromStorage;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void addOrderItem(OrderItem orderItem){
        orderItemList.add(orderItem);
    }

    public BigDecimal getTotalAmount() {
        BigDecimal sum = BigDecimal.ZERO;
        for(OrderItem item: orderItemList){
            sum = sum.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return sum;
    }

    public BigDecimal promoSum(String[] promoArticles){
        BigDecimal sum = BigDecimal.ZERO;
        for(String promoArt: promoArticles){
            for(OrderItem item: orderItemList){
                if(promoArt.equals(item.getArticle())){
                    sum = sum.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                    break;
                }
            }
        }
        return sum;
    }

    BigDecimal itemAmount(String id) {
        for(OrderItem item: orderItemList){
            if(item.getId().equals(id)){
                return item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            }
        }
        return BigDecimal.ZERO;
    }
}
