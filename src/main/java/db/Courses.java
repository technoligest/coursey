package db;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Courses database table.
 * 
 */
@Entity
@NamedQuery(name="Courses.findAll", query="SELECT c FROM Courses c")
public class Courses implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int crn;

	private String courseName;

	private int progID;

	public Courses() {
	}

	public int getCrn() {
		return this.crn;
	}

	public void setCrn(int crn) {
		this.crn = crn;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getProgID() {
		return this.progID;
	}

	public void setProgID(int progID) {
		this.progID = progID;
	}

}