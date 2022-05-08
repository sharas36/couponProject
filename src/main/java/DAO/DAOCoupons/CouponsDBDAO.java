package DAO.DAOCoupons;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import Users.Company;
import Users.Customer;
import firstStep.ConnectionPool;
import firstStep.Coupon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponsDBDAO implements CouponsDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstanse();
    private PreparedStatement preparedStatement;
    private ResultSet resultset;
    private CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
    private CustomersDBDAO customersDBDAO = new CustomersDBDAO();
    private static final Object lock = new Object();


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

    @Override
    public boolean addCouponPurchase(int customerId, int couponId) throws SQLException {
        Connection connection = connectionPool.getConnection();

        Coupon coupon = getOneCoupon(couponId);
        Customer customer = customersDBDAO.getCustomer(customerId);
        customer.setCustomerId(customerId);

        if (coupon.getAmount() > 0 && customer != null && !isThisPurchaseExist(couponId, customerId)) {
            String sql = "insert into customerandcoupons (customerId, couponId,companyId) values (?, ?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setInt(2, coupon.getCouponId());
            preparedStatement.setInt(3, coupon.getCompanyId());


            synchronized (lock) {
                preparedStatement.execute();
                setAmount(-1, couponId);
            }

            return true;
        }

        return false;
    }

    @Override
    public void deleteAllCouponsByCompany(int companyId) throws SQLException {

        Connection connection = connectionPool.getConnection();

        String sql = "delete from coupons where companyId = '" + companyId + "'";
        preparedStatement = connection.prepareStatement(sql);

        synchronized (lock) {
            preparedStatement.execute();
        }

        connectionPool.restoreConnection(connection);

    }

    public void deleteCoupon(int couponId) throws SQLException {

        Connection connection = connectionPool.getConnection();

        String sql = "delete from coupons where couponId = '" + couponId + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }

        connectionPool.restoreConnection(connection);

    }


    @Override
    public void deleteAllPurchasesForOneCoupon(int couponId) throws SQLException {

        Connection connection = connectionPool.getConnection();
        String sql = "delete from customerandcoupons where couponId = '" + couponId + "'";
        preparedStatement = connection.prepareStatement(sql);
        synchronized (lock) {
            preparedStatement.execute();
            connectionPool.restoreConnection(connection);
        }
    }

    public void deleteAllPurchasesForOneCustomer(int customerId) throws SQLException {

        Connection connection = connectionPool.getConnection();
        String sql = "delete from customerandcoupons where customerId = '" + customerId + "'";
        preparedStatement = connection.prepareStatement(sql);

        synchronized (lock) {
            preparedStatement.execute();
            connectionPool.restoreConnection(connection);
        }
    }

    public void deleteCouponPurchase(int couponId, int customerId) throws SQLException {

        Connection connection = connectionPool.getConnection();
        String sql = "delete from customerandcoupons where customerId = '" + customerId + "'  and couponId = '" + couponId + "'";
        preparedStatement = connection.prepareStatement(sql);


        synchronized (lock) {
            preparedStatement.execute();
            connectionPool.restoreConnection(connection);
        }
    }

    public ArrayList<Coupon> getAllCoupons() throws SQLException {
        Connection connection = connectionPool.getConnection();

        ArrayList<Coupon> coupons = new ArrayList<>();
        String sql = "select * from coupons";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            CompaniesDBDAO companiesDBDAO;
            while (resultSet.next()) {
                int couponId = resultSet.getInt("couponId");
                int companyId = resultSet.getInt("companyId");
                String categoryName = resultSet.getString("categoryName");
                String couponName = resultSet.getString("couponName");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                int amount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");
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
    public ArrayList<Coupon> getAllCouponsOfCompany(int companyId) throws SQLException {
        Connection connection = connectionPool.getConnection();

        ArrayList<Coupon> coupons = new ArrayList<>();
        String sql = "select * from coupons where companyId = '" + companyId + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int couponId = resultSet.getInt("couponId");
                String categoryName = resultSet.getString("categoryName");
                String couponName = resultSet.getString("couponName");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                int amount = resultSet.getInt("amount");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");
                coupons.add(new Coupon(couponName, description, companyId,
                        amount, price, categoryName, startDate, endDate, image));
            }
        }
        connectionPool.restoreConnection(connection);
        return coupons;
    }

    public List<Coupon> getAllCouponsByCustomer(int customerId) throws SQLException {//To be done!!!!!!

        Connection connection = connectionPool.getConnection();

        ArrayList<Coupon> coupons = new ArrayList<>();
        String sql = "select * from customerandcoupons where customerId = '" + customerId + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            this.resultset = preparedStatement.executeQuery();
        }
        while (this.resultset.next()) {
            coupons.add(getOneCoupon(this.resultset.getInt("couponId")));
        }
        connectionPool.restoreConnection(connection);
        return coupons;
    }

    public int getCouponIdByCouponName(String couponName) throws SQLException {

        Connection connection = connectionPool.getConnection();
        String sql = "select * from coupons where couponName = '" + couponName + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultset = preparedStatement.executeQuery();
        }
        int id = 0;
        while (resultset.next()) {
            id = resultset.getInt("couponId");
            return id;
        }

        connectionPool.restoreConnection(connection);
        return id;

    }

    @Override
    public ArrayList<Coupon> getCouponsOfCompanyByCategory(int companyId, int categoryId) throws SQLException {
        Connection connection = connectionPool.getConnection();

        ArrayList<Coupon> coupons = new ArrayList<>();
        String sql = "select * from coupons where companyId = '" + companyId + "' and categoryId = '" + categoryId + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int couponId = resultSet.getInt("name");
                String categoryName = resultSet.getString("email");
                String couponName = resultSet.getString("password");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                int amount = resultSet.getInt("amount");
                double price = resultset.getDouble("price");
                String image = resultSet.getString("image");
                coupons.add(new Coupon(couponName, description, companyId,
                        amount, price, categoryName, startDate, endDate, image));
            }
        }
        connectionPool.restoreConnection(connection);
        return coupons;
    }

    @Override
    public ArrayList<Coupon> getCouponsOfCompanyByMaxPrice(int companyId, double maxPrice) throws SQLException {
        Connection connection = connectionPool.getConnection();

        ArrayList<Coupon> coupons = new ArrayList<>();
        String sql = "select * from coupons where companyId = '" + companyId + "' and price <= '" + maxPrice + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int couponId = resultSet.getInt("name");
                String categoryName = resultSet.getString("email");
                String couponName = resultSet.getString("password");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                int amount = resultSet.getInt("amount");
                double price = resultset.getDouble("price");
                String image = resultSet.getString("image");
                coupons.add(new Coupon(couponName, description, companyId,
                        amount, price, categoryName, startDate, endDate, image));
            }
        }
        connectionPool.restoreConnection(connection);
        return coupons;
    }

    @Override
    public ArrayList<Coupon> getCouponsOfCustomerByCategory(int customerId, int categoryId) throws SQLException {
        Connection connection = connectionPool.getConnection();

        ArrayList<Coupon> coupons = new ArrayList<>();
        String sql = "select * from coupons where customerId = '" + customerId + "' and categoryId = '" + categoryId + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int couponId = resultSet.getInt("name");
                String categoryName = resultSet.getString("email");
                String couponName = resultSet.getString("password");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                int amount = resultSet.getInt("amount");
                double price = resultset.getDouble("price");
                String image = resultSet.getString("image");
                coupons.add(new Coupon(couponName, description, customerId,
                        amount, price, categoryName, startDate, endDate, image));
            }
        }
        connectionPool.restoreConnection(connection);
        return coupons;
    }

    @Override
    public ArrayList<Coupon> getCouponsOfCustomerByMaxPrice(int customerId, double maxPrice) throws SQLException {
        Connection connection = connectionPool.getConnection();

        ArrayList<Coupon> coupons = new ArrayList<>();
        String sql = "select * from coupons where customerId = '" + customerId + "' and price <= '" + maxPrice + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int couponId = resultSet.getInt("name");
                String categoryName = resultSet.getString("email");
                String couponName = resultSet.getString("password");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                int amount = resultSet.getInt("amount");
                double price = resultset.getDouble("price");
                String image = resultSet.getString("image");
                coupons.add(new Coupon(couponName, description, customerId,
                        amount, price, categoryName, startDate, endDate, image));
            }
        }
        connectionPool.restoreConnection(connection);
        return coupons;
    }

    public Coupon getOneCoupon(int couponId) throws SQLException {

        Connection connection = connectionPool.getConnection();
        String sql = "select * from coupons where couponId = '" + couponId + "'";
        Coupon coupon = null;

        synchronized (lock) {

            preparedStatement = connection.prepareStatement(sql);
        }
        this.resultset = preparedStatement.executeQuery();
        while (this.resultset.next()) {
            int idCoupon = this.resultset.getInt("couponId");
            int companyId = this.resultset.getInt("companyId");
            String categoryName = this.resultset.getString("categoryName");
            String couponName = this.resultset.getString("couponName");
            String description = this.resultset.getString("description");
            Date startDate = this.resultset.getDate("startDate");
            Date endDate = this.resultset.getDate("endDate");
            int amount = this.resultset.getInt("amount");
            double price = this.resultset.getDouble("price");
            String image = this.resultset.getString("image");
            coupon = new Coupon(couponName, description, companyId,
                    amount, price, categoryName, startDate, endDate, image);
            coupon.setCouponId(getCouponIdByCouponName(this.resultset.getString("couponName")));
            return coupon;

        }

        return coupon;
    }

    public boolean isExpired() throws SQLException {
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

    @Override
    public boolean isThisCouponExist(String couponName) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "select * from coupons where couponName = '" + couponName + "'";
        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultset = preparedStatement.executeQuery();
        }
        connectionPool.restoreConnection(connection);

        return resultset.next();
    }

    @Override
    public boolean isThisPurchaseExist(int couponId, int customerId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        String sql = "select * from customerandcoupons where customerId ='" + customerId + "' and couponId = '" + couponId + "'";

        preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);

        synchronized (lock) {

            resultset = preparedStatement.executeQuery();
            connectionPool.restoreConnection(connection);
        }

        return resultset.first();
    }

    public void setAmount(int add, int couponId) throws SQLException {


        Connection connection = connectionPool.getConnection();

        String sql = "select * from coupons where companyId = '" + couponId + "'";

        synchronized (lock) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            resultset = preparedStatement.getResultSet();
        }
        int oldAmount = 0;
        while (resultset.next()) {
            oldAmount = resultset.getInt("amount");
        }
//        int amount = oldAmount + add;
        int amount = oldAmount + add;

        sql = " update coupons set amount = '" + amount + "' where couponId = '" + couponId + "'";
        synchronized (lock) {
            preparedStatement = connection.prepareStatement(sql);
        }

        connectionPool.restoreConnection(connection);

    }

    @Override
    public void updateCouponDescription(Coupon coupon, String description) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "update coupons set description ='" + description + "' where couponId ='" + coupon.getCouponId() + "'";

        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }

        connectionPool.restoreConnection(connection);
    }

    @Override
    public void updateCouponEndDate(Coupon coupon, Date endDate) throws SQLException {
        Connection connection = connectionPool.getConnection();

        String sql = "update coupons set endDate ='" + endDate + "' where couponId ='" + coupon.getCouponId() + "'";

        synchronized (lock) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
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

}