package DAO.CustomerAndCoupons;

import java.sql.SQLException;

public interface CustomerAndCouponDBDAO {

    public boolean isCustomerAndCouponExist(int customerId, int couponsId) throws SQLException;

    public void addCouponsAndCustomer(int customerId, int couponId) throws SQLException;

    public void deleteCouponPurchase(int couponId, int customerId) throws SQLException;
}
