package org.example;

import org.example.helpers.Aggregation;
import org.example.helpers.Columns;

import java.util.*;
import java.util.stream.Collectors;

public class DataGrouping {
    public static void groupData(List<Map<String, String>> data, Columns groupColumns, Aggregation aggregation) {
        if (data == null || data.isEmpty() || groupColumns == null) {
            System.err.println("Invalid input for group operation.");
            return;
        }

        List<String> columns = groupColumns.getColumnNames();
        if (columns == null || columns.isEmpty()){
            System.err.println("Invalid columns for group operation.");
            return;
        }

        try{
            Map<List<String>, List<Map<String, String>>> groupedData = data.stream()
                    .collect(Collectors.groupingBy(row -> columns.stream()
                            .map(row::get)
                            .collect(Collectors.toList())));

            List<Map<String, String>> result = new ArrayList<>();
            for (Map.Entry<List<String>, List<Map<String, String>>> entry : groupedData.entrySet()) {
                List<String> groupKeys = entry.getKey();
                List<Map<String, String>> groupRows = entry.getValue();
                Map<String, String> aggregatedRow = new LinkedHashMap<>();
                for (int i = 0; i < columns.size(); i++) {
                    aggregatedRow.put(columns.get(i), groupKeys.get(i));
                }
                if(aggregation != null){
                    String aggregationColumn = aggregation.getColumn();
                    String aggregationType = aggregation.getType();

                    if (aggregationColumn != null && !aggregationColumn.isEmpty() &&
                            aggregationType != null && !aggregationType.isEmpty()) {
                        double aggregatedValue =  calculateGroupAggregation(groupRows, aggregation);
                        aggregatedRow.put(aggregationType+"("+aggregationColumn+")",String.valueOf(aggregatedValue));
                    }
                }
                result.add(aggregatedRow);
            }

            data.clear();
            data.addAll(result);

        } catch (Exception e){
            System.err.println("An error occurred during group operation: " + e.getMessage());
        }

    }

    private static double calculateGroupAggregation(List<Map<String, String>> groupRows, Aggregation aggregation){
        String aggregationColumn = aggregation.getColumn();
        String aggregationType = aggregation.getType();
        double aggregatedValue = 0;
        switch (aggregationType) {
            case "MOYENNE":
                aggregatedValue = calculateGroupAverage(groupRows, aggregationColumn);
                break;
            case "MAX":
                aggregatedValue = calculateGroupMax(groupRows,aggregationColumn);
                break;
            case "MIN":
                aggregatedValue = calculateGroupMin(groupRows, aggregationColumn);
                break;
            case "SOMME":
                aggregatedValue = calculateGroupSum(groupRows, aggregationColumn); // Added SOMME case
                break;
            default:
                System.err.println("Invalid aggregation type");
                aggregatedValue = 0.0;
                break;
        }
        return aggregatedValue;
    }

    private static double calculateGroupAverage(List<Map<String, String>> groupRows, String aggregationColumn) {
        double sum = 0;
        int count = 0;

        for (Map<String, String> row : groupRows) {
            String valueStr = row.get(aggregationColumn);
            if (valueStr != null){
                try{
                    double value = Double.parseDouble(valueStr);
                    sum += value;
                    count++;
                } catch (NumberFormatException e){
                    System.err.println("Invalid number format: " + valueStr);
                    // Skip this row if the number format is invalid
                }
            }
        }

        if (count > 0) {
            return sum / count;
        }
        return 0;
    }

    private static double calculateGroupMax(List<Map<String, String>> groupRows, String aggregationColumn) {
        double max = Double.NEGATIVE_INFINITY;
        boolean foundValidValue = false;

        for(Map<String, String> row: groupRows){
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

    private static double calculateGroupMin(List<Map<String, String>> groupRows, String aggregationColumn) {
        double min = Double.POSITIVE_INFINITY;
        boolean foundValidValue = false;

        for(Map<String, String> row: groupRows){
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

    private static double calculateGroupSum(List<Map<String, String>> groupRows, String aggregationColumn) {
        double sum = 0;
        boolean foundValidValue = false;

        for (Map<String, String> row : groupRows) {
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
}