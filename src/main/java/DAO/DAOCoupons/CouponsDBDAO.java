package DAO.DAOCoupons;

import firstStep.ConnectionPool;
import firstStep.Coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CouponsDBDAO implements CouponsDAO {

    private ConnectionPool connectionPool = ConnectionPool.getInstanse();
    private PreparedStatement preparedStatement;
    private ResultSet resultset;

    public void addCoupon(Coupon coupon) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "insert into coupons (companyId, categoryId, couponName, description, startDate ,endDate, amount, price, image) values ('" +
                coupon.getCompany().getCompanyId()
                + "', '" + coupon.getCategory(). + "', '" + company.getPassword() + "')   ";

        connectionPool.restoreConnection(connection);
    }

    public void updateCouponPrice(Coupon coupon, double price) throws SQLException {

    }

    public void deleteCoupon(int couponId) throws SQLException {

    }

    public ArrayList<Coupon> getAllCoupons() throws SQLException {
        return null;
    }

    public Coupon getOneCompany(int couponId) throws SQLException {
        return null;
    }

    public void addCouponPurchase(int couponId, int customerId) {

    }

    public void deleteCouponPurchase(int couponId, int customerId) {

    }
}
