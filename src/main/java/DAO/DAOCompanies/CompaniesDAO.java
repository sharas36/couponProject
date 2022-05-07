package DAO.DAOCompanies;

import java.sql.SQLException;
import java.util.ArrayList;

import Users.Company;

public interface CompaniesDAO {

    public void addCompany(Company company) throws SQLException;

    public void deleteCompany(int companyId) throws SQLException;

    public ArrayList<Company> getAllCompanies() throws SQLException;

    public int getCompanyId(String email) throws SQLException;

    public Company getOneCompany(int companyId) throws SQLException;

    public boolean isThisMailExist(String email) throws SQLException;

    public boolean isThisNameExist(String companyName) throws SQLException;

    public Boolean loginCheck(String email, String password) throws SQLException;

    public void updateCompany(Company company, String email, String password) throws SQLException;

}
