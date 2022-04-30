package DAO.DAOCompanies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Users.Company;
import firstStep.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO {

    private ConnectionPool connectionPool = ConnectionPool.getInstanse();
    private PreparedStatement preparedStatement;
    private ResultSet resultset;
    private Object lock = new Object();

    public Boolean isCompanyExist(String email, String password) throws SQLException {

        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * from companies where email = '" + email + "' and password = '" + password + "'";
        synchronized (lock) {
            this.preparedStatement = connection.prepareStatement(sql);
            this.resultset = this.preparedStatement.executeQuery();
        }
        connectionPool.restoreConnection(connection);
        if (this.resultset.next()) {
            return true;
        }
        return false;

    }

    @Override
    public boolean isThisMailExist(String email) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * from companies where email = '" + email + "'";
        synchronized (lock) {
            this.preparedStatement = connection.prepareStatement(sql);
            this.resultset = this.preparedStatement.executeQuery();
        }
        connectionPool.restoreConnection(connection);
        if (this.resultset.next()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isThisNameExist(String companyName) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * from companies where companyName = '" + companyName + "'";
        synchronized (lock) {
            this.preparedStatement = connection.prepareStatement(sql);
            this.resultset = this.preparedStatement.executeQuery();
        }
        connectionPool.restoreConnection(connection);
        if (this.resultset.next()) {
            return true;
        }
        return false;
    }

    public void addCompany(Company company) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "insert into companies (name, email, password) values ('" +
                company.getCompanyName() + "', '" + company.getEmail() + "', '" + company.getPassword() + "')";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            company.setCompanyId(this.getCompanyId(company.getEmail()));
        }
        connectionPool.restoreConnection(connection);
    }

    public void updateCompany(Company company, String email, String password) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "update companies set email ='" + email + "', password ='" + password + "' where companyId ='" + company.getCompanyId() + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
        connectionPool.restoreConnection(connection);
    }

    public void deleteCompany(int companyId) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "update companies set deleted = " + 1 + " where id = '" + companyId + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
        connectionPool.restoreConnection(connection);
    }

    public ArrayList<Company> getAllCompanies() throws SQLException {
        Connection connection = connectionPool.getConnection();

        ArrayList<Company> companies = new ArrayList<Company>();
        String sql = "select * from companies where deleted = " + 0;
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String companyName = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int id = resultSet.getInt("companyId");
                Company company = new Company(id, companyName, email, password);
                companies.add(company);
            }
        }
        connectionPool.restoreConnection(connection);
        return companies;
    }

    public Company getOneCompany(int companyId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "select from companies where id = '" + companyId + "' and deleted = " + 0;
        Company company = null;
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            String companyName = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            company = new Company(companyName, email, password);
        }

        connectionPool.restoreConnection(connection);
        return company;
    }

    public int getCompanyId(String email) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from companies where email = '" + email + "'";
        int id = 0;
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("companyId");
            }
        }
        connectionPool.restoreConnection(connection);
        return id;
    }

    public void restoreDeletedCompany(String email) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "update Companies set deleted = " + 0 + " where email = '" + email + "'";
        ResultSet resultSet;
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        }
        connectionPool.restoreConnection(connection);
    }

    public void restoreAllDeletedCompanies() throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "update Companies set deleted = " + 0 + " where deleted = " + 1;
        ResultSet resultSet;
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        }
        connectionPool.restoreConnection(connection);
    }


}
