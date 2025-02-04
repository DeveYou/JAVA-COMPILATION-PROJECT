package org.example.commands;


import org.example.data.Data;
import org.example.helpers.Aggregation;
import org.example.helpers.Columns;

public class GroupCommand extends Command{
    private final Columns groupColumns;
    private final Aggregation aggregation;

    public GroupCommand(Columns groupColumns, Aggregation aggregation) {
        this.groupColumns = groupColumns;
        this.aggregation = aggregation;
    }

    public Columns getGroupColumns() {
        return groupColumns;
    }

    public Aggregation getAggregation() {
        return aggregation;
    }

    @Override
    public void execute(Data data) {
        data.group(groupColumns, aggregation);
    }
}
