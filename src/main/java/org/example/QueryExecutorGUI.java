package org.example;

import org.example.commands.*;
import org.example.data.Data;
import org.example.generatedClasses.Parser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

public class QueryExecutorGUI {

    private Data data;
    private JTextArea commandInput;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public QueryExecutorGUI() {
        data = new Data();
        initUI();
    }

    private void initUI() {
        JFrame frame = new JFrame("Query Executor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel commandLabel = new JLabel("Enter your command:");
        commandLabel.setFont(new Font("Arial", Font.BOLD, 14));
        commandInput = new JTextArea(5, 40);
        commandInput.setFont(new Font("Courier New", Font.PLAIN, 14));
        commandInput.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        JScrollPane inputScrollPane = new JScrollPane(commandInput);

        JButton executeButton = new JButton("Execute");
        executeButton.setFont(new Font("Arial", Font.BOLD, 14));
        executeButton.setBackground(new Color(86, 89, 91));
        executeButton.setOpaque(true);
        executeButton.setContentAreaFilled(true);
        executeButton.setBorderPainted(false);
        executeButton.setForeground(Color.WHITE);
        executeButton.setFocusPainted(true);
        executeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        executeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0)); // Top margin
        buttonPanel.add(executeButton, BorderLayout.NORTH);

        executeButton.addActionListener(e -> executeCommand());

        inputPanel.add(commandLabel, BorderLayout.NORTH);
        inputPanel.add(inputScrollPane, BorderLayout.CENTER);
        //inputPanel.add(executeButton, BorderLayout.SOUTH);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);


        // Result Table
        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);
        resultTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        resultTable.setRowHeight(25);
        resultTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        resultTable.getTableHeader().setBackground(new Color(240, 240, 240));
        resultTable.setGridColor(Color.LIGHT_GRAY);
        JScrollPane tableScrollPane = new JScrollPane(resultTable);
        tableScrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Layout
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void executeCommand() {
        String input = commandInput.getText().trim();

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a command.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Parser parser = new Parser(new StringReader(input));
        try {
            List<Command> commands = parser.parse(input);

            if (commands != null) {
                for (Command command : commands) {
                    if (command instanceof ChargeCommand) {
                        ((ChargeCommand) command).execute(data);
                    } else if (command instanceof FilterCommand) {
                        data.filter(((FilterCommand) command).getCondition());
                    } else if (command instanceof SelectCommand) {
                        data.select(((SelectCommand) command).getColumns());
                    } else if (command instanceof CalculateCommand) {
                        data.calculate(((CalculateCommand) command).getAggregation());
                    } else if (command instanceof GroupCommand) {
                        data.group(((GroupCommand) command).getGroupColumns(), ((GroupCommand) command).getAggregation());
                    } else if (command instanceof DisplayCommand) {
                        ((DisplayCommand) command).execute(data);
                    }
                }
                updateTable(data.getData());
                data.resetData();
            }

        } catch (org.example.generatedClasses.ParseException e) {
            JOptionPane.showMessageDialog(null, "Parse Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable(List<Map<String, String>> data) {
        if (data == null || data.isEmpty()) {
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
            JOptionPane.showMessageDialog(null, "No data to display.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Set column names
        String[] columns = data.get(0).keySet().toArray(new String[0]);
        tableModel.setColumnIdentifiers(columns);

        // Add rows
        tableModel.setRowCount(0); // Clear existing rows
        for (Map<String, String> row : data) {
            Object[] rowData = columnsToRow(row, columns);
            tableModel.addRow(rowData);
        }
    }

    private Object[] columnsToRow(Map<String, String> row, String[] columns) {
        Object[] rowData = new Object[columns.length];
        for (int i = 0; i < columns.length; i++) {
            rowData[i] = row.getOrDefault(columns[i], "");
        }
        return rowData;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QueryExecutorGUI::new);
    }
}
