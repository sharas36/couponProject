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
        String sql = "insert into coupons (companyId, categoryName, " + // here we change to categoryName -- have to change it in the sql as well
                "couponName, description, startDate ,endDate, amount, price, image) values (?,?,?,?,?,?,?,?,?)";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,coupon.getCompany().getCompanyId());
        preparedStatement.setString(2,coupon.getCategory().toString());
        preparedStatement.setString(3,coupon.getCouponName());
        preparedStatement.setString(4,coupon.getDescription());
        preparedStatement.setDate(5,coupon.getStartDate());
        preparedStatement.setDate(6,coupon.getEndDate());
        preparedStatement.setInt(7,coupon.getAmount());
        preparedStatement.setDouble(8,coupon.getPrice());
        preparedStatement.setString(9,coupon.getImageURL());

        preparedStatement.execute();



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
