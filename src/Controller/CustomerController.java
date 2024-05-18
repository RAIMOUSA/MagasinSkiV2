package Controller;

import Business.CustomerManager;
import Exception.*;
import Model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private CustomerManager manager;

    public CustomerController() {
        this.setManager(new CustomerManager());
    }

    public void setManager(CustomerManager manager) {
        this.manager = manager;
    }

    public int getNumberCustomer() throws NumberCustomerException {
        return this.manager.getNumberCustomer();
    }

    public void createCustomer(Customer customer) throws CustomerException {
        this.manager.createCustomer(customer);
    }

    public Customer getCustomerById(int customerId) throws CustomerException {
        return this.manager.getCustomerById(customerId);
    }

    public void updateCustomer(Customer customer) throws CustomerException {
        this.manager.updateCustomer(customer);
    }

    public void deleteCustomer(int customerId) throws CustomerException {
        this.manager.deleteCustomer(customerId);
    }

    public ArrayList<Customer> readAllCustomers() throws CustomerException {
        return this.manager.readAllCustomers();
    }

    public ArrayList<Customer> searchCustomers(String keyword) throws CustomerException {
        return this.manager.searchCustomers(keyword);
    }
}
