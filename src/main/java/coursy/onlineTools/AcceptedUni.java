package coursy.onlineTools;


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
public class AcceptedUni implements Serializable, Cloneable {

    private Long id2;

    private String universityName2 = "";
    private String city2 = "";
    private String country2 = "";
    private String task2 = "";
    private String email2 = "";
    private Date startDate2;
    private Date endDate2;
   

    public Long getId2() {
        return id2;
    }

    public void setId2(Long id) {
        this.id2 = id;
    }

    public String getUniversityName2() {
        return universityName2;
    }

    public void setUniversityName2(String universityName) {
        this.universityName2 = universityName;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city) {
        this.city2 = city;
    }

    public String getCountry2() {
        return country2;
    }

    public void setCountry2(String country) {
        this.country2 = country;
    }
    
    public String getTask2() {
        return task2;
    }

    public void setTask2(String task) {
        this.task2 = task;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email) {
        this.email2 = email;
    }

    public Date getStartDate2() {
        return startDate2;
    }

    public void setStartDate2(Date startDate) {
        this.startDate2 = startDate;
    }
    
    public Date getEndDate2() {
        return endDate2;
    }

    public void setEndDate2(Date endDate) {
        this.endDate2 = endDate;
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
                + ", city=" + city2 + ", task=" + task2 + ", email="
                + email2 + ", startDate=" + startDate2 + ", endDate=" +endDate2+ '}';
    }

}
