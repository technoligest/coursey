package db;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pending_unis database table.
 * 
 */
@Entity
@Table(name="pending_unis")
@NamedQuery(name="PendingUni.findAll", query="SELECT p FROM PendingUni p")
public class PendingUni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String name;

	private String city;

	private String country;

	private String email;

	public PendingUni() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}