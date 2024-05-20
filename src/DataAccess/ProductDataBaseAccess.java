package DataAccess;

import InterfaceAccess.ProductDataAccess;
import Model.Product;

import java.sql.*;
import java.util.ArrayList;
import Exception.*;

public class ProductDataBaseAccess implements ProductDataAccess {
    @Override
    public int getNumberProduct() {
        return 0;
    }

    @Override
    public void createProduct(Product product) throws ProductException {
        try {
            // Code to create a product in the database
            Connection connection = SingletonConnexion.getInstance();
            String query = "INSERT INTO product VALUES (?,?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            
        } catch (Exception exception) {
            throw new ProductException(exception.getMessage(), new OneException(), new CreateException());
        }
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
