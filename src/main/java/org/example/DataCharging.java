package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataCharging {

    public List<Map<String, String>> readCSV(String filePath) throws Exception {
        List<Map<String, String>> data = new ArrayList<>();
        BufferedReader reader = null;
        String line;
        String[] headers = null;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            // Read headers
            if ((line = reader.readLine()) != null) {
                headers = line.split(",");
            }
            if (headers == null){
                throw new Exception("CSV file must have headers.");
            }

            // Read data rows
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length != headers.length) {
                    System.err.println("Warning: Row with incorrect number of columns found. Skipping row: " + Arrays.toString(values));
                    continue;
                }
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i].trim(), values[i].trim());
                }
                data.add(row);
            }

        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            throw e;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error closing reader: " + e.getMessage());
                }
            }
        }

        return data;
    }

}