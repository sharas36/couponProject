package firstStep;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import Facade.AdminFacade;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) throws SQLException {


        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        System.out.println(companiesDBDAO.isCompanyExist("vfd", "vfdsv"));
//        i check the function of add coupns and is work fine
//        in the sql in the image row change the length if the string to 150;
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DATE, 29);
//        Date endDate = new Date(calendar.getTime().getTime());
//
//
//        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
//        Coupon coupon = new Coupon();
//        coupon.setCompanyId(1);
//        coupon.setCategory("clothing");
//        coupon.setCouponName("check");
//        coupon.setDescription("check description");
//        coupon.setEndDate(endDate);
//        coupon.setAmount(10);
//        coupon.setImageURL("C:\\Users\\yoavd\\Pictures\\Screenshots\\registration.jpeg");
//        coupon.setPrice(35.7);
//
//
//        couponsDBDAO.addCoupon(coupon);


    }

}
