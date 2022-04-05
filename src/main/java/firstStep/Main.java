package firstStep;

import java.sql.SQLException;

import DAO.DAOCompanies.CompaniesDBDAO;

public class Main {

	public static void main(String[] args) {
		
		ConnectionPool connectionPool = ConnectionPool.getInstanse();
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		try {
			System.out.println(companiesDBDAO.isCompanyExist("fdvfd", "fsdgg"));	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
