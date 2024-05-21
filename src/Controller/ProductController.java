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

    public ArrayList<Product> readAllProducts() throws ProductException {
        try {
            return productManager.readAllProducts();
        } catch (Exception e) {
            throw new ProductException("Error reading products");
        }
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public void applyDiscount(Product product, double discount) throws ProductException {
        try {
            productManager.applyDiscount(product, discount);
        } catch (Exception e) {
            throw new ProductException("Error applying discount");
        }
    }

    public Product getProductInPromotion() {
        return productManager.getProductInPromotion();
    }

    public Product getProductByCode(int productCode) {
        return productManager.getProductByCode(productCode);
    }

    public Product getProductBySaleDetail(SaleDetail saleDetail) {
        return productManager.getProductBySaleDetail(saleDetail);
    }
}
