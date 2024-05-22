package UserInterface;

import Controller.ProductController;
import Controller.SaleController;
import Controller.SaleDetailController;
import Model.Product;
import Model.Sale;
import Model.SaleDetail;
import Exception.*;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ListingSaleModel extends AbstractTableModel {

    private String[] columnNames = {"Date", "Code", "Type", "Name", "Price", "Quantity", "UserID"};
    private ArrayList<SaleDetail> saleDetails;
    private ArrayList<SaleDetail> originalSaleDetails; // Liste pour stocker les données originales
    private SaleDetailController saleDetailController;
    private SaleController saleController;
    private ProductController productController;

    public ListingSaleModel() {
        this.saleDetailController = new SaleDetailController();
        this.saleController = new SaleController();
        this.productController = new ProductController();
        loadSaleDetails();
    }

    private void loadSaleDetails() {
        try {
            saleDetails = saleDetailController.readAllSaleDetails();
            originalSaleDetails = new ArrayList<>(saleDetails); // Initialisation de la liste originale
        } catch (Exception e) {
            e.printStackTrace();
            saleDetails = new ArrayList<>();
            originalSaleDetails = new ArrayList<>();
        }
    }

    @Override
    public int getRowCount() {
        if (saleDetails == null)
            return 0;
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
        Product product = null;
        try {
            sale = saleController.getSaleBySaleDetail(saleDetail);
            product = productController.getProductByCode(saleDetail.getProductCode());
        } catch (SaleException | ProductException e) {
            throw new RuntimeException(e);
        }

        switch (columnIndex) {
            case 0: return sale.getDate();
            case 1: return product.getCode();
            case 2: return product.getType();
            case 3: return product.getName();
            case 4: return product.getPrice();
            case 5: return saleDetail.getQuantity();
            case 6: return sale.getUserID();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void filterByDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate filterDate = LocalDate.parse(date, formatter);

        if (date == null || date.isEmpty()) {
            saleDetails = new ArrayList<>(originalSaleDetails);
        } else {
            ArrayList<SaleDetail> filteredSaleDetails = new ArrayList<>();
            for (SaleDetail saleDetail : originalSaleDetails) { // Utiliser les données originales pour le filtrage
                Sale sale = null;
                try {
                    sale = saleController.getSaleBySaleDetail(saleDetail);
                } catch (SaleException e) {
                    e.printStackTrace();
                    continue;
                }
                if (sale.getDate().equals(filterDate)) {
                    filteredSaleDetails.add(saleDetail);
                }
            }
            saleDetails = filteredSaleDetails;
        }
        fireTableDataChanged();
    }
}
