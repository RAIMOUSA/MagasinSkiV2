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


    public void applyDiscount(Product product, int discount) throws ProductException {
        this.productAccess.applyDiscount(product, discount);
    }

    public ArrayList<Product> getProductInPromotion() throws ProductException{
        return productAccess.getProductInPromotion();
    }

    public ArrayList<Product> getProductsWhithoutPromotion() throws ProductException {
        return productAccess.getProductsWhithoutPromotion();
    }

    public Product getProductByCode(int productCode) throws ProductException {
        return productAccess.getProductByCode(productCode);
    }


    public void removePromotion(int productId) throws ProductException{
        productAccess.removePromotion(productId);
    }
}
