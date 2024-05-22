package DataAccess;

import InterfaceAccess.CustomerDataAccess;
import Model.Contact;
import Model.Customer;

import java.sql.*;
import Exception.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class CustomerDataBaseAccess implements CustomerDataAccess {
    public void createCustomer(Customer customer) throws CustomerException {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String query = "INSERT INTO customer VALUES (?,?,?,?,?,?,?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customer.getUserID());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());

            LocalDate dateOfBirth = customer.getDateOfBirth();
            if (dateOfBirth != null) {
                statement.setDate(4, Date.valueOf(dateOfBirth));
            } else {
                statement.setNull(4, Types.DATE);
            }

            statement.setBoolean(5, customer.isProfessional());

            String gender = customer.getGender();
            if (gender != null) { // ca ou null ??, on testera
                statement.setString(6, gender);
            } else {
                statement.setNull(6, Types.VARCHAR);
            }

            statement.setInt(7, customer.getLocalityID());
            statement.setString(8, customer.getMail());

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
            Connection connection = SingletonConnexion.getInstance();
            String query = "UPDATE customer SET firstName = ?, lastName = ?, dateOfBirth = ?, isProfessional = ?," +
                    "gender = ?, localityID = ?, mail = ? WHERE userID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());

            if (customer.getDateOfBirth() != null) {
                statement.setDate(3, Date.valueOf(customer.getDateOfBirth()));
            } else {
                statement.setNull(3, Types.NULL);
            }

            statement.setBoolean(4, customer.isProfessional());

            if (customer.getGender() != null) {
                statement.setString(5, String.valueOf(customer.getGender()));
            } else {
                statement.setNull(5, Types.NULL);
            }

            statement.setInt(6, customer.getLocalityID());
            statement.setString(7, customer.getMail());
            statement.setInt(8, customer.getUserID());

            statement.executeUpdate();
        } catch (Exception exception) {
            throw new CustomerException(exception.getMessage(), new OneException(), new UpdateException());
        }
    }

    public void deleteCustomer(int codeCustomer) throws CustomerException {
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

                Date result = resultSet.getDate("dateOfBirth");
                if (!resultSet.wasNull()) {
                    customer.setDateOfBirth(result.toLocalDate());
                }

                String gender = resultSet.getString("gender");
                if (!resultSet.wasNull()) {
                    customer.setGender(gender);
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
            String query = "SELECT * FROM customer WHERE userID = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            //statement.setInt(1, code);
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
                String gender = resultSet.getString("gender");
                if (!resultSet.wasNull()) {
                    customer.setGender(gender);
                }
                customers.add(customer);

            }
            return customers;
        }catch (Exception exception) {
            throw new CustomerException(exception.getMessage(), new AllException(), new ReadException());
        }
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
