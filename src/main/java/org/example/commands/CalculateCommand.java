package org.example.commands;


import org.example.data.Data;
import org.example.helpers.Aggregation;

public class CalculateCommand extends Command{
    private final Aggregation aggregation;

    public CalculateCommand(Aggregation aggregation) {
        this.aggregation = aggregation;
    }

    public Aggregation getAggregation() {
        return aggregation;
    }

    @Override
    public void execute(Data data) {
        data.calculate(aggregation);
    }
}
