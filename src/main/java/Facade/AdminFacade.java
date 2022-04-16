package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import Users.Company;
import Users.Customer;
import firstStep.Exceptions;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminFacade extends MainFacade{
    public AdminFacade(CompaniesDBDAO companiesDBDAO, CouponsDBDAO couponsDBDAO, CustomersDBDAO customersDBDAO) {
        super(companiesDBDAO, couponsDBDAO, customersDBDAO);
    }

    @Override
    public Boolean login(String email, String password) { //need to be done!!!!
        return null;
    }

    public void addCompany(Company company) throws SQLException, Exceptions {
        if(this.companiesDBDAO.isCompanyExist(company.getEmail(), company.getPassword())){
            throw new Exceptions("This company already exist");
        }
        this.companiesDBDAO.addCompany(company);
    }

    public void updateCompany(Company company) throws Exceptions, SQLException {
        if(!this.companiesDBDAO.isCompanyExist(company.getEmail(), company.getPassword())){
            throw new Exceptions("This company isnt exist");
        }
        this.companiesDBDAO.updateCompany(company, company.getEmail(), company.getPassword());
    }

    public void deleteCompany(int companyId) throws SQLException, Exceptions {
        if(companiesDBDAO.getOneCompany(companyId) == null){
            throw new Exceptions("This company isnt exist");
        }
        this.companiesDBDAO.deleteCompany(companyId);
    }

    public ArrayList<Company> getAllCompanies() throws SQLException, Exceptions {
        if(this.companiesDBDAO.getAllCompanies().size() == 0){
            throw new Exceptions("We dont have any companies");
        }
        return this.companiesDBDAO.getAllCompanies();
    }

    public Company getOneCompany(int companyId) throws SQLException, Exceptions {
        if(companiesDBDAO.getOneCompany(companyId) == null){
            throw new Exceptions("This company isnt exist");
        }
        return companiesDBDAO.getOneCompany(companyId);
    }

    public void addCustomer(Customer customer) throws SQLException, Exceptions {
        if(this.customersDBDAO.isCustomerExist(customer.getEmail(), customer.getPassword())){
            throw new Exceptions("This customer already exist");
        }
        this.customersDBDAO.addCustomer(customer);
    }

    public void updateCustomerPassword(Customer customer, String password) throws Exceptions, SQLException {
        if(!this.customersDBDAO.isCustomerExist(customer.getEmail(), customer.getPassword())){
            throw new Exceptions("This customer isnt exist");
        }
        this.customersDBDAO.updateCustomerPassword(customer, password);
    }

    public void updateCustomerEmail(Customer customer, String email) throws Exceptions, SQLException {
        if(!this.customersDBDAO.isCustomerExist(customer.getEmail(), customer.getPassword())){
            throw new Exceptions("This customer isnt exist");
        }
        this.customersDBDAO.updateCustomerPassword(customer, email);
    }

    public void updateCustomerFirstName(Customer customer, String firstName) throws Exceptions, SQLException {
        if(!this.customersDBDAO.isCustomerExist(customer.getEmail(), customer.getPassword())){
            throw new Exceptions("This customer isnt exist");
        }
        this.customersDBDAO.updateCustomerPassword(customer, firstName);
    }

    public void updateCustomerLastName(Customer customer, String lastName) throws Exceptions, SQLException {
        if(!this.customersDBDAO.isCustomerExist(customer.getEmail(), customer.getPassword())){
            throw new Exceptions("This customer isnt exist");
        }
        this.customersDBDAO.updateCustomerPassword(customer, lastName);
    }

    public void deleteCustomer(int customerId) throws SQLException, Exceptions {
        if(customersDBDAO.getCustomer(customerId) == null){
            throw new Exceptions("This customer isnt exist");
        }
        this.companiesDBDAO.deleteCompany(customerId);
    }

    public ArrayList<Customer> getAllCustomers() throws SQLException, Exceptions {
        if(this.customersDBDAO.getAllCustomers().size() == 0){
            throw new Exceptions("We dont have any customers");
        }
        return this.customersDBDAO.getAllCustomers();
    }

    public Customer getOneCustomer(int customerId) throws SQLException, Exceptions {
        if(customersDBDAO.getCustomer(customerId) == null){
            throw new Exceptions("This customer isnt exist");
        }
        return customersDBDAO.getCustomer(customerId);
    }
}
