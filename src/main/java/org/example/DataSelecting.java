package org.example;

import org.example.helpers.Columns;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class DataSelecting {
    public static List<Map<String,String>> selectColumns(List<Map<String, String>> data, Columns columns) {
        if(columns == null || columns.getColumnNames().isEmpty()) {
            System.err.println("Invalid columns to select.");
            return null;
        }
        if(data == null || data.isEmpty()){
            System.err.println("No data to select");
            return null;
        }

        List<String> columnNames = columns.getColumnNames();
        return data.stream()
                .map(row -> {
                    Map<String, String> selectedRow = row.entrySet().stream()
                            .filter(entry -> columnNames.contains(entry.getKey()))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    return selectedRow;

                }).collect(Collectors.toList());
    }
}