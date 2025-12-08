package org.example.task4;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private boolean isValid = true;
    private List<String> exceptionMessageList = new ArrayList<>();


    public ValidationResult() {
    }

    public ValidationResult(boolean isValid) {
        this.isValid = isValid;
    }

    public ValidationResult(boolean isValid, List<String> exceptionMessageList) {
        this.isValid = isValid;
        this.exceptionMessageList = exceptionMessageList;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<String> getExceptionMessageList() {
        return exceptionMessageList;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void addExceptionMessage(String message){
        exceptionMessageList.add(message);
    }
}
