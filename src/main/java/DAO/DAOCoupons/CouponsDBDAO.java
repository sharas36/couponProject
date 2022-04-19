package DAO.DAOCoupons;

import DAO.DAOCompanies.CompaniesDBDAO;
import Users.Company;
import firstStep.ConnectionPool;
import firstStep.Coupon;

import java.sql.*;
import java.util.ArrayList;

public class CouponsDBDAO implements CouponsDAO {

    private ConnectionPool connectionPool = ConnectionPool.getInstanse();
    private PreparedStatement preparedStatement;
    private ResultSet resultset;
    private CompaniesDBDAO companiesDBDAO;

    public void addCoupon(Coupon coupon) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "insert into coupons (companyId, categoryName, " + // here we change to categoryName -- have to change it in the sql as well.
                "couponName, description, startDate ,endDate, amount, price, image) values (?,?,?,?,?,?,?,?,?)";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, coupon.getCompanyId());
        preparedStatement.setString(2, coupon.getCategory().toString());
        preparedStatement.setString(3, coupon.getCouponName());
        preparedStatement.setString(4, coupon.getDescription());
        preparedStatement.setDate(5, coupon.getStartDate());
        preparedStatement.setDate(6, coupon.getEndDate());
        preparedStatement.setInt(7, coupon.getAmount());
        preparedStatement.setDouble(8, coupon.getPrice());
        preparedStatement.setString(9, coupon.getImageURL());

        preparedStatement.execute();
        coupon.setCouponId(this.getCouponIdByCouponName(coupon.getCouponName()));
        connectionPool.restoreConnection(connection);
        coupon.setCouponId(getCouponIdByCouponName(coupon.getCouponName()));


    }

    public void updateCouponPrice(Coupon coupon, double price) throws SQLException {

        Connection connection = connectionPool.getConnection();

        String sql = "update coupons set price ='" + price + "' where couponId ='" + coupon.getCouponId() + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();

        connectionPool.restoreConnection(connection);

    }

    public void deleteCoupon(int couponId) throws SQLException {

        Connection connection = connectionPool.getConnection();

        String sql = "delete from coupons where id = '" + couponId + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();

        connectionPool.restoreConnection(connection);

    }

    public ArrayList<Coupon> getAllCoupons() throws SQLException {
        Connection connection = connectionPool.getConnection();

        ArrayList<Coupon> coupons = new ArrayList<>();
        String sql = "select * from coupons";
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

        connectionPool.restoreConnection(connection);
        return coupons;

    }

    public Coupon getOneCoupon(int couponId) throws SQLException {

        Connection connection = connectionPool.getConnection();
        String sql = "select from coupons where id = '" + couponId + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        Coupon coupon = null;

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
        return coupon;
    }

    public void addCouponPurchase(int couponId, int customerId) {

    }

    public void deleteCouponPurchase(int couponId, int customerId) {

    }

    @Override
    public boolean isThisCouponExist(String couponName) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "select * from coupons where couponName = '" + couponName + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        connectionPool.restoreConnection(connection);
        if(rs.next()){
            return true;
        }
        return false;
    }

    public int getCouponIdByCouponName(String couponName) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from coupons where couponName = '" + couponName + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("couponId");
        }
        connectionPool.restoreConnection(connection);
        return id;

    }
}
