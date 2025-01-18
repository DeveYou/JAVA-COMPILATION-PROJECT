package org.example.helpers;

import java.util.List;

public class Columns {
    private List<String> columnNames;

    public Columns(String columnsString) {
        // Parse columnsString and populate columnNames list
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }
}
