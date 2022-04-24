package firstStep;

import DAO.DAOCompanies.CompaniesDBDAO;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {

		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		System.out.println(companiesDBDAO.getAllCompanies().toString());




	}

}
