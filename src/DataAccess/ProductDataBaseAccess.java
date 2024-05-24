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
    public ArrayList<Product> getProductInPromotion() throws ProductException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM product WHERE promoIsEnable = 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Product> products = new ArrayList<Product>();

            while (resultSet.next()) {
                int codeID = resultSet.getInt("codeID");
                String typeProduct = resultSet.getString("typeProduct");
                String nameProduct = resultSet.getString("nameProduct");
                double price = resultSet.getDouble("price");
                int stockQuantity = resultSet.getInt("stockQuantity");
                boolean promoIsEnable = resultSet.getBoolean("promoIsEnable");
                Product product = new Product(codeID, typeProduct, nameProduct, price, stockQuantity, promoIsEnable);

                int percentPromo = resultSet.getInt("percentPromo");
                if (!resultSet.wasNull()) {
                    product.setPercentPromo(percentPromo);
                }
                products.add(product);
            }
            if (products.isEmpty()){
                return null;
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
            String query = "SELECT * FROM product WHERE promoIsEnable = 0;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Product> products = new ArrayList<Product>();

            while (resultSet.next()) {
                int codeID = resultSet.getInt("codeID");
                String typeProduct = resultSet.getString("typeProduct");
                String nameProduct = resultSet.getString("nameProduct");
                double price = resultSet.getDouble("price");
                int stockQuantity = resultSet.getInt("stockQuantity");
                boolean promoIsEnable = resultSet.getBoolean("promoIsEnable");
                Product product = new Product(codeID, typeProduct, nameProduct, price, stockQuantity, promoIsEnable);

                int percentPromo = resultSet.getInt("percentPromo");
                if (!resultSet.wasNull()) {
                    product.setPercentPromo(percentPromo);
                }
                products.add(product);

            }
            return products;

        } catch (Exception exception) {
            throw new ProductException("Erreur pour trouver les produits sans promotion.", new AllException(), new ReadException());
        }
    }

    @Override
    public void applyDiscount(Product product, int discount) throws ProductException {
        try {
            //true promoisenable et percentPromo
            Connection connection = SingletonConnexion.getInstance();
            String query = "UPDATE product SET promoIsEnable = true, percentPromo = ? WHERE codeID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, discount);
            statement.setInt(2, product.getCode());
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new ProductException("Erreur dans la mise à jour discount table.", new OneException(), new UpdateException());
        }
    }

    @Override
    public Product getProductByCode(int productCode) throws ProductException {
        try {

            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM product WHERE codeID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productCode);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int codeID = resultSet.getInt("codeID");
                String typeProduct = resultSet.getString("typeProduct");
                String nameProduct = resultSet.getString("nameProduct");
                double price = resultSet.getDouble("price");
                int stockQuantity = resultSet.getInt("stockQuantity");
                boolean promoIsEnable = resultSet.getBoolean("promoIsEnable");
                Product product = new Product(codeID, typeProduct, nameProduct, price, stockQuantity, promoIsEnable);

                int percentPromo = resultSet.getInt("percentPromo");
                if (!resultSet.wasNull()) {
                    product.setPercentPromo(percentPromo);
                }
                return product;
            }
            return null;
        } catch (Exception exception) {
            throw new ProductException("Erreur produit par code.", new OneException(), new ReadException());
        }
    }


    @Override
    public void removePromotion(int productId) throws ProductException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "UPDATE product SET promoIsEnable = 0, percentPromo = NULL WHERE codeID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new ProductException("Erreur dans la suppression de la promotion.", new OneException(), new UpdateException());
        }
    }
}
