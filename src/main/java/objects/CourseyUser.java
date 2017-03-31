package objects;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

public class CourseyUser implements Serializable {
	private String name;
	private String email;
	private String password;
	private String phoneNumber;
	private boolean isAdmin = false;
	public CourseyUser(String name, String email, String password, String number, boolean isAdmin) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = number;
		this.isAdmin = isAdmin;
	}
	public void setIsAdmin(boolean isAdmin){
		this.isAdmin = isAdmin;
	}
	public boolean getIsAdmin(boolean isAdmin){
		return isAdmin;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String number) {
		this.phoneNumber = number;
	}
}
