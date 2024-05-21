package Business;

import DataAccess.ProductDataBaseAccess;
import Exception.ProductException;
import InterfaceAccess.ProductDataAccess;
import Model.Product;
import Model.SaleDetail;

import java.util.ArrayList;

public class ProductManager {
    private ProductDataAccess productAccess;

    public ProductManager() {
        setProductAccess(new ProductDataBaseAccess());
    }

    public void setProductAccess(ProductDataAccess productAccess) {
        this.productAccess = productAccess;
    }

    public ArrayList<Product> readAllProducts() throws Exception {
        return productAccess.readAllProducts();
    }

    public void applyDiscount(Product product, double discount) throws ProductException {
        this.productAccess.applyDiscount(product, discount);
    }

    public Product getProductInPromotion() {
        return productAccess.getProductInPromotion();
    }

    public Product getProductByCode(int productCode) {
        return productAccess.getProductByCode(productCode);
    }

    public Product getProductBySaleDetail(SaleDetail saleDetail) {
        return productAccess.getProductBySaleDetail(saleDetail);
    }
}
