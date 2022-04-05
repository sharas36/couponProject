package DAO.DAOCustomers;

import customer.Customer;
import firstStep.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomersDAO {

    public boolean isCustomerExist(String email, String password) throws SQLException;
    public void addCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(int customerId);
    public void updateCustomer(Customer customer);
    public ArrayList<Customer> getAllCustomers();
    public Customer getCustomer(int customerId);

}