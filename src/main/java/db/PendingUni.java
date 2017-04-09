package db;

import java.io.Serializable;
import javax.persistence.*;


/**
* <h1>Pending Univeristy</h1>
* This is a University that has requested access to our software but has not been approved yet.
* 
* @author  Abdullah N.
* @version 1.0
* @since   2016-04-05 
*/
@Entity
@Table(name="pending_unis")
@NamedQuery(name="PendingUni.findAll", query="SELECT p FROM PendingUni p")
public class PendingUni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String city;

	private String country;

	private String email;

	private String name;

	public PendingUni() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}