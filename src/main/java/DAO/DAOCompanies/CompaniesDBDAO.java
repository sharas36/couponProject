package DAO.DAOCompanies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import company.Company;
import firstStep.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO {

	private ConnectionPool connectionPool = ConnectionPool.getInstanse();
	private PreparedStatement preparedStatement;
	private ResultSet resultset;

	public Boolean isCompanyExist(String email, String password) throws SQLException {

		Connection connection = connectionPool.getConnection();
		String sql = "SELECT * from companies where email = ? and password = ?";
		this.preparedStatement = connection.prepareStatement(sql);
		this.preparedStatement.setString(1, email);
		this.preparedStatement.setString(2, password);
		this.resultset = this.preparedStatement.getResultSet();
		connectionPool.restoreConnection(connection);
		if (this.resultset.next()) {
			return true;
		}
		return false;

	}

	public void addCompany(Company company) throws SQLException {
		Connection connection = connectionPool.getConnection();

		String sql = "insert into companies (name, email, password) values ('" + company.getCompanyName() + "', "
				+ company.getEmail() + "', " + company.getPassword() + "')";
		PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.execute();
		ResultSet resultSet = preparedStatement.getResultSet();
		int resId = -1;
		while (resultSet.next()) {
			resId = resultSet.getInt(1);
		}
		connectionPool.restoreConnection(connection);
	}

	public void updateCompany(Company company, String email, String password) throws SQLException {
		Connection connection = connectionPool.getConnection();

		String sql = "update companies email ='" + email + "', password ='" + password + "' where id ='" + company.getCompanyId() + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
		
		connectionPool.restoreConnection(connection);
	}

	public void deleteCompany(int companyId) throws SQLException {
		Connection connection = connectionPool.getConnection();
		
		String sql = "delete companies where id = '" + companyId + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();

		connectionPool.restoreConnection(connection);
	}

	public ArrayList<Company> getAllCompanies() throws SQLException {
		Connection connection = connectionPool.getConnection();
		
		ArrayList<Company> companies = new ArrayList<Company>();
		String sql = "select * from companies";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			String companyName = resultSet.getString("name");
			String email = resultSet.getString("email");;
			String password = resultSet.getString("password");
			Company company = new Company(companyName, email, password);
			companies.add(company);
		}
		
		connectionPool.restoreConnection(connection);
		return companies;
	}

	public Company getOneCompany(int companyId) throws SQLException {
		Connection connection = connectionPool.getConnection();
		String sql = "select from companies where id = '" + companyId + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		String companyName = resultSet.getString("name");
		String email = resultSet.getString("email");;
		String password = resultSet.getString("password");
		Company company = new Company(companyName, email, password);
		
		
		connectionPool.restoreConnection(connection);
		return company;
	}

}
