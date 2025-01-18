package org.example.commands;

import org.example.data.Data;
import org.example.helpers.Condition;

public class FilterCommand extends Command{
    private final Condition condition;

    public FilterCommand(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void execute(Data data) {
        data.filter(condition);
    }
}
