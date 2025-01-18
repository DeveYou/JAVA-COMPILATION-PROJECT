package org.example.helpers;

public class Condition {
    private final String column;
    private final String operator;
    private final String value;

    public Condition(String column, String operator, String value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public String getOperator() {
        return operator;
    }

    public String getValue() {
        return value;
    }
}