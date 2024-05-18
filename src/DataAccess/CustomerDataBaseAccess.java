package DataAccess;

import InterfaceAccess.CustomerDataAccess;
import Model.Customer;
import Exception.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDataBaseAccess implements CustomerDataAccess {
    public void createCustomer(Customer customer) throws CustomerException {
        // code to create a customer
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
