package org.example.data;
import org.example.DataFiltering;
import org.example.DataGrouping;
import org.example.DataSelecting;
import org.example.helpers.Aggregation;
import org.example.helpers.Columns;
import org.example.helpers.Condition;
import org.example.DataCalculating;

import java.util.List;
import java.util.Map;

public class Data {
    private List<Map<String, String>> data;

    public Data(List<Map<String, String>> data) {
        this.data = data;
    }
    public Data() {
        this.data = null;
    }
    public void filter(Condition condition) {
        DataFiltering.filterData(this.data, condition);
    }

    public void select(Columns columns) {
        DataSelecting.selectColumns(this.data, columns);
    }

    public void calculate(Aggregation aggregation) {
        DataCalculating.calculateAggregation(data, aggregation);
    }

    public void group(Columns groupColumns, Aggregation aggregation) {
        DataGrouping.groupData(this.data, groupColumns, aggregation);
    }

    public List<Map<String, String>> getData() {
        return data;
    }
    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }
}