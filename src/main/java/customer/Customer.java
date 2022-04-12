package customer;

import java.util.ArrayList;
import java.util.List;

import firstStep.Coupon;
import lombok.*;

@Data
@AllArgsConstructor
public class Customer {
	
	private static int id = 1;
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Coupon> coupons = new ArrayList<Coupon>();

	public Customer(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
}
