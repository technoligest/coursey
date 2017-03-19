package coursy.onlineTools;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ProgramRequirementsTest {

	   //Sample data for testing
	   ArrayList<String> courses = new ArrayList<>();
	   String programName = "Computer Science";
	   ProgramRequirements sample = new ProgramRequirements(programName, courses);
	   
	   //Insert courses in an empty course list and check if it has been inserted
	   @Test
	   public void testInsertingCourses() {
		   	sample.addCourse("Database");
		   	sample.addCourse("Operating Systems");
		   	sample.addCourse("Algorithms");
		   	int expectedResult = 3;
	   		int actualResult = sample.getProgramCourses().size();
			assertEquals(actualResult, expectedResult);
	   }
	   
	   //Delete a course and check if it has been deleted from the course requirements
	   @Test
	   public void testRemovingCourse() {
		   	sample.addCourse("Database");
		   	sample.addCourse("Operating Systems");
		   	sample.addCourse("Algorithms");
		   	sample.removeCourse("Database");
		   	boolean expectedResult = false;
		   	boolean actualResult = sample.getProgramCourses().contains("Database");		   	

			assertEquals(actualResult, expectedResult);
	   }
	   
	   //Rename a course and check if it has been renamed successfully
	   @Test
	   public void testRenamingCourse() {
		   	sample.addCourse("Database");
		   	sample.addCourse("Operating Systems");
		   	sample.addCourse("Algorithms");
		   	sample.editCourse("Algorithms", "AlgorithmsModified");
		    boolean expectedResult = true;
		   	boolean actualResult;		   	
		   	if(sample.getProgramCourses().contains("Algorithms") == false && sample.getProgramCourses().contains("AlgorithmsModified") == true){
		   		actualResult = true;
		   	}
		   	else{
		   		actualResult = false;
		   	}
			assertEquals(actualResult, expectedResult);
	   }
	   

}
