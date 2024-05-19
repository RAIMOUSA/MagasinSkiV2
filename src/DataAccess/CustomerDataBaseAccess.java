package DataAccess;

import InterfaceAccess.CustomerDataAccess;
import Model.Customer;

import java.sql.*;
import Exception.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


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

        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM customer WHERE userID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                LocalDate dateOfBirth = resultSet.getDate("dateOfBirth").toLocalDate();
                boolean isProfessional = resultSet.getBoolean("isProfessional");
            }
        } catch (Exception exception) {
            throw new CustomerException(exception.getMessage(), new OneException(), new ReadException());
        }
        return null;
    }

    public Customer readCustomer() throws CustomerException {
        return null;
    }

    public void updateCustomer(Customer customer) throws CustomerException {
        // code to update a customer
        try {
            if (getCustomerById(customer.getUserID()) == null) {
                throw new CustomerException("Customer not found", new OneException(), new UpdateException());
            } else {
                Connection connection = SingletonConnexion.getInstance();
                String query = "UPDATE customer SET firstName = ?, lastName = ?, dateOfBirth = ?, isProfessional = ?," +
                        "gender = ?, localityID = ?, mail = ? WHERE userID = ?;";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setBoolean(4, customer.isProfessional());
                statement.setInt(6, customer.getLocalityID());
                statement.setString(7, customer.getMail());

                if (customer.getDateOfBirth() != null) {
                    statement.setDate(3, Date.valueOf(customer.getDateOfBirth()));
                } else {
                    statement.setNull(3, Types.NULL);
                }
                if (customer.getGender() != '\u0000') {
                    statement.setString(5, String.valueOf(customer.getGender()));
                } else {
                    statement.setNull(5, Types.NULL);
                }

                statement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new CustomerException(exception.getMessage(), new OneException(), new UpdateException());
        }
    }

    public void deleteCustomer(int codeCustomer) throws CustomerException {
        // code to delete a customer
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "DELETE FROM customer WHERE userID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, codeCustomer);
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new CustomerException(exception.getMessage(), new OneException(), new DeleteException());
        }
    }

    @Override
    public ArrayList<Customer> readAllCustomers() throws CustomerException {
        try {

            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM customer;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Customer> customers = new ArrayList<Customer>();

            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                boolean isProfessional = resultSet.getBoolean("isProfessional");
                int localityID = resultSet.getInt("localityID");
                String mail = resultSet.getString("mail");

                Customer customer = new Customer(userID, firstName, lastName, isProfessional, localityID, mail);
                LocalDate dateOfBirth = resultSet.getDate("dateOfBirth").toLocalDate();
                if (!resultSet.wasNull()) {
                    customer.setDateOfBirth(dateOfBirth);
                }

                //il faut ptet mettre string, et du coup modifier customer
                //char gender = resultSet.getCharacterStream("gender");
                if (!resultSet.wasNull()) {
                    //customer.setGender(gender);
                }
                customers.add(customer);
            }

            return customers;
            } catch (Exception exception) {
                throw new CustomerException(exception.getMessage(), new AllException(), new ReadException());
            }
    }

    @Override
    public ArrayList<Customer> searchCustomers(String keyword) throws CustomerException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT * FROM customer WHERE firstName LIKE ? OR lastName LIKE ? OR mail LIKE ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Customer> customers = new ArrayList<Customer>();
            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                boolean isProfessional = resultSet.getBoolean("isProfessional");
                int localityID = resultSet.getInt("localityID");

            }
        }catch (Exception exception) {
            throw new CustomerException(exception.getMessage(), new AllException(), new ReadException());
        }
        return null;
    }
    //elle est déjà plus haute, why ?
    public ArrayList<Customer> readAllCustomer() throws CustomerException {
        return null;
    }

    public int getNumberCustomer() throws NumberCustomerException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "SELECT COUNT(*) FROM customer;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (Exception exception) {
            throw new NumberCustomerException(exception.getMessage());
        }
    }

    public int getNextCode() throws NextCodeCustomerException {
        return 0;
    }
}
