package ru.teamscore.task1;

public class SaleOrder extends Order{
    private final String customer;

    public SaleOrder(String customer, Storage fromStorage) {
        super(fromStorage);
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public boolean isWholesale(double minQuantity) {
        double sumQuantity = 0;
        for(OrderItem item : orderItemList){
            if(item.getQuantity() >= minQuantity){
                return true;
            }
            sumQuantity += item.getQuantity();
        }
        return sumQuantity >= minQuantity;
    }
}
