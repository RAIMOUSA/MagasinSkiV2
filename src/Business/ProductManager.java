package src.Business;

import InterfaceAccess.ProductDataAccess;
import Model.Product;

import java.util.ArrayList;

public class ProductManager {
    private ProductDataAccess productAcces;


    public ArrayList<Product> readAllProducts() {
        return productAcces.readAllProducts();
    }

    public Product getProductByName(String name) {
        return productAcces.getProductByName(name);
    }
}
