package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import Users.Company;
import Users.Customer;
import firstStep.Coupon;
import firstStep.SystemException;
import lombok.Data;

import java.sql.SQLException;
import java.util.ArrayList;


public class AdminFacade extends MainFacade {
    public AdminFacade(CompaniesDBDAO companiesDBDAO, CouponsDBDAO couponsDBDAO, CustomersDBDAO customersDBDAO) {
        super(companiesDBDAO, couponsDBDAO, customersDBDAO);
    }


    @Override
    public Boolean login(String email, String password) throws SystemException {
        if (!(email.equals("admin@admin.com"))) {
            throw new SystemException("This email is wrong");
        } else if (!(password.equals("admin"))) {
            throw new SystemException("This password is wrong");
        }
        return true;
    }

    public void addCompany(Company company) throws SQLException, SystemException {

        if (this.companiesDBDAO.isThisMailExist(company.getEmail())) {
            throw new SystemException("This company already exist");
        }

        if (this.companiesDBDAO.isThisNameExist(company.getCompanyName())) {
            throw new SystemException("This company name already exist");
        }

        this.companiesDBDAO.addCompany(company);
    }

    // wait for hibernate (merge function).
    public void updateCompany(Company company, String email, String password) throws SystemException, SQLException {
        if (!this.companiesDBDAO.isThisMailExist(company.getEmail())) {
            throw new SystemException("This company isnt exist");
        }
        this.companiesDBDAO.updateCompany(company, email, password);
    }

    public void deleteCompany(int companyId) throws SQLException, SystemException {
        if (companiesDBDAO.getOneCompany(companyId) == null) {
            throw new SystemException("This company isnt exist");
        }
        ArrayList<Coupon> coupons = couponsDBDAO.getAllCouponsOfCompany(companyId);
        this.companiesDBDAO.deleteCompany(companyId);
        this.couponsDBDAO.deleteAllCouponsByCompany(companyId);
        for (Coupon coupon : coupons) {
            this.couponsDBDAO.deleteAllPurchasesForOneCoupon(coupon.getCouponId());
        }


    }

    public ArrayList<Company> getAllCompanies() throws SQLException, SystemException {
        if (this.companiesDBDAO.getAllCompanies() == null) {
            throw new SystemException("We dont have any companies");
        }
        return this.companiesDBDAO.getAllCompanies();
    }

    public Company getOneCompany(int companyId) throws SQLException, SystemException {
        if (companiesDBDAO.getOneCompany(companyId) == null) {
            throw new SystemException("This company isnt exist");
        }
        return companiesDBDAO.getOneCompany(companyId);
    }

    public void addCustomer(Customer customer) throws SQLException, SystemException {
        if (this.customersDBDAO.isThisMailExist(customer.getEmail())) {
            throw new SystemException("This customer already exist");
        }
        this.customersDBDAO.addCustomer(customer);
    }

    public void updateCustomer(int customerId, String email, String password) throws SystemException, SQLException {
        if (this.customersDBDAO.getCustomer(customerId) == null) {
            throw new SystemException("This customer isnt exist");
        } else if (customersDBDAO.getCustomerIdByMail(email) != 0 && customersDBDAO.getCustomerIdByMail(email) != customerId) {
            throw new SystemException("This email is already exist");
        }
        this.customersDBDAO.updateCustomer(customerId, email, password);
    }

    public void deleteCustomer(int customerId) throws SQLException, SystemException {
        if (customersDBDAO.getCustomer(customerId) == null) {
            throw new SystemException("This customer isnt exist");
        }
        this.customersDBDAO.deleteCustomer(customerId);
        this.couponsDBDAO.deleteAllPurchasesForOneCustomer(customerId);
    }

    public ArrayList<Customer> getAllCustomers() throws SQLException, SystemException {
        if (this.customersDBDAO.getAllCustomers() == null) {
            throw new SystemException("We dont have any customers");
        }
        return this.customersDBDAO.getAllCustomers();
    }

    public Customer getOneCustomer(int customerId) throws SQLException, SystemException {
        if (customersDBDAO.getCustomer(customerId) == null) {
            throw new SystemException("This customer isnt exist");
        }
        return customersDBDAO.getCustomer(customerId);
    }
}
