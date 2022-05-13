package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import firstStep.Coupon;
import firstStep.SystemException;

import java.sql.SQLException;
import java.util.List;

public class CompanyFacade extends MainFacade {

    private int companyId;


    public CompanyFacade(CompaniesDBDAO companiesDBDAO, CouponsDBDAO couponsDBDAO, CustomersDBDAO customersDBDAO) {
        super(companiesDBDAO, couponsDBDAO, customersDBDAO);
    }


    @Override
    public Boolean login(String email, String password) throws SQLException, SystemException {
        if (companiesDBDAO.loginCheck(email, password)) {
            this.setCompanyId(email);
            return true;
        }
        if (!customersDBDAO.isThisMailExist(email)) {
            throw new SystemException("This email isnt exist!. please try again");
        }
        throw new SystemException("The password isnt match the email! please try again");
    }

    public void addCoupon(Coupon coupon) throws SQLException, SystemException {
        if (couponsDBDAO.isThisCouponExist(coupon.getCouponName())) {
            throw new SystemException("This coupon is already exist");
        }
        if(!companiesDBDAO.isThisMailExist(companiesDBDAO.getOneCompany(coupon.getCompanyId()).getEmail())){
            throw new SystemException("This company isnt exist");
        }
        couponsDBDAO.addCoupon(coupon);
        companiesDBDAO.getOneCompany(this.companyId).addCoupon(coupon);
    }

    public void updateCouponPrice(Coupon coupon, double price) throws SQLException, SystemException {
        if (!couponsDBDAO.isThisCouponExist(coupon.getCouponName())) {
            throw new SystemException("This coupon isnt exist");
        }
        couponsDBDAO.updateCouponPrice(coupon.getCouponId(), price);
    }

    public void deleteCoupon(int couponId) throws SQLException, SystemException {
        if (!couponsDBDAO.isThisCouponExist(couponsDBDAO.getOneCoupon(couponId).getCouponName())) {
            throw new SystemException("This coupon isnt exist");
        }
        couponsDBDAO.deleteCoupon(couponId);
        couponsDBDAO.deleteAllPurchasesForOneCoupon(couponId);
    }

    public List<Coupon> getAllCompanyCoupons() throws SQLException, SystemException {
        List<Coupon> coupons = couponsDBDAO.getAllCouponsOfCompany(this.companyId);
        if(coupons.size() == 0){
            throw new SystemException("You dont have any coupons");
        }
        return coupons;
    }

    public List<Coupon> getCompanyCouponsByCategory(int categoryId){

        return null;
    }

    public List<Coupon> getCompanyCouponsByMaxPrice(int maxPrice) throws SystemException, SQLException {
        List<Coupon> allCoupons = getAllCompanyCoupons();
        for (Coupon coupon : allCoupons) {
            if(coupon.getPrice() > maxPrice){
                allCoupons.remove(coupon);
            }
        }
        return allCoupons;
    }

    public void getCompanyDetails() throws SQLException {
        System.out.println(companiesDBDAO.getOneCompany(this.companyId).toString());
    }

    public void setCompanyId(String email) throws SQLException {
        this.companyId = companiesDBDAO.getCompanyId(email);
    }
}
