package DataAccess;

import InterfaceAccess.SaleDetailDataAccess;
import Model.Product;
import Model.Sale;
import Model.SaleDetail;
import Exception.*;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

public class SaleDetailDataBaseAccess implements SaleDetailDataAccess {

    public SaleDetail getSaleDetailByProduct(Product product) throws SaleDetailException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM sale_detail WHERE productCode = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, product.getCode());
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            int quantity = resultSet.getInt("quantity");
            int saleCode = resultSet.getInt("saleCode");
            int productCode = resultSet.getInt("productCode");

            return new SaleDetail(saleCode, productCode, quantity);
        } catch (Exception exception) {
            throw new SaleDetailException("Erreur dans la lecture des détails de la vente.", new OneException(), new ReadException());

        }
    }

    public ArrayList<SaleDetail> readAllSaleDetails() throws SaleDetailException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM sale_detail;";
            PreparedStatement statement = connection.prepareStatement(query);

            ArrayList<SaleDetail> saleDetails = new ArrayList<SaleDetail>();
            ResultSet resultSet = statement.executeQuery();

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

    @Override
    public ArrayList<SaleDetail> getSaleDetailsBySale(Sale sale) throws SaleDetailException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM sale_detail WHERE saleCode = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, sale.getCode());

            ResultSet resultSet = statement.executeQuery();
            ArrayList<SaleDetail> saleDetails = new ArrayList<SaleDetail>();

            while (resultSet.next()) {
                int quantity = resultSet.getInt("quantity");
                int productCode = resultSet.getInt("productCode");
                SaleDetail saleDetail = new SaleDetail(sale.getCode(), productCode, quantity);
                saleDetails.add(saleDetail);
            }

            return saleDetails;
        } catch (Exception exception) {
            throw new SaleDetailException("Erreur dans la lecture des détails de la vente.", new OneException(), new ReadException());
        }
    }
}
