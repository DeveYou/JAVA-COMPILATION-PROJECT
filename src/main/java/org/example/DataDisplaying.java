package org.example;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataDisplaying {
    public static void displayTable(List<Map<String, String>> data) {
        if (data == null || data.isEmpty()) {
            System.out.println("No data to display.");
            return;
        }

        // Get the headers (column names)
        Set<String> headers = data.get(0).keySet();

        // Print the headers
        for (String header : headers) {
            System.out.printf("%-20s",header);
        }
        System.out.println();

        // Print separator line
        for (int i = 0; i < headers.size(); i++) {
            System.out.printf("%-20s","-------------------");
        }
        System.out.println();

        // Print the rows
        for (Map<String,String> row : data){
            for(String header : headers){
                System.out.printf("%-20s", row.get(header));
            }
            System.out.println();
        }
    }
}