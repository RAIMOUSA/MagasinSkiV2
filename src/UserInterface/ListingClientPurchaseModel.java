package UserInterface;

import Controller.CustomerController;
import Controller.ProductController;
import Model.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ListingClientPurchaseModel extends AbstractTableModel {
    private String[] columnNames = {"Date", "CodeProduit", "TypeProduit", "NomProduit", "Prix", "Quantité", "CodeSale", "UserID"};
    private ArrayList<Object[]> data = new ArrayList<>();
    private ProductController productController;
    private CustomerController customerController;
    private

    public ListingClientPurchaseModel() {
        this.columnNames = new String[] {"Date", "CodeProduit", "TypeProduit", "NomProduit", "Prix", "Quantité", "CodeSale", "UserID"};
        this.productController = new ProductController();
        this.customerController = new CustomerController();




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
