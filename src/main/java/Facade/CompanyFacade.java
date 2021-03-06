package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import firstStep.Coupon;
import firstStep.SystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        if (couponsDBDAO.isThisCouponExistForThisCompany(coupon.getCouponName(), this.companyId)) {
            throw new SystemException("This coupon is already exist");
        }
        if(!companiesDBDAO.isThisMailExist(companiesDBDAO.getOneCompany(coupon.getCompanyId()).getEmail())){
            throw new SystemException("This company isnt exist");
        }
        if(coupon.getEndDate().getTime()<System.currentTimeMillis()){
            throw new SystemException("The end date already pass");
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

    public List<Coupon> getCompanyCouponsByCategory(int categoryId) throws SystemException, SQLException {
        List<Coupon> allCoupons = couponsDBDAO.getAllCouponsOfCompany(this.companyId);
//        List<Coupon> relevantCoupons = new ArrayList<>();
//        for (Coupon coupon : allCoupons) {
//            if(coupon.getCategoryId() == categoryId){
//                relevantCoupons.add(coupon);
//            }
//        }
        allCoupons = allCoupons.stream().filter(coupon -> coupon.getCategoryId() == categoryId).collect(Collectors.toList());
        if (allCoupons.size() == 0){
            throw new SystemException("You dont have relevant coupons");
        }
        return allCoupons;
    }

    public List<Coupon> getCompanyCouponsByMaxPrice(int maxPrice) throws SystemException, SQLException {
        List<Coupon> allCoupons = getAllCompanyCoupons();
//        List<Coupon> relevantCoupons = new ArrayList<>();
//        for (Coupon coupon : allCoupons) {
//            if(coupon.getPrice() <= maxPrice){
//                relevantCoupons.add(coupon);
//            }
//        }
        allCoupons = allCoupons.stream().filter(coupon -> coupon.getPrice() <= maxPrice).collect(Collectors.toList());
        if (allCoupons.size() == 0){
            System.out.println("You dont have relevant coupons");
        }
        return allCoupons;
    }

    public void getCompanyDetails() throws SQLException {
        System.out.println(companiesDBDAO.getOneCompany(this.companyId).toString());
    }

    public void setCompanyId(String email) throws SQLException {
        this.companyId = companiesDBDAO.getCompanyId(email);
    }

    public void setCompanyId(int companyId) throws SQLException {
        this.companyId = companyId;
    }
}
