package org.example;

import org.example.helpers.Condition;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class DataFiltering {

    public static void filterData(List<Map<String, String>> data, Condition condition) {
        if (data == null || data.isEmpty() || condition == null) {
            System.err.println("Invalid input for filter operation.");
            return;
        }
        String column = condition.getColumn();
        String operator = condition.getOperator();
        String value = condition.getValue();

        if (column == null || column.isEmpty() || operator == null || operator.isEmpty() || value == null || value.isEmpty()){
            System.err.println("Invalid condition for filter.");
            return;
        }

        // Using iterator to remove elements safely while iterating
        for (Iterator<Map<String, String>> it = data.iterator(); it.hasNext();) {
            Map<String, String> row = it.next();
            String cellValue = row.get(column);

            if (cellValue == null) {
                it.remove();
                continue; // Skip this row, null values don't pass the filter
            }
            try{
                boolean removeRow = !evaluateCondition(cellValue, operator, value);
                if(removeRow){
                    it.remove();
                }
            } catch (NumberFormatException e){
                System.err.println("Invalid Number Format on the value " + cellValue + " of the column " + column);
                it.remove();
            }
            catch (Exception e){
                System.err.println("An error occurred while evaluating condition: " + e.getMessage());
                it.remove();
            }
        }
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