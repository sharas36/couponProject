package firstStep;

import DAO.DAOCompanies.CompaniesDBDAO;
import Facade.AdminFacade;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {


		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		System.out.println(companiesDBDAO.isCompanyExist("vfd", "vfdsv"));



	}

}
