package src.InterfaceAccess;

import Model.Product;

import java.util.ArrayList;

public interface ProductDataAccess {
    int getNumberProduct();
    void createProduct(Product product);
    Product getProductById(int productId);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    ArrayList<Product> readAllProducts();
    ArrayList<Product> searchProducts(String keyword);

    Product getProductByName(String name);
}
