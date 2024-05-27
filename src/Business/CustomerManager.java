package Business;

import DataAccess.CustomerDataBaseAccess;
import Exception.*;

import InterfaceAccess.CustomerDataAccess;
import Model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private CustomerDataAccess customerAccess;

    public CustomerManager() { this.setCustomerAccess(new CustomerDataBaseAccess());}

    public void setCustomerAccess(CustomerDataAccess customerAccess) {
        this.customerAccess = customerAccess;
    }

    public void createCustomer(Customer customer) throws CustomerException {
        this.customerAccess.createCustomer(customer);
    }


    public void updateCustomer(Customer customer) throws CustomerException {
        this.customerAccess.updateCustomer(customer);
    }

    public void deleteCustomer(int customerId) throws CustomerException {
        this.customerAccess.deleteCustomer(customerId);
    }

    public ArrayList<Customer> readAllCustomers() throws CustomerException {
        return this.customerAccess.readAllCustomers();
    }

}
