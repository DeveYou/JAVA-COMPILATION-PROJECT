package org.example;

import org.example.helpers.Columns;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class DataSelecting {
    public static void selectColumns(List<Map<String, String>> data, Columns columns) {
        if (data == null || data.isEmpty() || columns == null) {
            System.err.println("Invalid input for select operation.");
            return;
        }

        List<String> columnNames = columns.getColumnNames();
        if(columnNames == null || columnNames.isEmpty()){
            System.err.println("Invalid columns for select operation.");
            return;
        }

        try{
            List<Map<String, String>> result = new ArrayList<>();
            for(Map<String, String> row : data){
                Map<String, String> selectedRow = new HashMap<>();
                for(String columnName : columnNames){
                    if(row.containsKey(columnName)){
                        selectedRow.put(columnName,row.get(columnName));
                    }
                }
                result.add(selectedRow);
            }
            data.clear();
            data.addAll(result);

        } catch (Exception e){
            System.err.println("An error occurred during select operation: " + e.getMessage());
        }
    }
}