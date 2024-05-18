package UserInterface;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ListingSaleModel extends AbstractTableModel {

    private String[] columnNames = {"Code", "Type", "Name", "Price", "Quantity", "UserID", "Date"};
    private List<Object[]> data = new ArrayList<>();
    private List<Object[]> originalData = new ArrayList<>(); // For storing original data

    public ListingSaleModel() {
        // Ajoutez ici des données de vente par défaut ou de test
        data.add(new Object[]{"001", "Electronics", "Laptop", 1200.99, 5, "user123", "2024-05-15"});
        data.add(new Object[]{"002", "Furniture", "Chair", 45.50, 10, "user456", "2024-05-15"});
        data.add(new Object[]{"003", "Clothing", "T-Shirt", 19.99, 20, "user789", "2024-05-16"});
        // Ajoutez d'autres ventes ici

        // Clone the initial data to originalData
        originalData.addAll(data);
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

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3 || columnIndex == 4) {
            return Number.class; // Price and Quantity are numbers
        }
        return String.class; // Other fields are strings
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // All cells are not editable
    }

    // Ajoutez des méthodes pour manipuler les données (ajouter, supprimer, modifier)
    public void addSale(Object[] sale) {
        data.add(sale);
        originalData.add(sale);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void removeSale(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Object[] getSale(int rowIndex) {
        return data.get(rowIndex);
    }

    public void filterByDate(String date) {
        data.clear();
        for (Object[] sale : originalData) {
            if (sale[6].equals(date)) {
                data.add(sale);
            }
        }
        fireTableDataChanged();
    }
}
