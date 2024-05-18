package UserInterface;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ListingClientPurchaseModel extends AbstractTableModel {
    private String[] columnNames = {"Date", "CodeProduit", "TypeProduit", "NomProduit", "Prix", "Quantité", "CodeSale", "UserID"};
    private List<Object[]> data = new ArrayList<>();
    private List<Object[]> originalData = new ArrayList<>(); // To store original data

    public ListingClientPurchaseModel() {
        // Initialize with sample data or load from a database
        // This is just a placeholder. Replace with actual data fetching logic.
        Object[] row1 = new Object[]{"2024-05-15", "P001", "Ski", "ULTRA300", 10.0, 2, "S001", "U001"};
        Object[] row2 = new Object[]{"2024-05-16", "P002", "Casque", "PROTEC2000", 20.0, 1, "S002", "U002"};
        Object[] row3 = new Object[]{"2024-05-17", "P003", "Gants", "WARM500", 15.0, 3, "S003", "U003"};
        Object[] row4 = new Object[]{"2024-05-18", "P004", "Lunettes", "VISIO200", 25.0, 1, "S004", "U001"};
        Object[] row5 = new Object[]{"2024-05-19", "P005", "Veste", "PROTECT1000", 50.0, 1, "S005", "U002"};
        Object[] row6 = new Object[]{"2024-05-20", "P006", "Chaussures", "FAST600", 40.0, 2, "S006", "U003"};
        Object[] row7 = new Object[]{"2024-05-21", "P007", "Bâtons", "BALANCE300", 30.0, 1, "S007", "U001"};
        Object[] row8 = new Object[]{"2024-05-22", "P008", "Sac à dos", "TREK900", 35.0, 1, "S008", "U002"};

        data.add(row1);
        data.add(row2);
        data.add(row3);
        data.add(row4);
        data.add(row5);
        data.add(row6);
        data.add(row7);
        data.add(row8);



        originalData.addAll(data); // Save original data
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

    public void filterByUserID(String userID) {
        data.clear();
        for (Object[] row : originalData) {
            if (row[7].equals(userID)) { // Check if userID matches
                data.add(row);
            }
        }
        fireTableDataChanged();
    }
}
