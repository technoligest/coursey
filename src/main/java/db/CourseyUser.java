package db;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CourseyUser database table.
 * 
 */
@Entity
@NamedQuery(name="CourseyUser.findAll", query="SELECT c FROM CourseyUser c")
public class CourseyUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private String attends;

	private String name;

	private String password;

	private String phoneNumber;

	private int role;

	public CourseyUser() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAttends() {
		return this.attends;
	}

	public void setAttends(String attends) {
		this.attends = attends;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}