package DAO.DAOCustomers;

import Users.Customer;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CustomersDAOTest {


    CustomersDBDAO customersDBDAO = new CustomersDBDAO();

    @Test
    void addCustomer() throws SQLException {
        int staticId = 7;
        String name = "firstName " + staticId;
        String lastName = "lastName " + staticId;
        String password = "password " + staticId;
        String email = "email " + staticId;
        customersDBDAO.addCustomer(new Customer(name, lastName, email, password));
        assertTrue(customersDBDAO.isCustomerExist(email, password));


    }

    @Test
    void deleteCustomer() throws SQLException, InterruptedException {
        int staticId = 7;
        String name = "deletedFirstName " + staticId;
        String lastName = "deletedLastName " + staticId;
        String email = "deletedEmail " + staticId;
        String password = "deletedPassword1 " + staticId;

        customersDBDAO.addCustomer(new Customer(name, lastName, email, password));
        assertTrue(customersDBDAO.isCustomerExist(email, password));
        Thread.sleep(1000 * 10); // this time to check out the database if the customer been added
        customersDBDAO.deleteCustomer(customersDBDAO.getCustomerIdByMail(email));
        assertFalse(customersDBDAO.isCustomerExist(email, password));


    }

    @Test
    void getAllCustomers() throws SQLException {
        customersDBDAO.getAllCustomers();
        List<Customer> customerList = customersDBDAO.getAllCustomers();
        assertNotNull(customerList);
        assertTrue(customerList.get(0) != null);
        for (int i = 0; i < customerList.size() / 2; i++) {
            System.out.println(customerList.get(new Random().nextInt(customerList.size())));
        }

    }

    @Test
    void getCustomer() throws SQLException {
        Customer customer1 = customersDBDAO.getCustomer(5);
        assertTrue(customer1.getCustomerId() > 0);

        // the two example below are true/false of non-existing customer
        Customer customer2 = customersDBDAO.getCustomer(55);
        assertTrue(customer2 == null);

        Customer customer3 = customersDBDAO.getCustomer(66);
        assertFalse(customer3 != null);
    }

    @Test
    void getCustomerIdByMail() throws SQLException {
        int idOfCustomer1 = customersDBDAO.getCustomerIdByMail("email 5");
        assertTrue(idOfCustomer1 > 0);
        int idOfCustomer2 = customersDBDAO.getCustomerIdByMail("email 99");
        assertTrue(idOfCustomer2 == 0);
    }

    @Test
    void isCustomerExist() throws SQLException {
        int staticId = 7;
        String name = "firstName " + staticId;
        String lastName = "lastName " + staticId;
        String password = "password " + staticId;
        String email = "email " + staticId;
        customersDBDAO.addCustomer(new Customer(name, lastName, email, password));
        assertTrue(customersDBDAO.isCustomerExist(email, password));


    }

    @Test
    void isThisMailExist() throws SQLException {

        int staticId = 20;
        String name = "firstName " + staticId;
        String lastName = "lastName " + staticId;
        String password = "password " + staticId;
        String email = "email " + staticId;
        customersDBDAO.addCustomer(new Customer(name, lastName, email, password));
        assertTrue(customersDBDAO.isThisMailExist(email));

    }

    @Test
    void updateCustomer() throws SQLException {

        int staticId = 24;
        String name = "firstName " + staticId;
        String lastName = "lastName " + staticId;
        String password = "password " + staticId;
        String email = "email " + staticId;

        Customer customer = new Customer(name, lastName, email, password);
        customersDBDAO.addCustomer(customer);

        int idCustomer = customersDBDAO.getCustomerIdByMail(email);
        customersDBDAO.updateCustomer(customersDBDAO.getCustomerIdByMail(email), email + " changed", password + " changed");
        System.out.println("the new email is: " + customersDBDAO.getCustomer(idCustomer).getEmail() +" the previous email is: " +email);

        assertFalse(customersDBDAO.getCustomer(idCustomer).getEmail().equals(email));


    }


}