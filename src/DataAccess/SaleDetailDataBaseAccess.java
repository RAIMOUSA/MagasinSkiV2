package DataAccess;

import InterfaceAccess.SaleDetailDataAccess;
import Model.Product;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;
import java.sql.*;

public class SaleDetailDataBaseAccess implements SaleDetailDataAccess {

    public SaleDetail getSaleDetailByProduct(Product product) throws SaleDetailException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM sales WHERE codeID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, product.getCode());
            ResultSet resultSet = statement.executeQuery();

            int saleCode = resultSet.getInt("saleCode");
            int productCode = resultSet.getInt("productCode");
            int quantity = resultSet.getInt("quantity");
            SaleDetail saleDetail = new SaleDetail(saleCode, productCode, quantity);
            return saleDetail;
        } catch (Exception exception) {
            throw new SaleDetailException("Erreur dans la lecture des détails de la vente.", new OneException(), new ReadException());

        }
    }

    public ArrayList<SaleDetail> readAllSaleDetails() throws SaleDetailException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM sales;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<SaleDetail> saleDetails = new ArrayList<SaleDetail>();

            while (resultSet.next()) {
                int saleCode = resultSet.getInt("saleCode");
                int productCode = resultSet.getInt("productCode");
                int quantity = resultSet.getInt("quantity");
                SaleDetail saleDetail = new SaleDetail(saleCode, productCode, quantity);
                saleDetails.add(saleDetail);
            }
            return saleDetails;
        } catch (Exception exception) {
            throw new SaleDetailException("Erreur dans la lecture de tous les détails de vente.", new AllException(), new ReadException());
        }
    }
}
