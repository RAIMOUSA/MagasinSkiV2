package DataAccess;

import Exception.ProductException;
import InterfaceAccess.ProductDataAccess;
import Model.Product;
import Model.SaleDetail;
import java.sql.*;
import java.util.ArrayList;
import Exception.*;
public class ProductDataBaseAccess implements ProductDataAccess {

    @Override
    public ArrayList<Product> readAllProducts() throws ProductException {
try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM products;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Product> products = new ArrayList<Product>();

            while (resultSet.next()) {
                String typeProduct = resultSet.getString("typeProduct");
                String nameProduct = resultSet.getString("nameProduct");
                double price = resultSet.getDouble("price");
                int stockQuantity = resultSet.getInt("stockQuantity");
                boolean promoIsEnable = resultSet.getBoolean("promoIsEnable");
                Product product = new Product(typeProduct, nameProduct, price, stockQuantity, promoIsEnable);

                int percentPromo = resultSet.getInt("percentPromo");
                if (!resultSet.wasNull()) {
                    product.setPercentPromo(percentPromo);
                }
                products.add(product);

            }
            return products;
        } catch (Exception exception) {
            // code to handle exception
        }
        return null;
    }

    @Override
    public ArrayList<Product> getProductInPromotion() throws ProductException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM products WHERE promotion = 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Product> products = new ArrayList<Product>();

            while (resultSet.next()) {
                String typeProduct = resultSet.getString("typeProduct");
                String nameProduct = resultSet.getString("nameProduct");
                double price = resultSet.getDouble("price");
                int stockQuantity = resultSet.getInt("stockQuantity");
                boolean promoIsEnable = resultSet.getBoolean("promoIsEnable");
                Product product = new Product(typeProduct, nameProduct, price, stockQuantity, promoIsEnable);

                int percentPromo = resultSet.getInt("percentPromo");
                if (!resultSet.wasNull()) {
                    product.setPercentPromo(percentPromo);
                }
                products.add(product);

            }
            return products;

        } catch (Exception exception) {
            throw new ProductException(exception.getMessage(), new AllException(), new ReadException());
        }
    }

    @Override
    public ArrayList<Product> getProductsWhithoutPromotion() throws ProductException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM products WHERE promoIsEnable = 0;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Product> products = new ArrayList<Product>();

            while (resultSet.next()) {
                String typeProduct = resultSet.getString("typeProduct");
                String nameProduct = resultSet.getString("nameProduct");
                double price = resultSet.getDouble("price");
                int stockQuantity = resultSet.getInt("stockQuantity");
                boolean promoIsEnable = resultSet.getBoolean("promoIsEnable");
                Product product = new Product(typeProduct, nameProduct, price, stockQuantity, promoIsEnable);

                int percentPromo = resultSet.getInt("percentPromo");
                if (!resultSet.wasNull()) {
                    product.setPercentPromo(percentPromo);
                }
                products.add(product);

            }
            return products;

        } catch (Exception exception) {
            throw new ProductException(exception.getMessage(), new AllException(), new ReadException());
        }
    }

    @Override
    public void applyDiscount(Product product, int discount) throws ProductException {
        try {
            //true promoisenable et percentPromo
            Connection connection = SingletonConnexion.getInstance();
            String query = "UPDATE products SET promoIsEnable = ?, percentPromo = ? WHERE nameProduct = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, true);
            statement.setInt(2, discount);
            statement.setString(3, product.getName());
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new ProductException(exception.getMessage(), new OneException(), new UpdateException());
        }
    }

    @Override
    public Product getProductByCode(int productCode) throws ProductException {
        return null;
        // fait esclave Félix
    }

    @Override
    public Product getProductBySaleDetail(SaleDetail saleDetail) {
        return null;
        // fait esclave Félix
    }

}
