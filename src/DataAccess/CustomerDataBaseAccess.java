package DataAccess;

import InterfaceAccess.CustomerDataAccess;
import Model.Customer;
import java.sql.*;
import Exception.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDataBaseAccess implements CustomerDataAccess {
    public void createCustomer(Customer customer) throws CustomerException {
        // code to create a customer
        try {
            // code to create a customer
            Connection connection = SingletonConnexion.getInstance();
            String query = "INSERT INTO customer VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, customer.getUserID());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setBoolean(5, customer.isProfessional());
            statement.setInt(7, customer.getLocalityID());
            statement.setString(8, customer.getMail());

            if (customer.getDateOfBirth() != null) {
                statement.setDate(4, Date.valueOf(customer.getDateOfBirth()));
            } else {
                statement.setNull(4, Types.NULL);
            }

            if (customer.getGender() != '\u0000') { // ca ou null ??, on testera
                statement.setString(6, String.valueOf(customer.getGender()));
            } else {
                statement.setNull(6, Types.NULL);
            }

            statement.executeUpdate();
        } catch (Exception exception) {
            throw new CustomerException(exception.getMessage(), new OneException(), new CreateException());
        }
    }

    @Override
    public Customer getCustomerById(int customerId) throws CustomerException {
        return null;
    }

    public Customer readCustomer() throws CustomerException {
        return null;
    }

    public void updateCustomer(Customer customer) throws CustomerException {
        // code to update a customer
    }

    public void deleteCustomer(int codeCustomer) throws CustomerException {
        // code to delete a customer
    }

    @Override
    public ArrayList<Customer> readAllCustomers() throws CustomerException {
        return null;
    }

    @Override
    public ArrayList<Customer> searchCustomers(String keyword) throws CustomerException {
        return null;
    }

    public ArrayList<Customer> readAllCustomer() throws CustomerException {
        return null;
    }

    public int getNumberCustomer() throws NumberCustomerException {
        return 0;
    }

    public int getNextCode() throws NextCodeCustomerException {
        return 0;
    }
}
