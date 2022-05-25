package firstStep;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import Facade.AdminFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import Facade.MainFacade;

import java.sql.SQLException;

public class LoginManager {

    private static LoginManager instance = new LoginManager();

    private LoginManager() {
    }


    public static LoginManager getInstance() {
        return instance;
    }

    public MainFacade login(ClientType clientType, String email, String password) throws SystemException, SQLException {

        switch (clientType) {

            case ADMINISTRATOR:
                if (new AdminFacade(new CompaniesDBDAO(), new CouponsDBDAO(), new CustomersDBDAO()).login(email, password)) {
                    return new AdminFacade(new CompaniesDBDAO(), new CouponsDBDAO(), new CustomersDBDAO());
                }
                break;

            case COMPANY:
                if (new CompanyFacade(new CompaniesDBDAO(), new CouponsDBDAO(), new CustomersDBDAO()).login(email, password)) {
                    return new CompanyFacade(new CompaniesDBDAO(), new CouponsDBDAO(), new CustomersDBDAO());
                }
                break;
            case CUSTOMER:
                if (new CustomerFacade(new CompaniesDBDAO(), new CouponsDBDAO(), new CustomersDBDAO()).login(email, password)) {
                    return new CustomerFacade(new CompaniesDBDAO(), new CouponsDBDAO(), new CustomersDBDAO());
                }
                break;
            default: {
                System.out.println("please try again");
                break;
            }

        }

        return null;
    }


}
