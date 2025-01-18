package org.example;

import org.example.commands.*;
import org.example.data.Data;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        // Read CSV data
        DataCharging dataCharger = new DataCharging();
        List<Map<String, String>> data = dataCharger.readCSV("data.csv");

        // Read input commands
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        // Parse commands
        //Parser parser = new Parser();
        //Command command = parser.parseCommand(input);

        // Execute commands
        if (command instanceof FilterCommand) {
            DataFiltering.filterData(data, ((FilterCommand) command).getCondition());
        } else if (command instanceof SelectCommand) {
            DataSelecting.selectColumns(data, ((SelectCommand) command).getColumns());
        } else if (command instanceof CalculateCommand) {
            Data.calculate(((CalculateCommand) command).getAggregation());
        } else if (command instanceof GroupCommand) {
            DataGrouping.groupData(data, ((GroupCommand) command).getGroupColumns(), ((GroupCommand) command).getAggregation());
        }

        // Display results
        DataDisplaying.displayTable(data);
    }
}