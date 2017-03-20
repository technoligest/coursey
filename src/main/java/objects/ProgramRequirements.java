package objects;


import java.util.ArrayList;

public class ProgramRequirements {
	String programName;
	ArrayList<String> courses = new ArrayList<String>();
	//Constructor
	public ProgramRequirements(String p, ArrayList<String> c){
		programName = p;
		courses = c;
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
	//Rename a course in the course requirements list
	public void editCourse(String prevName, String newName){
		for(int i=0; i< courses.size(); i++){
			if(courses.get(i).equals(prevName)){
				courses.set(i, newName);
			}
		}		
	}
}
