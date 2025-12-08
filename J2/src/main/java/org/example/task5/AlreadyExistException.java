package org.example.task5;

public class AlreadyExistException extends Exception {
    private final String value;
    private final int position;

    public AlreadyExistException(String value, int position) {
        super();
        this.value = value;
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }
}
