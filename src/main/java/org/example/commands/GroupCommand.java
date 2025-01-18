package org.example.commands;


import org.example.data.Data;
import org.example.helpers.Aggregation;
import org.example.helpers.Columns;

public class GroupCommand extends Command{
    private Columns groupColumns;
    private Aggregation aggregation;

    public GroupCommand(Columns groupColumns, Aggregation aggregation) {
        this.groupColumns = groupColumns;
        this.aggregation = aggregation;
    }

    public Columns getGroupColumns() {
        return groupColumns;
    }

    public void setGroupColumns(Columns groupColumns) {
        this.groupColumns = groupColumns;
    }

    public Aggregation getAggregation() {
        return aggregation;
    }

    public void setAggregation(Aggregation aggregation) {
        this.aggregation = aggregation;
    }

    @Override
    public void execute(Data data) {
        data.group(groupColumns, aggregation);
    }
}
