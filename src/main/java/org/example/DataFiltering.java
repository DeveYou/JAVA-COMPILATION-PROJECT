package org.example;

import org.example.helpers.Condition;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.stream.Collectors;

public class DataFiltering {

    public static List<Map<String, String>> filterData(List<Map<String, String>> data, Condition condition) {
        if (condition == null) {
            System.err.println("Invalid filter condition.");
            return null;
        }
        if(data == null || data.isEmpty()){
            System.err.println("No data to filter");
            return null;
        }

        return   data.stream()
                .filter(condition::matches)
                .collect(Collectors.toList());
    }

    private static boolean evaluateCondition(String cellValue, String operator, String filterValue) {
        try{
            switch (operator){
                case "=": return cellValue.equals(filterValue);
                case "!=": return !cellValue.equals(filterValue);
                case "<": return  Double.parseDouble(cellValue) < Double.parseDouble(filterValue);
                case ">": return  Double.parseDouble(cellValue) > Double.parseDouble(filterValue);
                case "<=": return  Double.parseDouble(cellValue) <= Double.parseDouble(filterValue);
                case ">=": return  Double.parseDouble(cellValue) >= Double.parseDouble(filterValue);
                default: return false; //Invalid operator
            }
        } catch (NumberFormatException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
}