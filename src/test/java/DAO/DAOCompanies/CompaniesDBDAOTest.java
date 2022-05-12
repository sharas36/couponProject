package DAO.DAOCompanies;

import Users.Company;
import firstStep.Coupon;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CompaniesDBDAOTest {
    CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
    Company company = new Company("", "");


    @Test
    void addCompany() throws SQLException {
        assertDoesNotThrow(() -> {
            companiesDBDAO.addCompany(new Company("checkName", "checkEmail", "checkPassword"));
        });

        Company company = companiesDBDAO.getOneCompany(companiesDBDAO.getCompanyId("checkEmail"));
        assertNotNull(company);


    }

    @Test
    void deleteCompany() throws SQLException {
        assertDoesNotThrow(() -> {
            companiesDBDAO.deleteCompany(7);
        });

        Company company = companiesDBDAO.getOneCompany(9);
        assertNull(company);
    }

    @Test
    void getAllCompanies() throws SQLException {
        assertNotNull(companiesDBDAO.getAllCompanies());

        for (Company company : companiesDBDAO.getAllCompanies()) {
            System.out.println(company.toString());
        }
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

        System.out.println(companiesDBDAO.getOneCompany(2).toString());
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
    void updateCompany() throws SQLException {
        String email = "emailChangeAt:5/8/22";
        String password = "emailChangeAt:5/8/22";
        int id = 2;

        assertDoesNotThrow(() -> {
            companiesDBDAO.updateCompany(companiesDBDAO.getOneCompany(id), email, password);
        });


        assertTrue(email.equals(companiesDBDAO.getOneCompany(id).getEmail()));
        assertTrue(password.equals(companiesDBDAO.getOneCompany(id).getPassword()));


    }
}