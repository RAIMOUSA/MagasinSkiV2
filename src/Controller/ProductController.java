package Controller;

import Business.ProductManager;
import Exception.ProductException;
import Model.Product;
import Model.SaleDetail;

import java.util.ArrayList;

public class ProductController {
    private ProductManager productManager;

    public ProductController() {
        setProductManager(new ProductManager());
    }


    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public void applyDiscount(Product product, int discount) throws ProductException {
        try {
            productManager.applyDiscount(product, discount);
        } catch (Exception e) {
            throw new ProductException("Error applying discount");
        }
    }

    public ArrayList<Product> getProductInPromotion() throws ProductException {
        return productManager.getProductInPromotion();
    }

    public ArrayList<Product> getProductsWhithoutPromotion() throws ProductException {
        return productManager.getProductsWhithoutPromotion();
    }

    public Product getProductByCode(int productCode) throws ProductException {
        return productManager.getProductByCode(productCode);
    }

    public void removePromotion(int productId) throws ProductException{
        productManager.removePromotion(productId);
    }
}
