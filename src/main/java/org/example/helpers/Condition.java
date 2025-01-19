package org.example.helpers;

import java.util.Map;

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

    public boolean matches(Map<String, String> row) {
        if (row == null || !row.containsKey(column)) {
            return false;
        }

        String rowValueStr = row.get(column);
        if (rowValueStr == null) {
            return false;
        }

        try {
            if(isNumeric(value) && isNumeric(rowValueStr)){
                double rowValue = Double.parseDouble(rowValueStr);
                double conditionValue = Double.parseDouble(value);
                return compareNumeric(rowValue, conditionValue);
            } else {
                return compareString(rowValueStr, value);
            }
        } catch (NumberFormatException e) {
            return compareString(rowValueStr, value);
        }
    }
    private boolean compareNumeric(double rowValue, double conditionValue){
        return switch (operator) {
            case "=" -> rowValue == conditionValue;
            case "!=" -> rowValue != conditionValue;
            case "<" -> rowValue < conditionValue;
            case ">" -> rowValue > conditionValue;
            case "<=" -> rowValue <= conditionValue;
            case ">=" -> rowValue >= conditionValue;
            default -> false; // Invalid operator
        };
    }

    private boolean compareString(String rowValue, String conditionValue){
        return switch (operator) {
            case "=" -> rowValue.equals(conditionValue);
            case "!=" -> !rowValue.equals(conditionValue);
            default -> {
                System.err.println("Invalid operator " + operator + " with string values" );
                yield false;
            }
        };
    }

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}