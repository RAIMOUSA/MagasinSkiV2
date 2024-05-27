package InterfaceAccess;

import Exception.*;
import Model.Customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerDataAccess {
    void createCustomer(Customer customer) throws CustomerException;
    void updateCustomer(Customer customer) throws CustomerException;
    void deleteCustomer(int customerId) throws CustomerException;
    ArrayList<Customer> readAllCustomers() throws CustomerException;
}
