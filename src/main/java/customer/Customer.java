package customer;

import java.util.ArrayList;
import java.util.List;

import firstStep.Coupon;

public class Customer {
	
	private static int id = 1;
	private int custometId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Coupon> coupons = new ArrayList<Coupon>();
	
	public Customer( String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.custometId = id++;
	}

	public final String getFirstName() {
		return firstName;
	}

	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public final String getLastName() {
		return lastName;
	}

	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final List<Coupon> getCoupons() {
		return coupons;
	}

	public final int getCustometId() {
		return custometId;
	}

	@Override
	public String toString() {
		return "Customer [custometId=" + custometId + ", firstName=" + firstName + ", lastName=" + lastName + ", coupons=" + coupons + "]";
	}

	


	
	

}
