package DAO.CustomerAndCoupons;

import firstStep.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerAndCoupon {

    private ConnectionPool connectionPool = ConnectionPool.getInstanse();
    private PreparedStatement preparedStatement;
    private ResultSet resultset;

    private Object lock = new Object();


    public boolean isCustomerAndCouponExist(int customerId, int couponsId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from customerandcoupons where customerId ='" + customerId + "' and couponId = '" + couponsId + "'";

        synchronized (lock) {
            resultset = preparedStatement.executeQuery(sql);
            connectionPool.restoreConnection(connection);
        }

        return resultset.first();
    }

    public void addCouponsAndCustomer(int customerId, int couponId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "insert into customerandcoupons (customerId, companyId) values ('" +
                customerId + "', '" + couponId + "')";

        synchronized (lock) {
            resultset = preparedStatement.executeQuery(sql);
            connectionPool.restoreConnection(connection);
        }
    }

    public void deleteCouponPurchase(int couponId, int customerId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "delete from customerandcoupons where customeId = '" + customerId + "'  and couponId = '" + couponId + "'";

        synchronized (lock) {
            resultset = preparedStatement.executeQuery(sql);
            connectionPool.restoreConnection(connection);
        }

    }


}
