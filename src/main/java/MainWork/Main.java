package MainWork;

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
import firstStep.ConnectionPool;
import firstStep.Coupon;
import firstStep.SystemException;

import javax.swing.plaf.IconUIResource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, SystemException {

        MainFacade mainFacade = userCheckScreen();
        User user = login(mainFacade);
        if (user instanceof Admin) {
            adminMenu((AdminFacade) mainFacade);
        } else if (user instanceof Company) {
            companyMenu((CompanyFacade) mainFacade);
        } else {
            customerMenu((CustomerFacade) mainFacade);
        }
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
        switch (Integer.parseInt(choice)) {
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

    public static User login(MainFacade mainFacade) throws SQLException {
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
        if (mainFacade instanceof AdminFacade) {
            return new Admin(email, password);
        } else if (mainFacade instanceof CompanyFacade) {
            return new Company(email, password);
        }
        return new Customer(email, password);
    }

    public static void adminMenu(AdminFacade adminFacade) throws SQLException {


        while (true) {
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
                    try {
                        adminAddCompany(adminFacade);
                    } catch (SystemException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        adminUpdateCompany(adminFacade);
                    } catch (SystemException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        adminDeleteCompany(adminFacade);
                    } catch (SystemException e) {
                        e.printStackTrace();
                    }
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
        }
    }

    public static void adminAddCompany(AdminFacade adminFacade) throws SystemException, SQLException {
        System.out.println("Company name: ");
        String companyName = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        adminFacade.addCompany(new Company(companyName, email, password));
    }

    public static void adminUpdateCompany(AdminFacade adminFacade) throws SystemException, SQLException {
        Company company = adminGetCompany(adminFacade);
        System.out.println("Please insert new email");
        String email = scanner.nextLine();
        System.out.println("Please insert new password");
        String password = scanner.nextLine();
        adminFacade.updateCompany(company, email, password);
    }

    private static void adminDeleteCompany(AdminFacade adminFacade) throws SystemException, SQLException {

        Company company = adminGetCompany(adminFacade);
        adminFacade.deleteCompany(company.getCompanyId());
    }

    public static Company adminGetCompany(AdminFacade adminFacade) throws SystemException, SQLException {
        System.out.println("What company do you want to work on? choose number or press 0 for all companies");
        String companyChoice = scanner.next();
        List<Company> companies = adminFacade.getAllCompanies();
        try {
            Integer.parseInt(companyChoice);
        } catch (NumberFormatException e) {
            System.out.println("You need to insert number. please try again");
        }

        while (true) {
            if (Integer.parseInt(companyChoice) != 0) {
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
            } else {
                for (int i = 0; i < companies.size(); i++) {
                    System.out.println(companies.get(i).toString());
                    System.out.println("Please choose a company");
                }
            }
            companyChoice = scanner.next();
        }
        return adminFacade.getOneCompany(Integer.parseInt(companyChoice));
    }

    public static void customerMenu(CustomerFacade mainFacade) {
    }


    public static void companyMenu(CompanyFacade mainFacade) {
    }
}
