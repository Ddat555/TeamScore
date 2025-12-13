package ru.teamscore.task1;

import java.math.BigDecimal;

public class MovingOrder extends Order {
    private final Storage toStorage;

    public MovingOrder(Storage fromStorage, Storage storage) {
        super(fromStorage);
        this.toStorage = storage;
    }

    public Storage getToStorage() {
        return toStorage;
    }

    boolean isInternalMovement() {
        return toStorage.getOwner().equals(fromStorage.getOwner());
    }

    @Override
    public BigDecimal promoSum(String[] promoArticles, double discount) {
        return super.promoSum(promoArticles, 0);
    }
}
