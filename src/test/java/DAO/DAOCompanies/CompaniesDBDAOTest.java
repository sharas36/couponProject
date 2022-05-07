package DAO.DAOCompanies;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CompaniesDBDAOTest {
    CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();

    @Test
    void isCompanyExist() throws SQLException {

        boolean isExist = companiesDBDAO.loginCheck("rr", "fef");
        assertTrue(isExist);
    }

    @Test
    void isThisMailExist() {
    }

    @Test
    void isThisNameExist() {
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
    void getCompanyId() {
    }

    @Test
    void getCompanyIdByEmail() {
    }
}