package DataAccess;

import InterfaceAccess.ProductDataAccess;
import Model.Product;
import java.util.ArrayList;

public class ProductDataBaseAccess implements ProductDataAccess {
    @Override
    public int getNumberProduct() {
        return 0;
    }

    @Override
    public void createProduct(Product product) {

    }

    @Override
    public Product getProductById(int productId) {
        return null;
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(int productId) {

    }

    @Override
    public ArrayList<Product> readAllProducts() {
        return null;
    }

    @Override
    public ArrayList<Product> searchProducts(String keyword) {
        return null;
    }

    @Override
    public Product getProductByName(String name) {
        return null;
    }
}
