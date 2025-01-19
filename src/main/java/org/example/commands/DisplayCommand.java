package org.example.commands;

import org.example.data.Data;
import org.example.DataDisplaying;

public class DisplayCommand extends Command {
    @Override
    public void execute(Data data) {
        DataDisplaying.displayTable(data.getData());
    }
}