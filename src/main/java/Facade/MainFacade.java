package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import firstStep.SystemException;

import java.sql.SQLException;

public abstract  class MainFacade {

    protected CompaniesDBDAO companiesDBDAO;
    protected CouponsDBDAO couponsDBDAO;
    protected CustomersDBDAO customersDBDAO;

    public MainFacade(CompaniesDBDAO companiesDBDAO, CouponsDBDAO couponsDBDAO, CustomersDBDAO customersDBDAO) {
        this.companiesDBDAO = companiesDBDAO;
        this.couponsDBDAO = couponsDBDAO;
        this.customersDBDAO = customersDBDAO;
    }

    public abstract Boolean login(String email, String password) throws SQLException, SystemException;
}
