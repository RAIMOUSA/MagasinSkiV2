package UserInterface;

import Controller.ProductController;
import Model.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ActivePromotionsModel extends AbstractTableModel {
    private String[] columnNames;
    private ArrayList<Object[]> data = new ArrayList<>();
    private ProductController productController;
    private Product productInPromotion;


    public ActivePromotionsModel() {
        this.columnNames = new String[] {"Produit", "Ancien prix (€)", "Promotion (%)", "Nouveau prix (€)"};
        this.productController = new ProductController();
        this.productInPromotion = productController.getProductInPromotion();
        loadData();
    }

    public void loadData(){
        data.clear();
        if(productInPromotion != null){
            Object[] productData = new Object[5];
            productData[0] = productInPromotion.getName();
            productData[1] = productInPromotion.getPrice();
            productData[2] = productInPromotion.getPercentPromo();
            productData[3] = productInPromotion.getPrice() - (productInPromotion.getPrice() * productInPromotion.getPercentPromo() / 100);
            data.add(productData);
        }
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

    public void setData(List<Object[]> promotionsData) {
        data.clear();
        data.addAll(promotionsData);
        fireTableDataChanged();
    }
}
