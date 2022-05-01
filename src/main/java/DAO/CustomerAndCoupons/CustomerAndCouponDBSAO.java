package DAO.CustomerAndCoupons;

import firstStep.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerAndCouponDBSAO {

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

    public void addCouponsAndCustomer(int customerId , int couponId) {
        String sql = "insert into customerandcoupons (customerId, companyId) values ('" +
               customerId + "', '" + couponId + "')";
    }


}
