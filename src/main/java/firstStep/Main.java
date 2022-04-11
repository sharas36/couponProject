package firstStep;

import java.sql.SQLException;
import java.util.List;

import DAO.DAOCompanies.CompaniesDBDAO;
import company.Company;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		ConnectionPool connectionPool = ConnectionPool.getInstanse();
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		Company comp = new Company("bgfbgfbgfb", "vfbcvbcvnvnvnvbvb", "fsvfd");

		companiesDBDAO.updateCompany(comp, "vfbcvbcvnvnvnvbvb", "1234");
		System.out.println(companiesDBDAO.getCompanyIdByEmail("vfbcvbcvnvnvnvbvb"));

	}

}
