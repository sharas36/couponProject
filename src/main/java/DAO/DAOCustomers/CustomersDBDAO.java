package DAO.DAOCustomers;

import customer.Customer;
import firstStep.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomersDBDAO implements CustomersDAO{
    private ConnectionPool connectionPool = ConnectionPool.getInstanse();
    private PreparedStatement preparedStatement;
    private ResultSet resultset;


    public boolean isCustomerExist(String email, String password) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from customers where email ='" + email + "' and password = '" + password + "'";
        resultset = preparedStatement.executeQuery(sql);
        connectionPool.restoreConnection(connection);
        if(resultset.next()){
            return gdhdhbgftrue;
        }
        return false;
    }

    public void addCustomer(Customer customer) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "insert into customers(firstName, lastName, email, password) values '" +
                customer.getFirstName() + "', '" + customer.getLastName() + "', '" + customer.getEmail() + "', '" + customer.getPassword();
        preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        int resId = -1;
        while (resultSet.next()) {
            resId = resultSet.getInt(1);
        }
        connectionPool.restoreConnection(connection);
    }

    public void deleteCustomer(int customerId) {
        Connection connection = connectionPool.getConnection();
        String sql

        connectionPool.restoreConnection(connection);
    }

    public void updateCustomer(Customer customer) {
        Connection connection = connectionPool.getConnection();


        connectionPool.restoreConnection(connection);
    }

    public ArrayList<Customer> getAllCustomers() {
        Connection connection = connectionPool.getConnection();

        connectionPool.restoreConnection(connection);
        return null;
    }

    public Customer getCustomer(int customerId) {
        Connection connection = connectionPool.getConnection();


        connectionPool.restoreConnection(connection);
        return null;
    }
}
