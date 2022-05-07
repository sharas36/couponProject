package DAO.DAOCompanies;

import Users.Company;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CompaniesDBDAOTest {
    CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
    Company company = new Company("","");




    @Test
    void addCompany() throws SQLException {
        assertDoesNotThrow(() -> {
            companiesDBDAO.addCompany(new Company("770", "770", "770"));
        });

        assertThrows(NullPointerException.class, () -> {
            companiesDBDAO.addCompany(null);
        });

    }

    @Test
    void deleteCompany() throws SQLException {
        companiesDBDAO.deleteCompany(1);

        assertDoesNotThrow(() -> {
            companiesDBDAO.deleteCompany(1);
        });

    }

    @Test
    void getAllCompanies() throws SQLException {
        assertNotNull(companiesDBDAO.getAllCompanies());
    }

    @Test
    void getCompanyId() throws SQLException {
        int i = companiesDBDAO.getCompanyId("770");
        assertTrue(i > 0);

        i = companiesDBDAO.getCompanyId("emailNotExist");
        assertTrue(i == 0);

    }

    @Test
    void getOneCompany() throws SQLException {
        assertNotNull(companiesDBDAO.getOneCompany(2));
    }

    @Test
    void isThisMailExist() throws SQLException {
        assertTrue(companiesDBDAO.isThisMailExist("770"));
        assertTrue(companiesDBDAO.isThisMailExist("emailNotExistForExample") == false);

    }

    @Test
    void isThisNameExist() throws SQLException {
        assertTrue(companiesDBDAO.isThisNameExist("dh770"));
        assertFalse(companiesDBDAO.isThisNameExist("nameNotExistForExample"));
    }

    @Test
    void loginCheck() throws SQLException {
        assertTrue(companiesDBDAO.loginCheck("770", "770"));
        assertFalse(companiesDBDAO.loginCheck("emailNotExistForExample", "nameNotExistForExample"));

    }

    @Test
    void updateCompany() {
        assertDoesNotThrow(() -> {
            companiesDBDAO.updateCompany(companiesDBDAO.getOneCompany(2), "changeName", "changePassword");
        });
    }
}