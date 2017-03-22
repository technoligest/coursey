package objects;


import java.io.Serializable;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;


public class Uni implements Serializable, Cloneable {

	private Long id;
	private String universityName = "";
	private String city = "";
	private String country = "";
	private String task = "";
	private String email = "";


	/**
	 * @return the Id of the University fdjkldsfhjhjlsadkfhs madshjgshlflask
	 * @author Yaser Alkayale
	 * */
	public Long getId() {
		return id;
	}
	/**
	 * sets pending university id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * returns pending unviversity name
	 * @return
	 */
	public String getUniversityName() {
		return universityName;
	}

	/**
	 * sets pending university name
	 * @param universityName
	 */
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	/**
	 * returns city of pending university
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * sets the city of pending university
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * returns the country of the pending university
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * sets the country of the pending university
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * return task
	 * @return
	 */
	public String getTask() {
		return task;
	}

	/**
	 * sets task
	 * @param task
	 */
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * gets email of the pending university
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * sets email for the pending university
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
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
				+ email + '}';
	}

}
