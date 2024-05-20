package InterfaceAccess;

import java.util.ArrayList;
import Model.Product;
import Exception.*;

public interface ProductDataAccess {
    int getNumberProduct();

    void createProduct(Product product) throws ProductException;

    Product getProductById(int productId);

    void updateProduct(Product product);

    void deleteProduct(int productId);

    ArrayList<Product> readAllProducts() throws Exception;

    ArrayList<Product> searchProducts(String keyword);

    Product getProductByName(String name);
}
