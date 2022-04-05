package firstStep;

import java.util.Calendar;
import java.util.Date;

import company.Company;

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
	private Date startDate;
	private Date endDate;
	private String imageURL;
	
	public Coupon(String couponName, String description, int amount, double price, Category category, Company company,
			Date endDate) {
		super();
		this.couponId = id++;
		this.couponName = couponName;
		this.description = description;
		this.amount = amount;
		this.howMuchRemain = amount;
		this.price = price;
		this.category = category;
		this.company = company;
		this.endDate = endDate;
		setStartDate();
	}
	protected final String getCouponName() {
		return couponName;
	}
	protected final void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	protected final String getDescription() {
		return description;
	}
	protected final void setDescription(String description) {
		this.description = description;
	}
	protected final Company getCompany() {
		return company;
	}
	protected final void setCompany(Company company) {
		this.company = company;
	}
	protected final int getAmount() {
		return amount;
	}
	protected final void setAmount(int amount) {
		this.amount = amount;
	}
	protected final int getHowMuchRemain() {
		return howMuchRemain;
	}
	protected final void setHowMuchRemain(int howMuchRemain) {
		this.howMuchRemain = howMuchRemain;
	}
	protected final double getPrice() {
		return price;
	}
	protected final void setPrice(double price) {
		this.price = price;
	}
	protected final Category getCategory() {
		return category;
	}
	protected final void setCategory(Category category) {
		this.category = category;
	}
	protected final Date getStartDate() {
		return startDate;
	}
	protected final void setStartDate() {
		Calendar today = Calendar.getInstance();
		this.startDate = today.getTime();
	}
	protected final Date getEndDate() {
		return endDate;
	}
	protected final void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	protected final int getCouponId() {
		return couponId;
	}
	
	
	protected final String getImageURL() {
		return imageURL;
	}
	protected final void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", couponName=" + couponName + ", description=" + description
				+ ", company=" + company + ", amount=" + amount + ", howMuchRemain=" + howMuchRemain + ", price="
				+ price + ", category=" + category + ", startDate=" + startDate + ", endDate=" + endDate + ", imageURL="
				+ imageURL + "]";
	}
	
	
	
	

}
