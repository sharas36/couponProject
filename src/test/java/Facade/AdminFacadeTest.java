package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import Users.Company;
import firstStep.SystemException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

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
    void getAllCompanies() {
    }

    @Test
    void getOneCompany() {
    }

    @Test
    void addCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void getAllCustomers() {
    }

    @Test
    void getOneCustomer() {
    }
}