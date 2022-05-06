package Users;

import firstStep.Coupon;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data

public class Admin extends User{

	private String firstName;
	private String lastName;
	private String email;
	private String password;
//	private List<Company> companies = new ArrayList<>();
//	private List<Coupon> coupons = new ArrayList<>();
//	private List<Customer> customers = new ArrayList<>();


	public Admin(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Admin(String email, String password, String firstName, String lastName) {
		super(email, password);
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
