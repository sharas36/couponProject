package DAO.DAOCustomers;

import Users.Customer;
import Users.User;
import firstStep.ConnectionPool;
import firstStep.Coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomersDBDAO implements CustomersDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstanse();
    private PreparedStatement preparedStatement;
    private ResultSet resultset;
    private Object lock = new Object();

    public void addCustomer(Customer customer) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "insert into customers(firstName, lastName, email, password) values ('" +
                customer.getFirstName() + "', '" + customer.getLastName() + "', '" + customer.getEmail() + "', '" + customer.getPassword() + "')";
        synchronized (lock) {
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            customer.setCustomerId(getCustomerIdByMail(customer.getEmail()));
        }
        connectionPool.restoreConnection(connection);
    }

    public void deleteCustomer(int customerId) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "delete from customers where customerId = '" + customerId + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
        connectionPool.restoreConnection(connection);
    }

    public ArrayList<Customer> getAllCustomers() throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "select * from customers";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ArrayList<Customer> customers = new ArrayList<>();
        synchronized (lock) {
            resultset = preparedStatement.executeQuery();
            resultset.last();
            int numOfRows = resultset.getRow();
            if (numOfRows == 0) {
                return null;
            }
            resultset.beforeFirst();
            while (resultset.next()) {
                int customerId = resultset.getInt("customerId");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String email = resultset.getString("email");
                String password = resultset.getString("password");
                User customer = new Customer(firstName, lastName, email, password);
                customers.add((Customer) customer);
            }
        }
        connectionPool.restoreConnection(connection);
        return customers;
    }

    public Customer getCustomer(int customerId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        ResultSet rs;
        String sql = "select * from customers where customerId = '" + customerId + "'";
        Customer customer = null;

        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
        }

        connectionPool.restoreConnection(connection);

        while (rs.next()) {
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String email = rs.getString("email");
            String password = rs.getString("password");
            customer = new Customer(firstName, lastName, email, password);
        }

        return customer;
    }

    public int getCustomerIdByMail(String email) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from customers where email = '" + email + "'";
        int id = 0;
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("customerId");
            }
        }
        connectionPool.restoreConnection(connection);
        return id;
    }

    public boolean isCustomerExist(String email, String password) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from customers where email ='" + email + "' and password = '" + password + "'";
        synchronized (lock) {
            resultset = preparedStatement.executeQuery(sql);
            connectionPool.restoreConnection(connection);
        }
        if (resultset.next()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isThisMailExist(String email) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from customers where email ='" + email + "'";
        synchronized (lock) {
            preparedStatement = connection.prepareStatement(sql);
            resultset = preparedStatement.executeQuery();
        }
        connectionPool.restoreConnection(connection);
        if (resultset.next()) {
            return true;
        }
        return false;
    }

    public void updateCustomer(int customerId, String email, String password) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "update customers set email = '" + email + "' and set password = '" + password + "' where customerId = '" + customerId + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
        connectionPool.restoreConnection(connection);
    }


}
