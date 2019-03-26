package com.zycus.validator.core.ex;

public class CellValueTypeException extends RuntimeException {

    private Class<?> clazz;

    public CellValueTypeException(Class<?> clazz) {
        this.clazz = clazz;
    }

    public CellValueTypeException(String message, Class<?> clazz) {
        super(message);
        this.clazz = clazz;
    }

    public CellValueTypeException(String message, Throwable cause, Class<?> clazz) {
        super(message, cause);
        this.clazz = clazz;
    }

    public CellValueTypeException(Throwable cause, Class<?> clazz) {
        super(cause);
        this.clazz = clazz;
    }

    public CellValueTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Class<?> clazz) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.clazz = clazz;
    }

    public Class<?> getExpectedType() {
        return clazz;
    }
}
