package objects;


import java.util.ArrayList;
import java.util.List;

import db.*;
/**
 * <h1>Program Requirements</h1>
 * This stores all the information for a given program.
 * This will help students calculate their schedules as they pick their programs.
 * 
 * @author Technoligest
 */
public class ProgramRequirements {
	String programName;
	ArrayList<String> courses = new ArrayList<>();
	/**
	 * Constructor takes in the program name and its requirements
	 * @param p
	 * @param arrayList
	 */
	public ProgramRequirements(String p, ArrayList<String> arrayList){
		programName = p;
		courses = arrayList;
	}
	
	//set and get methods
	public void setProgramName(String p){
		programName = p;
	}
	public void setProgramCourses(ArrayList<String> c){
		courses = c;
	}
	public String getProgramName(){
		return programName;
	}
	public ArrayList<String> getProgramCourses(){
		return courses;
	}
	//Remove a course from the course requirements list
	public void removeCourse(String courseName){
		courses.remove(courseName);
	}
	//Add a course to the course requirements list
	public void addCourse(String courseName){
		courses.add(courseName);
		
	}
	
	
	/**
	 * Given a course name and a new one, change the name of the course
	 * @param prevName old name of the course
	 * @param newName the new name to set the course to.
	 */
	public void editCourse(String prevName, String newName){
		for(int i=0; i< courses.size(); i++){
			if(courses.get(i).equals(prevName)){
				courses.set(i, newName);
			}
		}		
	}
}
