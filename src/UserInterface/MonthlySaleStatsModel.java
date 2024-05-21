package UserInterface;

import Controller.ProductController;
import Controller.SaleController;
import Controller.SaleDetailController;
import Model.Product;
import Model.Sale;
import Model.SaleDetail;

import Exception.*;

import javax.swing.table.AbstractTableModel;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MonthlySaleStatsModel extends AbstractTableModel {
    private String[] columnNames;
    private ArrayList<SaleDetail> sales;
    private ArrayList<SaleDetail> originalSales;
    private Set<String> productTypes;
    private ProductController productController;
    private SaleController saleController;
    private SaleDetailController saleDetailController;

    public MonthlySaleStatsModel() {
        this.columnNames = new String[]{"Date", "TypeProduit", "Quantité", "Revenu"};
        this.productTypes = new HashSet<>();
        this.productController = new ProductController();
        this.saleController = new SaleController();
        this.saleDetailController = new SaleDetailController();
        loadSales();
    }

    private void loadSales() {
        try {
            this.originalSales = saleDetailController.readAllSaleDetails();
            for (SaleDetail saleDetail : originalSales) {
                Product product = this.productController.getProductByCode(saleDetail.getProductCode());
                productTypes.add(product.getType());
            }
            this.sales = new ArrayList<>(originalSales);
        } catch (Exception e) {
            e.printStackTrace();
            this.sales = new ArrayList<>();
        }
    }

    @Override
    public int getRowCount() {
        if (sales == null) {
            return 0;
        }
        return sales.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SaleDetail saleDetail = sales.get(rowIndex);
        Sale sale = null;
        try {
            sale = this.saleController.getSaleBySaleDetail(saleDetail);
        } catch (SaleException e) {
            throw new RuntimeException(e);
        }
        Product product = null;
        try {
            product = this.productController.getProductByCode(saleDetail.getProductCode());
        } catch (ProductException e) {
            throw new RuntimeException(e);
        }

        switch (columnIndex) {
            case 0:
                return sale.getDate();
            case 1:
                return product.getType();
            case 2:
                return saleDetail.getQuantity();
            case 3:
                return saleDetail.getQuantity() * product.getPrice();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void filterByTypeAndMonth(String type, Month month, int year) throws ProductException, SaleException {
        sales.clear();
        for (SaleDetail saleDetail : originalSales) {
            Sale sale = saleController.getSaleBySaleDetail(saleDetail);
            Product product = productController.getProductByCode(saleDetail.getProductCode());
            if (product.getType().equals(type) && sale.getDate().getMonth().equals(month) && sale.getDate().getYear() == (year)){
                sales.add(saleDetail);
            }
        }
        fireTableDataChanged();
    }

    public Set<String> getProductTypes() {
        return productTypes;
    }
}
