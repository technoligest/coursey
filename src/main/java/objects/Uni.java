package objects;


import java.io.Serializable;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

/**
 * A simple DTO for the address book example.
 *
 * Serializable and cloneable Java Object that are typically persisted
 * in the database and can also be easily converted to different formats like JSON.
 */
// Backend DTO class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class Uni implements Serializable, Cloneable {

	private Long id;
	private String universityName = "";
	private String city = "";
	private String country = "";
	private String task = "";
	private String email = "";
	private Date startDate;
	private Date endDate;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public Uni clone() throws CloneNotSupportedException {
		try {
			return (Uni) BeanUtils.cloneBean(this);
		} catch (Exception ex) {
			throw new CloneNotSupportedException();
		}
	}

	@Override
	public String toString() {
		return "Contact{" + "id=" + id + ", UniversityName=" + universityName
				+ ", city=" + city + ", task=" + task + ", email="
				+ email + ", startDate=" + startDate + ", endDate=" +endDate+ '}';
	}

}
