package DAO.DAOCoupons;

import DAO.DAOCompanies.CompaniesDBDAO;
import Users.Company;
import firstStep.ConnectionPool;
import firstStep.Coupon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponsDBDAO implements CouponsDAO {

    private ConnectionPool connectionPool = ConnectionPool.getInstanse();
    private PreparedStatement preparedStatement;
    private ResultSet resultset;
    private CompaniesDBDAO companiesDBDAO;
    private static Object lock = new Object();

    public void addCoupon(Coupon coupon) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "insert into coupons (companyId, categoryName, " + // here we change to categoryName -- have to change it in the sql as well.
                "couponName, description, startDate ,endDate, amount, price, image) values (?,?,?,?,?,?,?,?,?)";
        synchronized (lock) {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, coupon.getCompanyId());
            preparedStatement.setString(2, coupon.getCategory());
            preparedStatement.setString(3, coupon.getCouponName());
            preparedStatement.setString(4, coupon.getDescription());
            preparedStatement.setDate(5, coupon.getStartDate());
            preparedStatement.setDate(6, coupon.getEndDate());
            preparedStatement.setInt(7, coupon.getAmount());
            preparedStatement.setDouble(8, coupon.getPrice());
            preparedStatement.setString(9, coupon.getImageURL());

            preparedStatement.execute();
            coupon.setCouponId(this.getCouponIdByCouponName(coupon.getCouponName()));
            coupon.setCouponId(getCouponIdByCouponName(coupon.getCouponName()));
        }
        connectionPool.restoreConnection(connection);

    }

    public void updateCouponPrice(Coupon coupon, double price) throws SQLException {

        Connection connection = connectionPool.getConnection();

        String sql = "update coupons set price ='" + price + "' where couponId ='" + coupon.getCouponId() + "'";

        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }

        connectionPool.restoreConnection(connection);

    }

    public void deleteCoupon(int couponId) throws SQLException {

        Connection connection = connectionPool.getConnection();

        String sql = "update coupons set deleted = " + 1 + " where id = '" + couponId + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }

        connectionPool.restoreConnection(connection);

    }

    public ArrayList<Coupon> getAllCoupons() throws SQLException {
        Connection connection = connectionPool.getConnection();

        ArrayList<Coupon> coupons = new ArrayList<>();
        String sql = "select * from coupons where deleted = " + 0;
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            CompaniesDBDAO companiesDBDAO;
            while (resultSet.next()) {
                int couponId = resultSet.getInt("name");
                int companyId = resultSet.getInt("name");
                String categoryName = resultSet.getString("email");
                String couponName = resultSet.getString("password");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                int amount = resultSet.getInt("amount");
                double price = resultset.getDouble("price");
                String image = resultSet.getString("image");

                companiesDBDAO = new CompaniesDBDAO();
                Company company = companiesDBDAO.getOneCompany(companyId);

                coupons.add(new Coupon(couponName, description, companyId,
                        amount, price, categoryName, startDate, endDate, image));
            }
        }
        connectionPool.restoreConnection(connection);
        return coupons;

    }

    @Override
    public void deleteAllCouponsByCompany(int companyId) throws SQLException {

        Connection connection = connectionPool.getConnection();

        String sql = "update coupons set deleted = " + 1 + " where companyId = '" + companyId + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        }

        connectionPool.restoreConnection(connection);

    }

    public Coupon getOneCoupon(int couponId) throws SQLException {

        Connection connection = connectionPool.getConnection();
        String sql = "select from coupons where id = '" + couponId + "'" + " and deleted = " + 0;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        Coupon coupon = null;

        synchronized (lock) {
            while (resultSet.next()) {
                int idCoupon = resultSet.getInt("couponId");
                int companyId = resultSet.getInt("companyId");
                String categoryName = resultSet.getString("categoryName");
                String couponName = resultSet.getString("couponName");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                int amount = resultSet.getInt("amount");
                double price = resultset.getDouble("price");
                String image = resultSet.getString("image");
                coupon = new Coupon(couponName, description, companyId,
                        amount, price, categoryName, startDate, endDate, image);

            }
        }
        return coupon;
    }

    @Override
    public boolean addCouponPurchase(int couponId, int customerId) {
        return false;
    }


    public void deleteCouponPurchase(int couponId, int customerId) {

    }

    @Override
    public boolean isThisCouponExist(String couponName) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "select * from coupons where couponName = '" + couponName + "'" + " and deleted = " + 0;
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultset = preparedStatement.executeQuery();
        }
        connectionPool.restoreConnection(connection);

        if (resultset.next()) {
            return true;
        }
        return false;
    }

    public int getCouponIdByCouponName(String couponName) throws SQLException {

        Connection connection = connectionPool.getConnection();
        String sql = "select * from coupons where couponName = '" + couponName + "'" + " and deleted = " + 0;
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultset = preparedStatement.executeQuery();
        }
        int id = 0;
        while (resultset.next()) {
            id = resultset.getInt("couponId");
        }

        connectionPool.restoreConnection(connection);
        return id;

    }

    public boolean getExpired() throws SQLException {
        Connection connection = connectionPool.getConnection();
        Date date = new Date(System.currentTimeMillis());
        String sql = "select * from coupons where endDate <= '" + date + "'";
        ResultSet resultSet;
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.getResultSet();
        }
        if (resultset.first()) {
            sql = "update coupons set deleted = " + 1 + " where endDate <= '" + date + "'";
            synchronized (lock) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
            }
        }
        connectionPool.restoreConnection(connection);
        return resultSet.first();
    }

    public void restoreAllDeletedCoupons() throws SQLException {

        Connection connection = connectionPool.getConnection();

        String sql = "update coupons set deleted = " + 0 + " where deleted = " + 1;
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }

        connectionPool.restoreConnection(connection);
    }
}