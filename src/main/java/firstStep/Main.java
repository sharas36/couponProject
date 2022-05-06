package firstStep;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import Facade.MainFacade;
import Users.Admin;
import Users.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, SystemException {

//
//        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
//        System.out.println(companiesDBDAO.isCompanyExist("vfd", "vfdsv"));

        //        i check the function of add coupns and is work fine
//        in the sql in the image row change the length if the string to 150;

//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DATE, 29);
//        Date endDate = new Date(calendar.getTime().getTime());

//
//        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
//        Coupon coupon = new Coupon();
//        coupon.setCompanyId(1);
//        coupon.setCategory("checkNight");
//        coupon.setCouponName("check");
//        coupon.setDescription("check description");
//        coupon.setEndDate(endDate);
//        coupon.setAmount(10);
//        coupon.setImageURL("C:\\Users\\yoavd\\Pictures\\Screenshots\\registration.jpeg");
//        coupon.setPrice(35.7);

        MainFacade mainFacade = userCheckScreen();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        if (mainFacade instanceof AdminFacade){
            mainFacade.login(email, password);
        }


    }

    public static Coupon getCoupon() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 29);
        Date endDate = new Date(calendar.getTime().getTime());

        Coupon coupon = new Coupon();

        coupon.setCompanyId(1);
        coupon.setCategory("checkNight");
        coupon.setCouponName("check");
        coupon.setDescription("check description");
        coupon.setEndDate(endDate);
        coupon.setAmount(10);
        coupon.setImageURL("C:\\Users\\yoavd\\Pictures\\Screenshots\\registration.jpeg");
        coupon.setPrice(35.7);

        return coupon;
    }


    public void loginScreen() {

    }

    public static MainFacade userCheckScreen() {

        System.out.println("1. Admin \n" +
                "2. Company \n" +
                "3. customer");

        String choice = scanner.next();
        try{
            Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("You need to insert number 1-3. please try again");
            userCheckScreen();
        }
        switch (choice) {
            case 1:
                return new AdminFacade(new CompaniesDBDAO(), new CouponsDBDAO(), new CustomersDBDAO());
            case 2:
                return new CompanyFacade(new CompaniesDBDAO(), new CouponsDBDAO(), new CustomersDBDAO());
            case 3:
                return new CustomerFacade(new CompaniesDBDAO(), new CouponsDBDAO(), new CustomersDBDAO());
            default:
                System.out.println("Wrong choice! Please try again.");
                userCheckScreen();
        }
        return null;
    }

    public static void login(MainFacade mainFacade){


    }
}
