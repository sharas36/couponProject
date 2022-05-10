package DAO.DAOCoupons;

import Users.Company;
import firstStep.ConnectionPool;
import firstStep.Coupon;
import org.junit.jupiter.api.Test;

import javax.crypto.spec.PSource;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CouponsDBDAOTest {
    CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
    Coupon coupon = new Coupon
            ("nameForSql", "descripExample", 49, 5,
                    5.5, 2, new Date(System.currentTimeMillis()), "exampleImageUrL");


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
            couponsDBDAO.addCouponPurchase(1, 33);
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
    void getCouponsOfCompanyByCategory() throws SQLException {
        assertTrue(couponsDBDAO.getCouponsOfCompanyByCategory(99, 1).size() > 0);

        for (Coupon coupon :
                couponsDBDAO.getCouponsOfCompanyByCategory(99, 1)
        ) {
            System.out.println(coupon.toString());
        }
    }

    @Test
    void getCouponsOfCompanyByMaxPrice() throws SQLException {
        couponsDBDAO.getCouponsOfCompanyByMaxPrice(49, 4.5);
        assertTrue(couponsDBDAO.getCouponsOfCompanyByMaxPrice(49, 4.5).size() > 0);

        for (Coupon coupon :
                couponsDBDAO.getCouponsOfCompanyByMaxPrice(49, 4.5)) {
            System.out.println(coupon.toString());
        }
    }

    @Test
    void getCouponsOfCustomerByCategory() throws SQLException {
        ArrayList<Coupon> coupons = couponsDBDAO.getCouponsOfCustomerByCategory(1, 0);
        assertTrue(coupons.size() >= 1);


        for (int i = 0; i < coupons.size(); i++) {
            System.out.println(coupons.get(i));
        }

    }


    @Test
    void getCouponsOfCustomerByMaxPrice() {
    }

    @Test
    void getOneCoupon() throws SQLException {
        assertNotNull(couponsDBDAO.getOneCoupon(22));

    }

    @Test
    void isExpired() {
    }

    @Test
    void isThisCouponExist() {
    }

    @Test
    void isThisPurchaseExist() throws SQLException {
        assertTrue(couponsDBDAO.isThisPurchaseExist(1, 89));
    }

    @Test
    void setAmount() throws SQLException {
        Coupon coupon = couponsDBDAO.getOneCoupon(24);
        int i = coupon.getAmount();
        System.out.println(i);
        int number = 5;
        couponsDBDAO.setAmount(number, 24);
        Coupon coupon2 = couponsDBDAO.getOneCoupon(24);
        int x = coupon2.getAmount();
        assertTrue(x - i == 5);


    }

    @Test
    void updateCouponDescription() throws SQLException {
        Coupon coupon = couponsDBDAO.getOneCoupon(23);
        String prevText = coupon.getDescription();
        couponsDBDAO.updateCouponDescription(23, "cvdfbgrr");
        Coupon coupon2 = couponsDBDAO.getOneCoupon(23);
        String nextText = coupon2.getDescription();
        System.out.println(prevText + " " + nextText);

    }

    @Test
    void updateCouponEndDate() throws SQLException {

        Coupon coupon = couponsDBDAO.getOneCoupon(23);
        Date date = coupon.getEndDate();

        couponsDBDAO.updateCouponEndDate(23, new Date(System.currentTimeMillis() + 86400000 * 2));
        Coupon coupon2 = couponsDBDAO.getOneCoupon(23);
        System.out.println(date + "" + coupon2.getEndDate());
        assertTrue(!date.equals(coupon2.getEndDate()));
    }

    @Test
    void updateCouponPrice() throws SQLException {
        int id = 24;
        double price = 25;
        couponsDBDAO.updateCouponPrice(id, price);
        Coupon coupon3 = couponsDBDAO.getOneCoupon(id);
        assertTrue(price == coupon3.getPrice());
    }
}

