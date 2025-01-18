package org.example.commands;

import org.example.data.Data;
import org.example.helpers.Columns;

public class SelectCommand extends Command{
    private Columns columns;

    public SelectCommand(Columns columns) {
        this.columns = columns;
    }

    public Columns getColumns() {
        return columns;
    }

    public void setColumns(Columns columns) {
        this.columns = columns;
    }


    @Override
    public void execute(Data data) {
        data.select(columns);
    }
}
