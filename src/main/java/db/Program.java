package db;

import java.io.Serializable;
import javax.persistence.*;


/**
* <h1>Program</h1>
* This is a given program at a university which could be a degree or a certificate
* 
* @author  Abdullah N.
* @version 1.0
* @since   2016-04-05 
*/
@Entity
@Table(name="Programs")
@NamedQuery(name="Program.findAll", query="SELECT p FROM Program p")
public class Program implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int programID;

	private String progName;

	private int uniID;

	public Program() {
	}

	public int getProgramID() {
		return this.programID;
	}

	public void setProgramID(int programID) {
		this.programID = programID;
	}

	public String getProgName() {
		return this.progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}

	public int getUniID() {
		return this.uniID;
	}

	public void setUniID(int uniID) {
		this.uniID = uniID;
	}

}