package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import Users.Company;
import Users.Customer;
import firstStep.SystemException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdminFacadeTest {

    AdminFacade adminFacade = new AdminFacade(new CompaniesDBDAO(), new CouponsDBDAO(), new CustomersDBDAO());

    @Test
    void login() throws SystemException {
        assertTrue(adminFacade.login("admin@admin.com", "admin"));


        assertThrows(SystemException.class, () -> {
            adminFacade.login("admin@admin.com123", "admin");
        });
        assertThrows(SystemException.class, () -> {
            adminFacade.login("admin@admin.com", "admin123");
        });
    }

    @Test
    void addCompany() throws SystemException, SQLException {


        assertThrows(SystemException.class, () -> {
            adminFacade.addCompany(new Company("vb", "123", "vfdvd"));
        });
    }

    @Test
    void updateCompany() throws SystemException, SQLException {

        Company company = new Company("bgfbfgb", "4254254252", "gfdfd");
        assertThrows(SystemException.class, () -> {
            adminFacade.updateCompany(company, "12345", "123");
        });
    }

    @Test
    void deleteCompany() throws SystemException, SQLException {

//        assertThrows(SystemException.class, () -> {
//            adminFacade.deleteCompany(2);
//        });
        adminFacade.deleteCompany(7);
        assertNull(new CouponsDBDAO().getOneCoupon(3));
    }

    @Test
    void getAllCompanies() throws SQLException {
        ArrayList<Company> companies = adminFacade.getCompaniesDBDAO().getAllCompanies();
        assertNotNull(companies);
        assertTrue(companies.size() == 114);
    }

    @Test
    void getOneCompany() throws SystemException, SQLException {
        Company company = adminFacade.getOneCompany(120);
        assertNotNull(company);
        System.out.println(company.getCompanyName());
    }

    @Test
    void addCustomer() throws SystemException, SQLException {
        Customer customer = new Customer("test", "Customer", "test@gmail.com", "12345");
        adminFacade.addCustomer(customer);
        assertNotNull(adminFacade.getOneCustomer(customer.getCustomerId()));
    }

    @Test
    void updateCustomer() throws SystemException, SQLException {

        assertDoesNotThrow( () ->{
            adminFacade.updateCustomer(100, "updateTest@gmail.com", "updateTest");
        });

        System.out.println(adminFacade.getOneCustomer(100).getEmail());
        System.out.println(adminFacade.getOneCustomer(100).getPassword());

    }

    @Test
    void deleteCustomer() throws SystemException, SQLException {

        assertDoesNotThrow( () ->{
            adminFacade.deleteCustomer(106);
        });

        assertThrows(SystemException.class, () -> {
            adminFacade.getOneCustomer(106);
        });
    }

    @Test
    void getAllCustomers() throws SystemException, SQLException {
        assertDoesNotThrow(()->{
            adminFacade.getAllCustomers();
        });
        assertNotNull(adminFacade.getAllCustomers());
        assertTrue(adminFacade.getAllCustomers().size() == 99);
    }

    @Test
    void getOneCustomer() throws SystemException, SQLException {
        assertDoesNotThrow(()->{
            adminFacade.getOneCustomer(107);
        });
        assertTrue(adminFacade.getOneCustomer(100).getLastName().equals("92"));
    }
}