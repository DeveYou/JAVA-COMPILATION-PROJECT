package org.example.helpers;

import java.util.List;

public class Columns {
    private final List<String> columnNames;

    public Columns(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

}
