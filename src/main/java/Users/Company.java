package Users;

import java.util.ArrayList;
import java.util.List;

import firstStep.Coupon;
import lombok.*;

@Data
public class Company extends User{

	private static int id = 1;
	private int companyId;
	private String companyName;
	private String email;
	private String password;
	private List<Coupon> coupons = new ArrayList<Coupon>();



	public Company(String companyName, String email, String password){
		super(email, password);
		this.companyName = companyName;
		this.email = email;
		this.password = password;
	}

	public Company(int id, String companyName, String email, String password) {
		super();
		this.companyName = companyName;
		this.email = email;
		this.password = password;
		this.companyId = id;
	}

	public void addCoupon(Coupon coupon){
		coupons.add(coupon);
	}

	public void deleteCoupon(Coupon coupon){
		coupons.remove(coupon);
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
}
