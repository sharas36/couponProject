package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import firstStep.SystemException;
import firstStep.SystemException;

import java.sql.SQLException;

public class CustomerFacade extends MainFacade{
    public CustomerFacade(CompaniesDBDAO companiesDBDAO, CouponsDBDAO couponsDBDAO, CustomersDBDAO customersDBDAO) {
        super(companiesDBDAO, couponsDBDAO, customersDBDAO);
    }

    @Override
    public Boolean login(String email, String password) throws SQLException{
        if(customersDBDAO.isCustomerExist(email, password)){
            return true;
        }
        if(!customersDBDAO.isThisMailExist(email)){
            new SystemException("This mail isnt exist");
        }
        else
            new SystemException("The password isnt match the mail");
        return null;
    }


}
