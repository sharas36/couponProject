package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import Users.Company;
import Users.Customer;
import firstStep.SystemException;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminFacade extends MainFacade{
    public AdminFacade(CompaniesDBDAO companiesDBDAO, CouponsDBDAO couponsDBDAO, CustomersDBDAO customersDBDAO) {
        super(companiesDBDAO, couponsDBDAO, customersDBDAO);
    }

    @Override
    public Boolean login(String email, String password) {
        if(!email.equals("admin@admin.com")){
            new SystemException("This email is wrong");
        }
        else if(!password.equals("admin")){
            new SystemException("This password is wrong");
        }
        return true;
    }

    public void addCompany(Company company) throws SQLException, SystemException {
        if(this.companiesDBDAO.isCompanyExist(company.getEmail(), company.getPassword())){
            throw new SystemException("This company already exist");
        }
        this.companiesDBDAO.addCompany(company);
    }
    // wait for hibernate (merge function).
    public void updateCompany(Company company) throws SystemException, SQLException {
        if(!this.companiesDBDAO.isCompanyExist(company.getEmail(), company.getPassword())){
            throw new SystemException("This company isnt exist");
        }
        this.companiesDBDAO.updateCompany(company, company.getEmail(), company.getPassword());
    }

    public void deleteCompany(int companyId) throws SQLException, SystemException {
        if(companiesDBDAO.getOneCompany(companyId) == null){
            throw new SystemException("This company isnt exist");
        }
        this.companiesDBDAO.deleteCompany(companyId);
    }

    public ArrayList<Company> getAllCompanies() throws SQLException, SystemException {
        if(this.companiesDBDAO.getAllCompanies() == null){
            throw new SystemException("We dont have any companies");
        }
        return this.companiesDBDAO.getAllCompanies();
    }

    public Company getOneCompany(int companyId) throws SQLException, SystemException {
        if(companiesDBDAO.getOneCompany(companyId) == null){
            throw new SystemException("This company isnt exist");
        }
        return companiesDBDAO.getOneCompany(companyId);
    }

    public void addCustomer(Customer customer) throws SQLException, SystemException {
        if(this.customersDBDAO.isThisMailExist(customer.getEmail())){
            throw new SystemException("This customer already exist");
        }
        this.customersDBDAO.addCustomer(customer);
    }

    public void updateCustomerPassword(Customer customer, String password) throws SystemException, SQLException {
        if(!this.customersDBDAO.isThisMailExist(customer.getEmail())){
            throw new SystemException("This customer isnt exist");
        }
        this.customersDBDAO.updateCustomerPassword(customer, password);
    }

    public void updateCustomerEmail(Customer customer, String email) throws SystemException, SQLException {
        if(!this.customersDBDAO.isThisMailExist(customer.getEmail())){
            throw new SystemException("This customer isnt exist");
        }
        this.customersDBDAO.updateCustomerPassword(customer, email);
    }

    public void updateCustomerFirstName(Customer customer, String firstName) throws SystemException, SQLException {
        if(!this.customersDBDAO.isThisMailExist(customer.getEmail())){
            throw new SystemException("This customer isnt exist");
        }
        this.customersDBDAO.updateCustomerPassword(customer, firstName);
    }

    public void updateCustomerLastName(Customer customer, String lastName) throws SystemException, SQLException {
        if(!this.customersDBDAO.isThisMailExist(customer.getEmail())){
            throw new SystemException("This customer isnt exist");
        }
        this.customersDBDAO.updateCustomerPassword(customer, lastName);
    }

    public void deleteCustomer(int customerId) throws SQLException, SystemException {
        if(customersDBDAO.getCustomer(customerId) == null){
            throw new SystemException("This customer isnt exist");
        }
        this.companiesDBDAO.deleteCompany(customerId);
    }

    public ArrayList<Customer> getAllCustomers() throws SQLException, SystemException {
        if(this.customersDBDAO.getAllCustomers() == null){
            throw new SystemException("We dont have any customers");
        }
        return this.customersDBDAO.getAllCustomers();
    }

    public Customer getOneCustomer(int customerId) throws SQLException, SystemException {
        if(customersDBDAO.getCustomer(customerId) == null){
            throw new SystemException("This customer isnt exist");
        }
        return customersDBDAO.getCustomer(customerId);
    }
}
