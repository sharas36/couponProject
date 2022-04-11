package company;

import java.util.ArrayList;
import java.util.List;

import firstStep.Coupon;
import lombok.*;

@Data
@AllArgsConstructor
public class Company {

	private static int id = 1;
	private int companyId;
	private String companyName;
	private String email;
	private String password;
	private List<Coupon> coupons = new ArrayList<Coupon>();

	public Company(String companyName, String email, String password){
		this.companyName = companyName;
		this.email = email;
		this.password = password;
	}

	public Company(int id, String companyName, String email, String password) {
		this.companyName = companyName;
		this.email = email;
		this.password = password;
		this.companyId = id;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
}
