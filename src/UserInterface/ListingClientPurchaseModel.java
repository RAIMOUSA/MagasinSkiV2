package UserInterface;

import Controller.ProductController;
import Controller.SaleController;
import Controller.SaleDetailController;
import Model.Product;
import Model.Sale;
import Model.SaleDetail;
import Exception.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ListingClientPurchaseModel extends AbstractTableModel {
    private String[] columnNames;
    private ArrayList<Product> purchases;
    private ProductController productController;
    private SaleDetailController saleDetailController;
    private SaleController saleController;

    public ListingClientPurchaseModel() {
        this.columnNames = new String[] {"Date", "CodeProduit", "TypeProduit", "NomProduit", "Prix", "Quantité", "CodeSale", "UserID"};
        this.productController = new ProductController();
        this.saleDetailController = new SaleDetailController();
        this.saleController = new SaleController();
        loadPurchases();
    }

    private void loadPurchases() {
        try {
            this.purchases = productController.readAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
            this.purchases = new ArrayList<>();
        }
    }

    @Override
    public int getRowCount() {
        if(purchases == null)
            return 0;
        return purchases.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = purchases.get(rowIndex);
        SaleDetail saleDetail = null;
        Sale sale = null;
        try {
            saleDetail = saleDetailController.getSaleDetailByProduct(product);
            sale = saleController.getSaleBySaleDetail(saleDetail);
        } catch (SaleDetailException | SaleException e) {
            throw new RuntimeException(e);
        }

        switch (columnIndex) {
            case 0: return sale.getDate();
            case 1: return product.getCode();
            case 2: return product.getType();
            case 3: return product.getName();
            case 4: return product.getPrice();
            case 5: return saleDetail.getQuantity();
            case 6: return sale.getCode();
            case 7: return sale.getUserID();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void filterByUserID(String userID) throws SaleDetailException, SaleException {
        ArrayList<Product> filteredPurchases = new ArrayList<>();
        for (Product product : purchases) {
            SaleDetail saleDetail = saleDetailController.getSaleDetailByProduct(product);
            Sale sale = saleController.getSaleBySaleDetail(saleDetail);
            if (String.valueOf(sale.getUserID()).equals(userID)) {
                filteredPurchases.add(product);
            }
        }
        this.purchases = filteredPurchases;
        fireTableDataChanged();
    }
}
