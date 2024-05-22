package UserInterface;

import Controller.ProductController;
import Controller.SaleController;
import Controller.SaleDetailController;
import Model.Product;
import Model.Sale;
import Model.SaleDetail;
import Exception.ProductException;
import Exception.SaleDetailException;
import Exception.SaleException;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ListingClientPurchaseModel extends AbstractTableModel {
    private String[] columnNames = {"Date", "CodeProduit", "TypeProduit", "NomProduit", "Prix", "Quantité", "CodeSale", "UserID"};
    private ArrayList<SaleDetail> saleDetails = new ArrayList<>();
    private ProductController productController;
    private SaleDetailController saleDetailController;
    private SaleController saleController;

    public ListingClientPurchaseModel() {
        this.productController = new ProductController();
        this.saleDetailController = new SaleDetailController();
        this.saleController = new SaleController();
        loadPurchases();
    }

    private void loadPurchases() {
        try {
            ArrayList<Sale> sales = saleController.readAllSales();
            for (Sale sale : sales) {
                ArrayList<SaleDetail> details = saleDetailController.getSaleDetailsBySale(sale);
                saleDetails.addAll(details);
            }
        } catch (SaleException | SaleDetailException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return saleDetails.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SaleDetail saleDetail = saleDetails.get(rowIndex);
        Sale sale = null;
        try {
            sale = this.saleController.getSaleBySaleDetail(saleDetail);
        } catch (SaleException e) {
            throw new RuntimeException(e);
        }
        Product product = null;
        try {
            product = productController.getProductByCode(saleDetail.getProductCode());
        } catch (ProductException e) {
            e.printStackTrace();
            return null;
        }

        switch (columnIndex) {
            case 0: return sale.getDate();
            case 1: return saleDetail.getProductCode();
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

    public void filterByUserID(String userID) {
        ArrayList<SaleDetail> filteredSaleDetails = new ArrayList<>();
        for (SaleDetail saleDetail : saleDetails) {
            Sale sale = null;
            try {
                sale = saleController.getSaleBySaleDetail(saleDetail);
            } catch (SaleException e) {
                throw new RuntimeException(e);
            }
            if (sale.getUserID() == (Integer.parseInt(userID))) {
                filteredSaleDetails.add(saleDetail);
            }
        }
        saleDetails = filteredSaleDetails;
        fireTableDataChanged();
    }

    public void refreshData() {
        saleDetails.clear();
        loadPurchases();
        fireTableDataChanged();
    }
}