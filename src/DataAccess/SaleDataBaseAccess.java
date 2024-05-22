package DataAccess;

import InterfaceAccess.SaleDataAccess;
import Model.Product;
import Model.Sale;
import Model.SaleDetail;
import Exception.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;

public class SaleDataBaseAccess implements SaleDataAccess {

    @Override
    public ArrayList<Sale> readAllSales() throws SaleException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM sales;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Sale> sales = new ArrayList<Sale>();

            while (resultSet.next()) {
                int codeID = resultSet.getInt("codeID");
                LocalDate dateSale = resultSet.getDate("dateSale").toLocalDate();
                int userID = resultSet.getInt("userID");
                Sale sale = new Sale(codeID, dateSale, userID);
                sales.add(sale);
            }
            return sales;
        }catch (SQLException exception) {
            throw new SaleException("Erreur dans la lecture de toutes les ventes.", new OneException(), new ReadException());
        }
    }

    @Override
    public Sale getSaleBySaleDetail(SaleDetail saleDetail) throws SaleException {
        try {
            System.out.println("la");
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM sales WHERE codeID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, saleDetail.getSaleCode());
            System.out.println(saleDetail.getSaleCode());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int codeID = resultSet.getInt("codeID");
            LocalDate dateSale = resultSet.getDate("dateSale").toLocalDate();
            int userID = resultSet.getInt("userID");
            Sale sale = new Sale(codeID, dateSale, userID);
            System.out.println("cfini");
            return sale;
        } catch (SQLException exception) {
            throw new SaleException("Erreur dans la lecture de la vente.", new OneException(), new ReadException());
        }
    }
}
