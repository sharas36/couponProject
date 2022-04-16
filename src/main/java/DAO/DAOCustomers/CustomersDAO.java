package DAO.DAOCustomers;

import Users.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomersDAO {

    public boolean isCustomerExist(String email, String password) throws SQLException;
    public void addCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(int customerId) throws SQLException;
    public void updateCustomerMail(int customerId, String email) throws SQLException;
    public void updateCustomerFirstName(Customer customer, String firstName) throws SQLException;
    public void updateCustomerLastName(Customer customer, String lastName) throws SQLException;
    public void updateCustomerPassword(Customer customer, String firstName) throws SQLException;
    public int getCustomerIdByMail(String email) throws SQLException;
    public ArrayList<Customer> getAllCustomers() throws SQLException;
    public Customer getCustomer(int customerId) throws SQLException;

}