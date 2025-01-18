package org.example.helpers;

public class Condition {
    private String column;
    private String operator;
    private String value;

    public Condition(String conditionString) {
        // Parse conditionString and set column, operator, value
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
