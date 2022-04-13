package firstStep;

import java.util.Calendar;
import java.util.Date;

import company.Company;
import lombok.*;

@Data
@AllArgsConstructor

public class Coupon {
	
	private static int id = 0;
	private int couponId;
	private String couponName;
	private String description;
	private Company company;
	private int amount;
	private int howMuchRemain;
	private double price;
	private Category category;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private String imageURL;



	

	
	



}
