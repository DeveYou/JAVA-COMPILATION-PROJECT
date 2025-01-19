package org.example;

import org.example.helpers.Aggregation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataCalculating {

    public static List<Map<String, String>> calculateAggregation(List<Map<String, String>> data, Aggregation aggregation) {
        if (aggregation == null) {
            System.err.println("Invalid aggregation parameters.");
            return null;
        }

        if(data == null || data.isEmpty()){
            System.err.println("No data to calculate");
            return null;
        }


        return data.stream().map(row -> {
            double aggregatedValue = DataGrouping.calculateGroupAggregation(List.of(row), aggregation);
            String aggregatedName = aggregation.getType() + "(" + aggregation.getColumn() + ")";
            row.put(aggregatedName, String.valueOf(aggregatedValue));
            return row;
        }).collect(Collectors.toList());

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