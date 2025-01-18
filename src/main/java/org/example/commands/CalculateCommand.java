package org.example.commands;


import org.example.data.Data;
import org.example.helpers.Aggregation;

public class CalculateCommand extends Command{
    private Aggregation aggregation;

    public CalculateCommand(Aggregation aggregation) {
        this.aggregation = aggregation;
    }

    public Aggregation getAggregation() {
        return aggregation;
    }

    public void setAggregation(Aggregation aggregation) {
        this.aggregation = aggregation;
    }

    @Override
    public void execute(Data data) {
        data.calculate(aggregation);
    }
}
