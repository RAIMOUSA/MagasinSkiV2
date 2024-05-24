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


    public void createCustomer(Customer customer) throws CustomerException {
        this.manager.createCustomer(customer);
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

}
