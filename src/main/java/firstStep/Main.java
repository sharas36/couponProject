package firstStep;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import Facade.MainFacade;
import Users.Admin;
import Users.Company;
import Users.Customer;
import Users.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
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
        User user = login(mainFacade);
        if (user instanceof Admin) {
            adminMenu();
        }
        else if (user instanceof Company) {
            companyMenu();
        }
        else{
            customerMenu();
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


    public static MainFacade userCheckScreen() {

        System.out.println("1. Admin \n" +
                "2. Company \n" +
                "3. customer");

        String choice = scanner.next();
        try {
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

    public static User login(MainFacade mainFacade) throws SystemException, SQLException {
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        try {
            mainFacade.login(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
            System.out.println("Do you want to choose again your type again? y/n");
            switch (scanner.next()) {
                case "y":
                    mainFacade = userCheckScreen();
                    break;
                case "n":
                    break;
                default:
                    System.out.println("Wrong choice! Please try again.");
            }
            login(mainFacade);
        }
        if(mainFacade instanceof AdminFacade){
            return new Admin(email, password);
        }
        else if(mainFacade instanceof CompanyFacade){
            return new Company(email, password);
        }
        return new Customer(email, password);
    }

    public static void adminMenu(AdminFacade adminFacade) throws SystemException, SQLException {

        System.out.println("1. Add company \n" +
                "2. Update company \n" +
                "3. Delete company \n" +
                "4. Show all companies \n" +
                "5. Show one company \n" +
                "6. Add customer \n" +
                "7. Update customer \n" +
                "8. Delete customer \n" +
                "9. Show all customers \n" +
                "10. Show one customer");
        String choice = scanner.next();
        try {
            Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("You need to insert number 1-10. please try again");
            adminMenu(adminFacade);
        }

        switch (Integer.parseInt(choice)) {
            case 1:
                adminAddCompany(adminFacade);
                break;
            case 2:
                adminUpdateCompany(adminFacade);
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            case 10:

                break;
            default:
                System.out.println("You need to insert number 1-10. please try again");

        }
        adminMenu(adminFacade);
    }

    public static void adminAddCompany(AdminFacade adminFacade){
        System.out.println("Company name: ");
        String companyName = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        try {
            adminFacade.addCompany(new Company(companyName, email, password));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }

    public static void adminUpdateCompany(AdminFacade adminFacade) throws SystemException, SQLException {
        System.out.println("What company do you want to update? choose number or press 0 for all companies");
        String companyChoice = scanner.next();
        List<Company> companies= adminFacade.getAllCompanies();
        try {
            Integer.parseInt(companyChoice);
        } catch (NumberFormatException e) {
            System.out.println("You need to insert number. please try again");
        }

        while (true){
            if(Integer.parseInt(companyChoice) != 0){
                try {
                    Company company = adminFacade.getOneCompany(Integer.parseInt(companyChoice));
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (SystemException e) {
                    e.printStackTrace();
                    int size = companies.size();
                    System.out.println("You need to choose 0 - " + size);
                }
            }
            else{
                for (int i = 0; i < companies.size(); i++) {
                    System.out.println(companies.get(i).toString());
                    System.out.println("Please choose a company");
                }
            }
            companyChoice = scanner.next();
        }
        System.out.println("Please insert new email");
        String email = scanner.nextLine();
        System.out.println("Please insert new password");
        String password = scanner.nextLine();
        adminFacade.updateCompany(adminFacade.getOneCompany(Integer.parseInt(companyChoice)), email, password);
    }

    public static void customerMenu() {
    }



    public static void companyMenu() {
    }
}
