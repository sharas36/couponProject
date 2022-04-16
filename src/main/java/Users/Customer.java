package Users;

import java.util.ArrayList;
import java.util.List;

import firstStep.Coupon;
import lombok.*;

@Data
public class Customer extends User {
	
	private static int id = 1;
	private int customerId;
	private String firstName;
	private String lastName;
	private List<Coupon> coupons = new ArrayList<Coupon>();

	public Customer(String firstName, String lastName, String email, String password) {
		super(email, password);
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
