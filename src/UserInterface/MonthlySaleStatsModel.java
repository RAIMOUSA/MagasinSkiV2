package UserInterface;

import javax.swing.table.AbstractTableModel;
import java.util.*;

public class MonthlySaleStatsModel extends AbstractTableModel {
    private String[] columnNames = {"Date", "TypeProduit", "Quantité", "Revenu"};
    private List<Object[]> data = new ArrayList<>();
    private List<Object[]> originalData = new ArrayList<>(); // To store original data
    private Set<String> productTypes = new HashSet<>(); // To store unique product types

    public MonthlySaleStatsModel() {
        // Initialize with sample data or load from a database
        // This is just a placeholder. Replace with actual data fetching logic.
        addSaleData(new Object[]{"2024-05", "Ski", 10, 1000.0});
        addSaleData(new Object[]{"2024-05", "Casque", 5, 500.0});
    }

    private void addSaleData(Object[] saleData) {
        originalData.add(saleData);
        productTypes.add((String) saleData[1]); // Add the product type to the set
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void filterByTypeAndMonth(String type, String monthYear) {
        data.clear();
        for (Object[] row : originalData) {
            if (row[0].equals(monthYear) && row[1].equals(type)) { // Check if monthYear and type match
                data.add(row);
            }
        }
        fireTableDataChanged();
    }

    public Set<String> getProductTypes() {
        return productTypes;
    }
}
