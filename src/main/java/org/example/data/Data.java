package org.example.data;

import org.example.DataFiltering;
import org.example.DataGrouping;
import org.example.DataSelecting;
import org.example.helpers.Aggregation;
import org.example.helpers.Columns;
import org.example.helpers.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.example.DataCalculating;
import java.util.stream.Collectors;

public class Data {
    private List<Map<String, String>> initialData;
    private List<Map<String, String>> currentData;
    public Data() {
        this.initialData = new ArrayList<>();
        this.currentData = new ArrayList<>();
    }
    public void setData(List<Map<String, String>> data) {
        this.initialData = new ArrayList<>(data); // initial data
        this.currentData = new ArrayList<>(data); // copy of data
    }
    public void filter(Condition condition) {
        if (condition == null) {
            System.err.println("Invalid filter condition.");
            return;
        }
        if(this.currentData == null || this.currentData.isEmpty()){
            System.err.println("No data to filter");
            return;
        }
        this.currentData =  DataFiltering.filterData(currentData, condition);
    }

    public void select(Columns columns) {
        if(columns == null || columns.getColumnNames().isEmpty()) {
            System.err.println("Invalid columns to select.");
            return;
        }
        if(this.currentData == null || this.currentData.isEmpty()){
            System.err.println("No data to select");
            return;
        }
        this.currentData =  DataSelecting.selectColumns(currentData, columns);
    }

    public void calculate(Aggregation aggregation) {
        if (aggregation == null) {
            System.err.println("Invalid aggregation parameters.");
            return;
        }
        if(this.currentData == null || this.currentData.isEmpty()){
            System.err.println("No data to calculate");
            return;
        }
        this.currentData = DataCalculating.calculateAggregation(currentData, aggregation);
    }

    public void group(Columns groupColumns, Aggregation aggregation) {
        if(groupColumns == null)
        {
            System.err.println("No columns to group with");
            return;
        }
        if(this.currentData == null || this.currentData.isEmpty()){
            System.err.println("No data to group");
            return;
        }

        DataGrouping.groupData(currentData, groupColumns, aggregation);
    }
    public List<Map<String, String>> getData() {
        return currentData;
    }

    public void resetData() {
        this.currentData = new ArrayList<>(this.initialData);
    }
}