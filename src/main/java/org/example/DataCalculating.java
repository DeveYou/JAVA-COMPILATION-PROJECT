package org.example;

import org.example.helpers.Aggregation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


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
        String aggregationColumn = aggregation.getColumn();
        String aggregationType = aggregation.getType();

        if (aggregationColumn == null || aggregationColumn.isEmpty() ||
                aggregationType == null || aggregationType.isEmpty()) {
            System.err.println("Invalid aggregation parameters.");
            return null;
        }

        double aggregatedValue;
        List<Map<String,String>> result = new ArrayList<>();
        Map<String, String> aggregatedRow = new HashMap<>();

        switch (aggregationType) {
            case "MOYENNE" -> aggregatedValue = calculateAverage(data, aggregationColumn);
            case "MAX" -> aggregatedValue = calculateMax(data, aggregationColumn);
            case "MIN" -> aggregatedValue = calculateMin(data, aggregationColumn);
            case "SOMME" -> aggregatedValue = calculateSum(data, aggregationColumn);
            case "COUNT" -> aggregatedValue = calculateCount(data, aggregationColumn);
            default -> {
                System.err.println("Invalid aggregation type: " + aggregationType);
                return null;
            }
        }
        String aggregatedName = aggregationType + "(" + aggregationColumn + ")";
        aggregatedRow.put(aggregatedName, String.valueOf(aggregatedValue));
        result.add(aggregatedRow);
        return result;
    }


    private static double calculateAverage(List<Map<String, String>> data, String aggregationColumn) {
        double sum = 0;
        int count = 0;

        for (Map<String, String> row : data) {
            String valueStr = row.get(aggregationColumn);
            if (valueStr != null) {
                try {
                    double value = Double.parseDouble(valueStr);
                    sum += value;
                    count++;
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format: " + valueStr + ", skipping row for average calculation");
                }
            }else {
                System.err.println("Missing value for column: " + aggregationColumn + ", skipping row for average calculation");
            }
        }

        if (count > 0) {
            return sum / count;
        }
        return 0;
    }

    private static double calculateMax(List<Map<String, String>> data, String aggregationColumn) {
        double max = Double.NEGATIVE_INFINITY;
        boolean foundValidValue = false;

        for(Map<String, String> row: data){
            String valueStr = row.get(aggregationColumn);
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
            return max;
        }
        return 0;
    }

    private static double calculateMin(List<Map<String, String>> data, String aggregationColumn) {
        double min = Double.POSITIVE_INFINITY;
        boolean foundValidValue = false;

        for(Map<String, String> row: data){
            String valueStr = row.get(aggregationColumn);
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
            return min;
        }
        return 0;
    }

    private static double calculateSum(List<Map<String, String>> data, String aggregationColumn) {
        double sum = 0;
        boolean foundValidValue = false;

        for (Map<String, String> row : data) {
            String valueStr = row.get(aggregationColumn);
            if (valueStr != null){
                try{
                    double value = Double.parseDouble(valueStr);
                    sum += value;
                    foundValidValue = true;
                } catch (NumberFormatException e){
                    System.err.println("Invalid number format: " + valueStr);
                }
            }
        }

        if (foundValidValue){
            return sum;
        }
        return 0;
    }
    private static double calculateCount(List<Map<String, String>> data, String aggregationColumn){

        int count = 0;
        for(Map<String,String> row: data){
            if(row.containsKey(aggregationColumn) && row.get(aggregationColumn) != null){
                count++;
            }
        }
        return count;
    }

}