package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import firstStep.Coupon;
import firstStep.SystemException;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CompanyFacadeTest {

    CompanyFacade companyFacade = new CompanyFacade(new CompaniesDBDAO(), new CouponsDBDAO(), new CustomersDBDAO());

    @Test
    void login() throws SystemException, SQLException {
        assertTrue(companyFacade.login("0@gmail.com", "64474223a"));


        assertThrows(SystemException.class, () -> {
            companyFacade.login("admin@admin.com123", "64474223a");
        });
        assertThrows(SystemException.class, () -> {
            companyFacade.login("0@gmail.com", "64474223");
        });
    }

    @Test
    void addCoupon() {
        assertThrows(SystemException.class, () -> {
            companyFacade.addCoupon(new Coupon("testCoupon", "Test", 5, 100, 7.7,  1, new Date(99999999), "test"));
        });

    }

    @Test
    void updateCouponPrice() {
    }

    @Test
    void deleteCoupon() {
    }

    @Test
    void getAllCompanyCoupons() {
    }

    @Test
    void getCompanyCouponsByCategory() {
    }

    @Test
    void getCompanyCouponsByMaxPrice() {
    }

    @Test
    void getCompanyDetails() {
    }

    @Test
    void setCompanyId() {
    }
}