package Facade;

import DAO.DAOCompanies.CompaniesDBDAO;
import DAO.DAOCoupons.CouponsDBDAO;
import DAO.DAOCustomers.CustomersDBDAO;
import Users.Customer;
import firstStep.Category;
import firstStep.Coupon;
import firstStep.SystemException;
import firstStep.SystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFacade extends MainFacade{

    private int customerId;

    public CustomerFacade(CompaniesDBDAO companiesDBDAO, CouponsDBDAO couponsDBDAO, CustomersDBDAO customersDBDAO) {
        super(companiesDBDAO, couponsDBDAO, customersDBDAO);
    }

    @Override
    public Boolean login(String email, String password) throws SQLException, SystemException {
        if(customersDBDAO.isCustomerExist(email, password)){
            this.customerId = customersDBDAO.getCustomerIdByMail(email);
            return true;
        }
        if(!customersDBDAO.isThisMailExist(email)){
            throw new SystemException("This mail isnt exist");
        }
        else
            throw new SystemException("The password isnt match the mail");
    }

    public void purchaseCoupon(int couponId) throws SQLException, SystemException {
        if(couponsDBDAO.getOneCoupon(couponId).getAmount() == 0 || couponsDBDAO.getOneCoupon(couponId).getDeleted().booleanValue() == true){
            throw new SystemException("This coupon is not available");
        }
        couponsDBDAO.addCouponPurchase(couponId, this.customerId);
    }

    public List<Coupon> getAllCustomersCoupons() throws SystemException, SQLException {
        if(couponsDBDAO.getAllCouponsByCustomer(this.customerId) == null){
            throw new SystemException("This customer havent coupons");
        }
        return couponsDBDAO.getAllCouponsByCustomer(this.customerId);
    }

    public List<Coupon> getCustomersCouponsOfCategory(String category) throws SystemException {
        List<Coupon> couponsOfCustomer = new ArrayList<>();
        couponsOfCustomer = getAllCustomersCoupons();
        List<Coupon> couponOfCategory = new ArrayList<>();
        for(Coupon coupon : couponsOfCustomer){
            if(coupon.getCategory().equals(category)){
                couponOfCategory.add(coupon);
            }
        }
        if(couponOfCategory == null){
            throw new SystemException("This customer havent coupons in this category");
        }
        return couponOfCategory;
    }

    public List<Coupon> getCustomersCouponsByMaxPrice(int maxPrice) throws SystemException {
        List<Coupon> couponsOfCustomer = new ArrayList<>();
        couponsOfCustomer = getAllCustomersCoupons();
        List<Coupon> couponsByMaxPrice = new ArrayList<>();
        for(Coupon coupon : couponsOfCustomer){
            if(coupon.getPrice() <= maxPrice){
                couponsByMaxPrice.add(coupon);
            }
        }
        if(couponsByMaxPrice == null){
            throw new SystemException("This customer havent coupons under this price");
        }
        return couponsByMaxPrice;
    }

    public Customer getCustomerDetails() throws SQLException {
        return customersDBDAO.getCustomer(this.customerId);
    }
}
