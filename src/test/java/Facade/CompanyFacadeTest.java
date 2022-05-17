package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import Users.Company;
import firstStep.Coupon;
import firstStep.SystemException;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    void addCoupon() throws SQLException {
        assertThrows(SystemException.class, () -> {
            companyFacade.addCoupon(new Coupon("test", "Test", 1, 100, 7.7,  1, new Date(99999999), "test"));
        });

        assertTrue(companyFacade.getCouponsDBDAO().isThisCouponExist("testCoupon"));

    }

    @Test
    void updateCouponPrice() throws SQLException {

        assertDoesNotThrow(() -> {
            companyFacade.updateCouponPrice(companyFacade.getCouponsDBDAO().getOneCoupon(107), 50);
        });

        assertThrows(SystemException.class, () -> {
            companyFacade.updateCouponPrice(companyFacade.getCouponsDBDAO().getOneCoupon(108), 50);
        });

        assertTrue(companyFacade.getCouponsDBDAO().getOneCoupon(107).getPrice() == 50);
    }

    @Test
    void deleteCoupon() throws SQLException {
        assertThrows(SystemException.class, () -> {
            companyFacade.deleteCoupon(108);
        });
        assertDoesNotThrow(() -> {
            companyFacade.deleteCoupon(106);
        });
        assertTrue(companyFacade.getCouponsDBDAO().isThisCouponExist(companyFacade.couponsDBDAO.getOneCoupon(106).getCouponName()));
    }

    @Test
    void getAllCompanyCoupons() throws SQLException, SystemException {

        this.companyFacade.setCompanyId(1);
        assertTrue(companyFacade.getAllCompanyCoupons().size() == 5);
        assertThrows(SystemException.class, () -> {
            this.companyFacade.getAllCompanyCoupons();
        });
    }

    @Test
    void getCompanyCouponsByCategory() throws SQLException, SystemException {
        this.companyFacade.setCompanyId(169);
        assertTrue(this.companyFacade.getCompanyCouponsByCategory(31).size() == 1);
    }

    @Test
    void getCompanyCouponsByMaxPrice() throws SQLException, SystemException {
        this.companyFacade.setCompanyId(169);
        assertTrue(this.companyFacade.getCompanyCouponsByMaxPrice(800).size() == 4);
    }

    @Test
    void getCompanyDetails() throws SQLException {
        this.companyFacade.setCompanyId(169);
        companyFacade.getCompanyDetails();
    }

}