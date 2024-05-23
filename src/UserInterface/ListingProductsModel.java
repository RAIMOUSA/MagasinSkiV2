package UserInterface;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

import Controller.ProductController;
import Model.Product;
import Exception.ProductException;

public class ListingProductsModel extends AbstractTableModel {
    private String[] columnNames = {"Type", "Name", "Price", "Code", "StockQuantity"};
    private ArrayList<Product> data = new ArrayList<>();
    private ArrayList<Product> originalData = new ArrayList<>();
    private ProductController productController;

    public ListingProductsModel(ProductController productController) throws ProductException {
        this.productController = productController;
        loadData();
    }

    private void loadData() throws ProductException {
        data.clear();
        originalData.clear();

        ArrayList<Product> products = productController.getProductsWhithoutPromotion();
        if (products != null) {
            data.addAll(products);
            originalData.addAll(products);
        }

        fireTableDataChanged();
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
        Product product = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return product.getType();
            case 1:
                return product.getName();
            case 2:
                return product.getPrice();
            case 3:
                return product.getCode();
            case 4:
                return product.getStockQuantity();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Product getRow(int rowIndex) {
        return data.get(rowIndex);
    }

    public void filterProductsByName(String name) {
        data.clear();
        for (Product product : originalData) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                data.add(product);
            }
        }
        fireTableDataChanged();
    }

    public void refreshData() throws ProductException {
        loadData();
    }

    public ArrayList<Product> getProducts() {
        return data;
    }
}
