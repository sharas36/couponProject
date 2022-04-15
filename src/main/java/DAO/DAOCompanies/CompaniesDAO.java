package DAO.DAOCompanies;

import java.sql.SQLException;
import java.util.ArrayList;

import company.Company;

public interface CompaniesDAO {

    public Boolean isCompanyExist(String email, String password) throws SQLException;

    public void addCompany(Company company) throws SQLException;

    public void updateCompany(Company company, String email, String password) throws SQLException;

    public void deleteCompany(int companyId) throws SQLException;

    public ArrayList<Company> getAllCompanies() throws SQLException;

    public Company getOneCompany(int companyId) throws SQLException;

    public int getCompanyIdByEmail(String email) throws SQLException;
}
