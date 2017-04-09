package db;

import java.io.Serializable;
import javax.persistence.*;





/**
* <h1>Courses</h1>
*This object stores a course and all of its values
* 
* @author  Abdullah N.
* @version 1.0
* @since   2016-04-05 
*/
@Entity
@NamedQuery(name="Courses.findAll", query="SELECT c FROM Courses c")
public class Courses implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int crn;

	private String courseName;

	private int progID;
	
	/**
	* This constructor creates an empty Courses object
	*/
	public Courses() {
	}
	
	/**
	 * @return int this returns the given CRN for a course
	 */
	public int getCrn() {
		return this.crn;
	}
	
	/**
	 * @param crn  this is the value to set the crn
	 */
	public void setCrn(int crn) {
		this.crn = crn;
	}

	/**
	 * @return String this returns the course name
	 */
	public String getCourseName() {
		return this.courseName;
	}
	
	
	/**
	 * @param courseName this sets the courseName to a given value
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return int this returns the program id of this object
	 */
	public int getProgID() {
		return this.progID;
	}
	
	/**
	 * @param progID this sets the program id to the inputed value
	 */
	public void setProgID(int progID) {
		this.progID = progID;
	}

}