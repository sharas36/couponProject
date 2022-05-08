package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import firstStep.SystemException;
import org.junit.jupiter.api.Test;

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
    void addCompany() {
    }

    @Test
    void updateCompany() {
    }

    @Test
    void deleteCompany() {
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