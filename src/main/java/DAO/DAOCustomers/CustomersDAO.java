package DAO.DAOCustomers;

import Users.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomersDAO {

    public void addCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(int customerId) throws SQLException;
    public ArrayList<Customer> getAllCustomers() throws SQLException;
    public Customer getCustomer(int customerId) throws SQLException;
    public int getCustomerIdByMail(String email) throws SQLException;
    public boolean isCustomerExist(String email, String password) throws SQLException;
    public boolean isThisMailExist(String email) throws SQLException;
    public void updateCustomer(int customerId, String email, String password) throws SQLException;





}