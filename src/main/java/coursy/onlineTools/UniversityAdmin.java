package coursy.onlineTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class UniversityAdmin extends UI {
    String selectedProgram;
    String selectedCourse;
	Grid coursesTable = new Grid();
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	//Sample data
    	ArrayList ENGRequirements = new ArrayList<>();
    	ENGRequirements.add("Physics");
    	ENGRequirements.add("Calculus");
    	ENGRequirements.add("Programming");
    	ENGRequirements.add("Circuits");
    	ENGRequirements.add("Dynamics");
    	ArrayList CSRequirements = new ArrayList<>();
    	CSRequirements.add("Database");
    	CSRequirements.add("Software Engineering");
    	CSRequirements.add("Web design");
    	CSRequirements.add("Assembly");
    	CSRequirements.add("Operating Systems");
    	ProgramRequirements cs = new ProgramRequirements("Computer Science", CSRequirements);
    	ProgramRequirements eng = new ProgramRequirements("Engineering", ENGRequirements);
    	ArrayList<ProgramRequirements> programsList = new ArrayList<ProgramRequirements>();
    	programsList.add(eng);
    	programsList.add(cs);
    	
    	//Programs list panel
    	Panel programs = new Panel("Available Programs");
    	Label successMsg = new Label();
    	successMsg.setVisible(false);
    	successMsg.setWidth("475px");
        Grid programsTable = new Grid();
        programsTable.addColumn("Program Name");
        for(int i=0; i<programsList.size(); i++){
            programsTable.addRow(programsList.get(i).getProgramName());
        }
 
        //Create new program
        TextField programName = new TextField();
        programName.setCaption("Input program name:");
        Button createProgramButton = new Button("Create prgoram");
        HorizontalLayout newProgram = new HorizontalLayout(programName, createProgramButton);
        newProgram.setSpacing(true);
        createProgramButton.setHeight("62px");
        
        //Sidebar contains programs list and creating new program panel
        VerticalLayout sidebar = new VerticalLayout(successMsg, programsTable, newProgram);
    	sidebar.setMargin(true);
        sidebar.setSpacing(true);
        VerticalLayout main = new VerticalLayout();
        main.setMargin(true);
        main.setSpacing(true);
    	Panel requirements = new Panel("Program Requirements");
    	requirements.setVisible(false);
        requirements.setContent(main);
        programs.setContent(sidebar);
        programs.setSizeUndefined();
        requirements.setSizeUndefined();
        main.setVisible(false);
        
        //Edit program requirements panel. Add, remove and rename courses
        VerticalLayout editCourses = new VerticalLayout();
        TextField courseName = new TextField();
        courseName.setCaption("Input course name:");
        Button change = new Button("Rename course");
        Button remove = new Button("Remove course");
        Button add = new Button("Add course");
        editCourses.addComponent(courseName);
        editCourses.addComponent(change);
        editCourses.addComponent(remove);
        editCourses.addComponent(add);
        editCourses.setMargin(true);
        editCourses.setSpacing(true);
        Panel editCoursesPanel = new Panel("Edit Requirements");
        editCoursesPanel.setContent(editCourses);
        editCoursesPanel.setVisible(false);
        
        //Container layout that includes all panels
        HorizontalLayout container = new HorizontalLayout();
        container.addComponents(programs, requirements, editCoursesPanel);
        container.setMargin(true);
        container.setSpacing(true);

      
        //event listener for pressing program name from list of programs
        programsTable.addItemClickListener(event -> 
        	{
        		selectedProgram = String.valueOf(event.getItem());
        		coursesTable = new Grid();
        		renderRequirements(coursesTable, main, selectedProgram, programsList);
        		requirements.setVisible(true);
        		editCoursesPanel.setVisible(true);
        		//event listener for pressing a course from requirements list
                coursesTable.addItemClickListener(event2 -> 
                {
        	        selectedCourse = String.valueOf(event2.getItem()); 
                });
        	}
        );
        //event listener for pressing "rename" button in the editCourses panel
        change.addClickListener( event3 -> {
        	String prevName = selectedCourse;
        	String newName = "";
        	//find the program and the selected course to rename it
            for(int i=0; i<programsList.size(); i++){
            	if(programsList.get(i).getProgramName().equals(selectedProgram)){
	            	newName = String.valueOf(courseName.getValue());
	            	programsList.get(i).editCourse(prevName, newName);
            	}
            }
            requirements.setVisible(false);
            editCoursesPanel.setVisible(false);
            //check if the user has selected a valid course before pressing "rename"
            if(prevName == null){
        		successMsg.setValue("No course has been selected to rename. " + selectedProgram + " has not been updated" );
        		successMsg.setVisible(true);
            }
            else{
	    		successMsg.setValue(selectedProgram + " requirements is successfully updated. " + prevName + " has been updated to " + newName + " successfuly.");
	    		successMsg.setVisible(true);
            }
        });
        //event listener for pressing "remove" button in the editCourses panel
        remove.addClickListener( event4 -> {
        	//find the program and the selected course to delete it
            for(int i=0; i<programsList.size(); i++){
            	if(programsList.get(i).getProgramName().equals(selectedProgram)){
	            	programsList.get(i).removeCourse(selectedCourse);
            	}
            }
            requirements.setVisible(false);
            editCoursesPanel.setVisible(false);
            //check if the user has selected a valid course before pressing "remove"
            if(selectedCourse == null){
        		successMsg.setValue("No course has been selected to be deleted. " + selectedProgram + " has not been updated" );
        		successMsg.setVisible(true);
            }
            else{
	    		successMsg.setValue(selectedCourse + " has been removed from " + selectedProgram + " requirements.");
	    		successMsg.setVisible(true);
            }
        });
        //event listener for pressing "add" button in the editCourses panel
        add.addClickListener( event5 -> {
        	//Add the new course to the selected program
            for(int i=0; i<programsList.size(); i++){
            	if(programsList.get(i).getProgramName().equals(selectedProgram)){
	            	programsList.get(i).addCourse(String.valueOf(courseName.getValue()));
            	}
            }
            requirements.setVisible(false);
            editCoursesPanel.setVisible(false);
    		successMsg.setValue(String.valueOf(courseName.getValue()) + " has been added to " + selectedProgram + " requirements.");
    		successMsg.setVisible(true);
        });
        
        //event listener for creating a new program
        createProgramButton.addClickListener( event6 -> {
        	//Create new ProgramRequirements object
        	programsList.add(new ProgramRequirements(programName.getValue(), new ArrayList<String>()));
        	//Add the new program to the programs table
        	programsTable.addRow(programsList.get(programsList.size()-1).getProgramName());
        });
        setContent(container);

    }
    //Render course requirements of the selected ProgramRequirements object
    public static void renderRequirements(Grid coursesTable, VerticalLayout requirements, String selectedProgram, ArrayList<ProgramRequirements> programsList){
        coursesTable.addColumn("Course Name");
        //Remove previous components from the requirements panel
        requirements.removeAllComponents();
        //add the grid component to the requirements panel
        requirements.addComponent(coursesTable);
        requirements.setVisible(true);
        //Add all course requirements that belong to the selected program
        for(int i=0; i<programsList.size(); i++){
        	if(programsList.get(i).getProgramName().equals(selectedProgram)){
        		for(int j=0; j<programsList.get(i).getProgramCourses().size(); j++){
        			coursesTable.addRow(programsList.get(i).getProgramCourses().get(j));
        		}
        	}
        }
    }

    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = UniversityAdmin.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}