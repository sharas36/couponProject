package firstStep;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private String categoryName;
	private List<Coupon> coupons = new ArrayList<>();

	public Category(int categoryId, String categoryName) {
		this.categoryName = categoryName;
	}

	private void addCoupon(Coupon coupon){
		this.coupons.add(coupon);
	}

	private void deleteCoupon(Coupon coupon){
		this.coupons.remove(coupon);
	}
}
