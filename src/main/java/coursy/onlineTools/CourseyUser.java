package coursy.onlineTools;

public class CourseyUser {
	private String name;
	private String email;
	private String password;
	private String number;
	private String userID;
	public CourseyUser(String name, String email, String password, String number, String userID) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.number = number;
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
