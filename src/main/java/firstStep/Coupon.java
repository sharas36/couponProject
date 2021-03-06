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
    private int categoryId;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private String imageURL;
    private Boolean deleted = false;
    private Boolean notCreatet = false;


    public Coupon(String couponName, String description, int companyId, int amount, double price,
                  int categoryId, Date endDate, String imageURL) {
        this.couponName = couponName;
        this.description = description;
        this.companyId = companyId;
        this.amount = amount;
        this.price = price;
        this.categoryId = categoryId;
        this.endDate = endDate;
        this.imageURL = imageURL;
        init();

    }

    public Coupon(String couponName, String description, int companyId,
                  int amount, double price, int categoryId, Date startDate, Date endDate, String imageURL) {
        this.couponName = couponName;
        this.description = description;
        this.companyId = companyId;
        this.amount = amount;
        this.price = price;
        this.categoryId = categoryId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageURL = imageURL;
        init();
    }

    public Coupon() {
        this.notCreatet = true;
    }

    private void init() {
        this.startDate = new Date(System.currentTimeMillis());

    }

    public void setDeleted() {
        this.deleted = !this.deleted;
    }

    public void printSomething() {
        System.out.println("it is work!");
    }

    public boolean getExpired(long currentTimeInMillisecond) {
        return this.getEndDate().getTime() <= currentTimeInMillisecond;
    }


}
