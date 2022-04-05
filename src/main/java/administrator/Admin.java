package administrator;

public class Admin {

	private String firstName;
	private String lastName;
	
	public Admin(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	protected final String getFirstName() {
		return firstName;
	}

	protected final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	protected final String getLastName() {
		return lastName;
	}

	protected final void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	
	
}
