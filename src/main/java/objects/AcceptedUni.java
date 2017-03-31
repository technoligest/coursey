package objects;


import java.io.Serializable;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

public class AcceptedUni implements Serializable, Cloneable {

    private Long id2;
    private String universityName2 = "";
    private String city2 = "";
    private String country2 = "";
    private String email2 = "";
   
    
    public Long getId2() { 
    	return id2; 
    }
    /**
     * sets id
     * @param id
     */
    public void setId2(Long id) { 
    	this.id2 = id; 
    }
    /**
     * returns the approved university name
     * @return
     */
    public String getUniversityName2() { 
    	return universityName2; 
    }
    /**
     * sets approved university name
     * @param universityName
     */
    public void setUniversityName2(String universityName) { 
    	this.universityName2 = universityName; 
    }
    /**
     * returns the city of the approved university
     * @return
     */
    public String getCity2() { 
    	return city2; 
    }
    /**
     * sets the city of the approved university 
     * @param city
     */
    public void setCity2(String city) { 
    	this.city2 = city; 
    }
    /**
     * returns the country of the approved university
     * @return
     */
    public String getCountry2() { 
    	return country2; 
    }
    /**
     * sets the country of the approved university
     * @param country
     */
    public void setCountry2(String country) {
        this.country2 = country;
    }
    

    /**
     * returns the email of the approved university
     * @return
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * sets the email of the approved university
     * @param email
     */
    public void setEmail2(String email) {
        this.email2 = email;
    }

    @Override
    public AcceptedUni clone() throws CloneNotSupportedException {
        try {
            return (AcceptedUni) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id2 + ", UniversityName=" + universityName2
                + ", city=" + city2 +  ", email="
                + email2 + '}';
    }

}