package firstStep;

import java.sql.Date;

import Users.Company;
import lombok.*;

@Data
@AllArgsConstructor

public class Coupon {

    private static int id = 0;
    private int couponId;
    private String couponName;
    private String description;
    private int companyId;
    private int amount;
    private int howMuchRemain;
    private double price;
    private String category;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private String imageURL;
    private Boolean deleted = false;


    public Coupon(String couponName, String description, int companyId, int amount, double price,
                 String category, Date endDate, String imageURL) {
        this.couponName = couponName;
        this.description = description;
        this.companyId = companyId;
        this.amount = amount;
        this.price = price;
        this.category = category;
        this.endDate = endDate;
        this.imageURL = imageURL;
         init();

    }

    public Coupon(String couponName, String description, int companyId,
                  int amount, double price, String category, Date startDate, Date endDate, String imageURL) {
        this.couponName = couponName;
        this.description = description;
        this.companyId = companyId;
        this.amount = amount;
        this.price = price;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageURL = imageURL;
    }

    public Coupon(){
     init();
    }

    private void init() {
        java.util.Date myDate = new java.util.Date();
        //this.startDate = new Date(myDate.getTime());
        this.startDate = new Date(System.currentTimeMillis());

    }

    public void setDeleted(){
        this.deleted = !this.deleted;
    }

    public void printSomething(){
        System.out.println("it is work!");
    }
}
