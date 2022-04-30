package DAO.DAOCoupons;

import Users.Company;
import firstStep.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public interface CouponsDAO {

    public void addCoupon(Coupon coupon) throws SQLException;
    public void updateCouponPrice(Coupon coupon, double price) throws SQLException;
    public void deleteCoupon(int couponId) throws SQLException;
    public ArrayList<Coupon> getAllCoupons() throws SQLException;
    public void deleteAllCouponsByCompany(int companyId) throws SQLException;
    public Coupon getOneCoupon(int couponId) throws SQLException;
    public boolean addCouponPurchase(int couponId, int customerId);
    public void deleteCouponPurchase(int couponId, int customerId);
    public boolean isThisCouponExist(String couponName) throws SQLException;
    public boolean getExpired() throws SQLException;
    public void restoreAllDeletedCoupons() throws SQLException;
    public List<Coupon> getAllCouponsByCustomer(int customerId);


}