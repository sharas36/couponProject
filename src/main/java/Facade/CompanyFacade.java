package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import firstStep.Coupon;
import firstStep.systemException;

import java.sql.SQLException;

public class CompanyFacade extends MainFacade {

    private int companyId;

    public CompanyFacade(CompaniesDBDAO companiesDBDAO, CouponsDBDAO couponsDBDAO, CustomersDBDAO customersDBDAO) {
        super(companiesDBDAO, couponsDBDAO, customersDBDAO);
    }

    @Override
    public Boolean login(String email, String password) throws SQLException, systemException {
        if (companiesDBDAO.isCompanyExist(email, password)) {
            this.setCompanyId(email);
            return true;
        }
        if (!customersDBDAO.isThisMailExist(email)) {
            throw new systemException("This email isnt exist!. please try again");
        }
        throw new systemException("The password isnt match the email! please try again");
    }

    public void addCoupon(Coupon coupon) throws SQLException, systemException {
        if(couponsDBDAO.isThisCouponExist(coupon.getCouponName())){
            throw new systemException("This coupon is already exist");
        }
        couponsDBDAO.addCoupon(coupon);
        companiesDBDAO.getOneCompany(coupon.getCompanyId()).addCoupon(coupon);
    }




    public void setCompanyId(String email) throws SQLException {
        this.companyId = companiesDBDAO.getCompanyId(email);
    }
}
