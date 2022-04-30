package DAO.DAOCompanies;

import java.sql.SQLException;
import java.util.ArrayList;

import Users.Company;

public interface CompaniesDAO {

    public Boolean isCompanyExist(String email, String password) throws SQLException;

    public void addCompany(Company company) throws SQLException;

    public void updateCompany(Company company, String email, String password) throws SQLException;

    public void deleteCompany(int companyId) throws SQLException;

    public ArrayList<Company> getAllCompanies() throws SQLException;

    public Company getOneCompany(int companyId) throws SQLException;

    public int getCompanyId(String email) throws SQLException;

    public boolean isThisMailExist(String email) throws SQLException;

    public boolean isThisNameExist(String companyName) throws SQLException;

    public void restoreDeletedCompany(String email) throws SQLException;

    public void restoreAllDeletedCompanies() throws SQLException;

}
