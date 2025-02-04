package org.example;

import org.example.commands.*;
import org.example.data.Data;
import org.example.generatedClasses.Parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create Data object
        Data data = new Data();

        // Read input commands
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while(true) {
            System.out.print("Enter command: ");
            input = reader.readLine();
            if(input == null || input.equalsIgnoreCase("exit")){
                break;
            }
            // Parse commands
            Parser parser = new Parser(new StringReader(input));
            try{
                List<Command> commands = parser.parse(input);
                // Execute commands
                if (commands != null){
                    for (Command command : commands){
                        if (command instanceof ChargeCommand) {
                            ((ChargeCommand) command).execute(data);
                        }else if (command instanceof FilterCommand) {
                            data.filter(((FilterCommand) command).getCondition());
                        } else if (command instanceof SelectCommand) {
                            data.select(((SelectCommand) command).getColumns());
                        } else if (command instanceof CalculateCommand) {
                            data.calculate(((CalculateCommand) command).getAggregation());
                        } else if (command instanceof GroupCommand) {
                            data.group(((GroupCommand) command).getGroupColumns(), ((GroupCommand) command).getAggregation());
                        }  else if (command instanceof DisplayCommand){
                            ((DisplayCommand) command).execute(data);
                        }
                    }
                    data.resetData();
                }

            } catch (org.example.generatedClasses.ParseException e){
                System.err.println("Parse Error: " + e.getMessage());
            }
        }
    }
}
