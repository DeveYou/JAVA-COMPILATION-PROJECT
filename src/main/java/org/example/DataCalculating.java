package org.example;

import org.example.helpers.Aggregation;

import java.util.List;
import java.util.Map;

public class DataCalculating {

    public static void calculateAggregation(List<Map<String, String>> data, Aggregation aggregation) {
        if (data == null || data.isEmpty() || aggregation == null) {
            System.err.println("Invalid input for aggregation calculation.");
            return;
        }

        String column = aggregation.getColumn();
        String type = aggregation.getType();

        if (column == null || column.isEmpty()) {
            System.err.println("Column name is missing for aggregation.");
            return;
        }
        if (type == null || type.isEmpty()) {
            System.err.println("Aggregation function type is missing.");
            return;
        }


        try {
            switch (type){
                case "MOYENNE" : calculateAverage(data, column); break;
                case "MAX" : calculateMax(data, column); break;
                case "MIN" : calculateMin(data,column); break;
                default:
                    System.err.println("Unknown aggregation function: " + type);
            }

        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in column '" + column + "': " + e.getMessage());
        } catch (Exception e){
            System.err.println("An error occurred during aggregation calculation: " + e.getMessage());
        }

    }

    private static void calculateAverage(List<Map<String, String>> data, String column) {
        double sum = 0;
        int count = 0;

        for (Map<String, String> row : data) {
            String valueStr = row.get(column);
            if (valueStr != null) {
                try {
                    double value = Double.parseDouble(valueStr);
                    sum += value;
                    count++;
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format: " + valueStr);
                    // Skip this row if the number format is invalid
                }
            }
        }

        if (count > 0) {
            double average = sum / count;
            System.out.println("Average of column '" + column + "' is: " + average);
        } else {
            System.out.println("No valid numeric values found in column '" + column + "' to calculate the average.");
        }
    }

    private static void calculateMax(List<Map<String, String>> data, String column) {
        double max = Double.NEGATIVE_INFINITY;
        boolean foundValidValue = false;

        for(Map<String, String> row: data){
            String valueStr = row.get(column);
            if (valueStr != null){
                try{
                    double value = Double.parseDouble(valueStr);
                    if (value > max){
                        max = value;
                        foundValidValue = true;
                    }
                }catch (NumberFormatException e){
                    System.err.println("Invalid number format: " + valueStr);
                }
            }
        }
        if (foundValidValue){
            System.out.println("Max of column '" + column + "' is: " + max);
        } else {
            System.out.println("No valid numeric values found in column '" + column + "' to calculate the max.");
        }
    }

    private static void calculateMin(List<Map<String, String>> data, String column) {
        double min = Double.POSITIVE_INFINITY;
        boolean foundValidValue = false;

        for (Map<String, String> row : data){
            String valueStr = row.get(column);
            if (valueStr != null){
                try{
                    double value = Double.parseDouble(valueStr);
                    if (value < min){
                        min = value;
                        foundValidValue = true;
                    }
                } catch (NumberFormatException e){
                    System.err.println("Invalid number format: " + valueStr);
                }
            }
        }

        if (foundValidValue){
            System.out.println("Min of column '" + column + "' is: " + min);
        } else {
            System.out.println("No valid numeric values found in column '" + column + "' to calculate the min.");
        }
    }
}