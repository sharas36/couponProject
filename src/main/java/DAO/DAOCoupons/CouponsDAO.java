package DAO.DAOCoupons;

import Users.Company;
import firstStep.Coupon;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public interface CouponsDAO {

    public void addCoupon(Coupon coupon) throws SQLException;

    public boolean addCouponPurchase(int couponId, int customerId) throws SQLException;

    public void deleteAllCouponsByCompany(int companyId) throws SQLException;

    public void deleteAllPurchasesForOneCustomer(int customerId) throws SQLException;

    public void deleteAllPurchasesForOneCoupon(int couponId) throws SQLException;

    public void deleteCoupon(int couponId) throws SQLException;

    public void deleteCouponPurchase(int couponId, int customerId) throws SQLException;

    public ArrayList<Coupon> getAllCoupons() throws SQLException;

    public List<Coupon> getAllCouponsByCustomer(int customerId) throws SQLException;

    public ArrayList<Coupon> getAllCouponsOfCompany(int companyId) throws SQLException;

    public ArrayList<Coupon> getCouponsOfCompanyByCategory(int companyId, int categoryId) throws SQLException;

    public ArrayList<Coupon> getCouponsOfCompanyByMaxPrice(int companyId, double maxPrice) throws SQLException;

    public ArrayList<Coupon> getCouponsOfCustomerByCategory(int customerId, int categoryId) throws SQLException, InterruptedException;

    public List<Coupon> getCouponsOfCustomerByMaxPrice(int customerId, double maxPrice) throws SQLException;

    public Coupon getOneCoupon(int couponId) throws SQLException;

    public void filterExpiredCoupon() throws SQLException;

    public boolean isThisCouponExist(String couponName) throws SQLException;

    public boolean isThisPurchaseExist(int couponId, int customerId) throws SQLException;

    public void setAmount(int add, int couponsId) throws SQLException;

    public void updateCouponDescription(int couponId, String description) throws SQLException;

    public void updateCouponEndDate(int couponId, Date endDate) throws SQLException;

    public void updateCouponPrice(int couponId, double price) throws SQLException;

}