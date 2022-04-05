package company;

import java.util.ArrayList;
import java.util.List;

import firstStep.Coupon;

public class Company {
	
	private static int id = 1;
	private int companyId;
	private String companyName;
	private String email;
	private String password;
	private List<Coupon> coupons = new ArrayList<Coupon>();
	
	public Company(String companyName, String email, String password) {
		super();
		this.companyName = companyName;
		this.email = email;
		this.password = password;
		this.companyId = id++;
	}

	public final String getCompanyName() {
		return companyName;
	}

	protected final void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public final String getEmail() {
		return email;
	}

	protected final void setEmail(String email) {
		this.email = email;
	}

	public final String getPassword() {
		return password;
	}

	protected final void setPassword(String password) {
		this.password = password;
	}

	protected final List<Coupon> getCoupons() {
		return coupons;
	}

	public int getCompanyId() {
		return companyId;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", coupons=" + coupons + "]";
	}

	
	

}
