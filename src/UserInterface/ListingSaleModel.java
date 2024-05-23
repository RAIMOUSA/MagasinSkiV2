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
import java.util.HashSet;
import java.util.Set;

public class ListingSaleModel extends AbstractTableModel {

    private String[] columnNames = {"Date", "Code", "Type", "Name", "Price", "Quantity", "UserID"};
    private ArrayList<SaleDetail> saleDetails;
    private ArrayList<SaleDetail> originalSaleDetails; // Liste pour stocker les données originales
    private SaleDetailController saleDetailController;
    private SaleController saleController;
    private ProductController productController;
    private Set<LocalDate> dates;

    public ListingSaleModel() {
        this.saleDetailController = new SaleDetailController();
        this.saleController = new SaleController();
        this.productController = new ProductController();
        this.dates = new HashSet<>();
        loadSaleDetails();
    }

    private void loadSaleDetails() {
        try {
            saleDetails = saleDetailController.readAllSaleDetails();
            originalSaleDetails = new ArrayList<>(saleDetails);
            // remplir le set de dates
            for (SaleDetail saleDetail : saleDetails) {
                Sale sale = saleController.getSaleBySaleDetail(saleDetail);
                dates.add(sale.getDate());
            }

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

        if (sale == null || product == null) {
            return null;
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
            for (SaleDetail saleDetail : originalSaleDetails) {
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

    public Set<LocalDate> getDates() {
        return dates;
    }
}
