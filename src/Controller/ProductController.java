package src.Controller;

import Business.ProductManager;
import Model.Product;

import java.util.ArrayList;

import Exception.*;

public class ProductController {
    private ProductManager manager;

    public ProductController() {
        setManager(new ProductManager());
    }

    public void setManager(ProductManager manager) {
        this.manager = manager;
    }

    public ArrayList<Product> readAllProducts() throws ProductException {
        return this.manager.readAllProducts();
    }

    public Product getProductByName(String name) throws ProductException {
        return this.manager.getProductByName(name);
    }
}
