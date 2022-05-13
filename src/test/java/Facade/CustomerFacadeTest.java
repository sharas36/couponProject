package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import Users.Customer;
import firstStep.Coupon;
import firstStep.SystemException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFacadeTest {

    CustomerFacade customerFacade = new CustomerFacade(new CompaniesDBDAO(), new CouponsDBDAO(), new CustomersDBDAO());

    @Test
    void login() throws SystemException, SQLException {
        assertTrue(customerFacade.login("0@gmail.com", "27581622a"));


        assertThrows(SystemException.class, () -> {
            customerFacade.login("0@gmail.com", "64474223a");
        });
        assertThrows(SystemException.class, () -> {
            customerFacade.login("0@gm", "27581622a");
        });
    }

    @Test
    void purchaseCoupon() {
        assertThrows(SystemException.class, () -> {
            customerFacade.purchaseCoupon(40);
        });
        assertThrows(SystemException.class, () -> {
            customerFacade.purchaseCoupon(107);
        });
    }

    @Test
    void getAllCustomersCoupons() throws SQLException, SystemException {
        int customerId = 7;
        this.customerFacade.setCustomerId(customerId);
        List<Coupon> couponList = this.customerFacade.getAllCustomersCoupons();
        System.out.println(couponList.size());
        for (Coupon coupon : couponList){
            System.out.println(coupon.toString());
        }
//        System.out.println(couponList.get(0));
//        System.out.println(couponList.size());
//        assertTrue(couponList.size() == 9);
//        customerId = 6;
//        couponList = customerFacade.getAllCustomersCoupons();
//        assertTrue(couponList.isEmpty());

    }


    @Test
    void getCustomersCouponsOfCategory() {
    }

    @Test
    void getCustomersCouponsByMaxPrice() {
    }

    @Test
    void getCustomerDetails() {
    }
}