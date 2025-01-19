package org.example.commands;

import org.example.data.Data;
import org.example.DataCharging;

import java.util.List;
import java.util.Map;

public class ChargeCommand extends Command {
    private final String filePath;
    private List<Map<String, String>> loadedData;

    public ChargeCommand(String filePath) {
        this.filePath = filePath;
        this.loadedData = null;
    }

    public String getFilePath() {
        return filePath;
    }

    public List<Map<String, String>> getLoadedData(){
        return this.loadedData;
    }

    @Override
    public void execute(Data data) {
        DataCharging dataCharger = new DataCharging();
        try {
            this.loadedData = dataCharger.readCSV(filePath);
            data.setData(loadedData);
        } catch (Exception e) {
            System.err.println("An error occurred during data charging: " + e.getMessage());
        }
    }
}