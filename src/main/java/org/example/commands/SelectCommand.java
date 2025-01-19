package org.example.commands;

import org.example.data.Data;
import org.example.helpers.Columns;

public class SelectCommand extends Command{
    private final Columns columns;

    public SelectCommand(Columns columns) {
        this.columns = columns;
    }

    public Columns getColumns() {
        return columns;
    }

    @Override
    public void execute(Data data) {
        data.select(columns);
    }
}
