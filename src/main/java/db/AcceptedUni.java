package db;

import java.io.Serializable;
import javax.persistence.*;


/**
 * <H1>Accepted University</H1>
 * This class helps us store an accepted university into the table .
 */
@Entity
@Table(name="accepted_unis")
@NamedQuery(name="AcceptedUni.findAll", query="SELECT a FROM AcceptedUni a")
public class AcceptedUni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String city;

	private String country;

	private String email;

	private String name;

	public AcceptedUni() {
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