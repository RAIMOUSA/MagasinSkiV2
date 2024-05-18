package Controller;

import Business.ProductManager;
import InterfaceAccess.ProductDataAccess;
import java.util.ArrayList;
import Model.Product;
import Exception.*;

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
}
