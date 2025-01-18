package org.example.commands;

import org.example.data.Data;
import org.example.helpers.Condition;

public class FilterCommand extends Command{
    private Condition condition;

    public FilterCommand(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public void execute(Data data) {
        data.filter(condition);
    }
}
