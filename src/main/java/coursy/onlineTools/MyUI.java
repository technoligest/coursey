
package coursy.onlineTools;
import java.util.*;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
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
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        ArrayList<String> usernames = new ArrayList<String>();
        ArrayList<String> passwords = new ArrayList<String>();
        
        usernames.add("Admin");
        passwords.add("pass");
        
    	VerticalLayout form = new VerticalLayout();
    	form.setSizeUndefined();
    	form.setMargin(true);
    	form.setSpacing(true);
    	
    	Panel panel = new Panel("Admin login");
    	//panel.setSizeUndefined();
    	panel.setWidth("300px");
    	panel.setHeight("300px");

    	
        Label errorMsg = new Label();
        Label successMsg = new Label();
        errorMsg.setVisible(false);
        successMsg.setVisible(false);
        
        final TextField name = new TextField();
        name.setCaption("Input Username:");
        name.setIcon(FontAwesome.USER);
        final PasswordField password = new PasswordField();
        password.setCaption("Input password:");
        password.setIcon(FontAwesome.LOCK);
        password.setVisible(false);

        Button next = new Button("Next");
        Button login = new Button("login");
        login.setVisible(false);
        
    	form.addComponent(errorMsg);
    	form.addComponent(successMsg);
    	form.addComponent(name);
    	form.addComponent(password);
    	form.addComponent(next);
    	form.addComponent(login);
    	
    	panel.setContent(form);
        
        next.addClickListener( e -> {
        	boolean found = false;
        	if(!name.getValue().equals("")){
	        	for(int i=0; i<usernames.size(); i++){
	        		if(usernames.get(i).equals(name.getValue())){
	        			found = true;
	        		}
	        	}
	        	if(found == true){
	        		successMsg.setValue("Welcome " + name.getValue() + ", please input your password!");
	                successMsg.setVisible(true);
	        		errorMsg.setVisible(false);
	        		name.setVisible(false);
	        		next.setVisible(false);
	        		password.setVisible(true);
	        		login.setVisible(true);
	                login.addClickListener( e2 -> {
	                	if(!password.getValue().equals("")){
	                		boolean passFound = false;
	        	        	for(int i=0; i<passwords.size(); i++){
	        	        		if(passwords.get(i).equals(password.getValue())){
	        	        			passFound = true;
	        	        		}
	        	        	}
	        	        	if(passFound == true){
	        	        		successMsg.setValue("Welcome " + name.getValue() + ", login is successfull!");
	        	        		successMsg.setVisible(true);
	        	        		errorMsg.setVisible(false);
	        	        		login.setEnabled(false);
	        	        		password.setReadOnly(true);
	        	        	}
	        	        	else{
	        	        		errorMsg.setValue("Invalid password.");
	        	        		errorMsg.setVisible(true);
	        	        	}
	                	}
	                });
	        		
	        	}
	        	else{
	        		errorMsg.setValue(name.getValue() + " is invalid username.");   
	                errorMsg.setVisible(true);
	        	}
        	}
        	else{
        		errorMsg.setValue("You have entered empty.");  	
                errorMsg.setVisible(true);
        	}

        });
        
        //layout.addComponents(name, next);
        //layout.addComponents(password, login);
        layout.addComponent(panel);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
