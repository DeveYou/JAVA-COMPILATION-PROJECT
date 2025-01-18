package org.example.helpers;

public class Aggregation {
    private final String type;
    private final String column;

    public Aggregation(String type, String column) {
        this.type = type;
        this.column = column;
    }

    public String getType() {
        return type;
    }

    public String getColumn() {
        return column;
    }

}
