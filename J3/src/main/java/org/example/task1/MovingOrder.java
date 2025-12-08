package org.example.task1;

public class MovingOrder extends Order{
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
}
