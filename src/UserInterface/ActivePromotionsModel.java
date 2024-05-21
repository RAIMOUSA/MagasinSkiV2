package UserInterface;

import Controller.ProductController;
import Model.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import Exception.*;


class ActivePromotionsModel extends AbstractTableModel {
    private ArrayList<Product> productsWithPromotions;
    private String[] columnNames;
    private ProductController productController;

    public ActivePromotionsModel() {
        columnNames = new String[] {
                "ProduitID",
                "Nom",
                "Ancien prix",
                "Promotion",
                "Nouveau prix",
        };
        productController = new ProductController();
        try{
            productsWithPromotions = productController.getProductInPromotion();
        }catch(ProductException e){
            e.printStackTrace();
            productsWithPromotions = new ArrayList<>();
        }

    }

    public void setData(List<Object[]> data) {
        this.productsWithPromotions = productsWithPromotions;
        fireTableDataChanged();
    }

    public void removeRow(int rowIndex) {
        this.productsWithPromotions.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    @Override
    public int getRowCount() {
        if(this.productsWithPromotions == null)
            return 0;
        return productsWithPromotions.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = productsWithPromotions.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> product.getCode();
            case 1 -> product.getName();
            case 2 -> product.getPrice();
            case 3 -> product.getPercentPromo();
            case 4 -> product.getPrice() - (product.getPrice() * product.getPercentPromo() / 100);
            default -> "";
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
