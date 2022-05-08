package DAO.DAOCoupons;

import Users.Company;
import firstStep.Coupon;
import org.junit.jupiter.api.Test;

import javax.crypto.spec.PSource;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CouponsDBDAOTest {
    CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
    Coupon coupon = new Coupon
            ("nameForSql", "descripExample", 49, 5,
                    5.5, "categoryExample", new Date(System.currentTimeMillis()), "exampleImageUrL");


    @Test
    void addCoupon() throws SQLException {

        assertDoesNotThrow(() -> {
            couponsDBDAO.addCoupon(coupon);
        });

        assertThrows(NullPointerException.class, () -> {
            couponsDBDAO.addCoupon(null);
        });

    }


    @Test
    void addCouponPurchase() throws SQLException {
        assertDoesNotThrow(() -> {
            couponsDBDAO.addCouponPurchase(1, 21);
        });

    }

    @Test
    void deleteAllCouponsByCompany() {
        assertDoesNotThrow(() -> {
            couponsDBDAO.deleteAllCouponsByCompany(3);
        });

    }


    @Test
    void deleteCoupon() throws SQLException {

        assertDoesNotThrow(() -> {
            couponsDBDAO.deleteCoupon(20);
        });
    }

    @Test
    void deleteAllPurchasesForOneCoupon() throws SQLException {

        assertDoesNotThrow(() -> {
            couponsDBDAO.deleteAllPurchasesForOneCoupon(15);
        });
    }

    @Test
    void deleteAllPurchasesForOneCustomer() throws SQLException {


        assertDoesNotThrow(() -> {
            couponsDBDAO.deleteAllPurchasesForOneCustomer(1);
        });
    }

    @Test
    void deleteCouponPurchase() throws SQLException {

        assertDoesNotThrow(() -> {
            couponsDBDAO.deleteCouponPurchase(99, 2);
        });

    }

    @Test
    void getAllCoupons() throws SQLException {

        assertNotNull(couponsDBDAO.getAllCoupons());

        for (Coupon coupon :
                couponsDBDAO.getAllCoupons()
        ) {
            System.out.println(coupon.toString());
        }
        System.out.println();

    }


    @Test
    void getAllCouponsOfCompany() throws SQLException {

        assertNotNull(couponsDBDAO.getAllCouponsOfCompany(20));

        for (Coupon coupon :
                couponsDBDAO.getAllCouponsOfCompany(99)
        ) {
            System.out.println(coupon.toString());
        }

    }

    @Test
    void getAllCouponsByCustomer() throws SQLException {
        assertNotNull(couponsDBDAO.getAllCouponsByCustomer(1));
        assertTrue(couponsDBDAO.getAllCouponsByCustomer(3).size() <= 0);
    }

    @Test
    void getCouponIdByCouponName() throws SQLException {
        int i = couponsDBDAO.getCouponIdByCouponName("nameForSql");
        assertTrue(i == 25);
    }

    @Test
    void getCouponsOfCompanyByCategory() {
    }

    @Test
    void getCouponsOfCompanyByMaxPrice() {
    }

    @Test
    void getCouponsOfCustomerByCategory() {
    }

    @Test
    void getCouponsOfCustomerByMaxPrice() {
    }

    @Test
    void getOneCoupon() {
    }

    @Test
    void isExpired() {
    }

    @Test
    void isThisCouponExist() {
    }

    @Test
    void isThisPurchaseExist() {
    }

    @Test
    void setAmount() {
    }

    @Test
    void updateCouponDescription() {
    }

    @Test
    void updateCouponEndDate() {
    }

    @Test
    void updateCouponPrice() {
    }
}