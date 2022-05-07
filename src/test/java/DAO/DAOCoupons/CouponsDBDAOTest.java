package DAO.DAOCoupons;

import firstStep.Coupon;
import MainWork.Main;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CouponsDBDAOTest {
//    CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
//    Coupon coupon = Main.getCoupon();


    @Test
    void addCoupon() throws SQLException {
// here is two ways to check if is good. each one them is getting supplier and inside if him
// we implement the function we want

// assertThrows(SQLException.class, () -> {
//  couponsDBDAO.addCoupon(coupon);
//        });

        assertDoesNotThrow(() -> couponsDBDAO.addCoupon(coupon));


    }

    @Test
    void updateCouponPrice() {

    }

    @Test
    void deleteCoupon() {
    }

    @Test
    void getAllCoupons() {
    }

    @Test
    void deleteAllCouponsByCompany() {
    }

    @Test
    void getOneCoupon() throws SQLException {
//        Coupon coupon = couponsDBDAO.getOneCoupon(2);
//        assertNotNull(coupon);
//    }

    @Test
    void addCouponPurchase() {
    }

    @Test
    void deleteCouponPurchase() {
    }

    @Test
    void isThisCouponExist() {
    }

    @Test
    void getCouponIdByCouponName() {
    }

    @Test
    void getLock() {
    }
}