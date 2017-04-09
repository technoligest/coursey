package views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.Notification.Type;
import db.*;
/**
 * <h1>Create Coursy Account View</h1>
 * 
 * This class helps us create an account for a coursy employee.
 * @author Technoligest
 *
 */
public class CreateCoursyAccountView extends VerticalLayout implements View {
	Navigator nv;
	
	static final String url = "jdbc:mysql://localhost:3306/coursey_db?zeroDateTimeBehavior=convertToNull";
	static final String USER = "root";
	static final String PASS = "negahban";
	
	
	/**
	 * Everything in this view is initialized in the contructor and it is added to the navigator of the parent.
	 * @param nv
	 */
	public CreateCoursyAccountView(Navigator nv){
		this.nv=nv;
		
		Label req = new Label();
		req.setCaption("* required fields");
		final TextField name = new TextField();
		name.setCaption("*Name");

		final TextField email = new TextField();
		email.setCaption("*Email address:");

		final PasswordField password = new PasswordField();
		password.setCaption("*Create password:");

		final TextField phone = new TextField();
		phone.setCaption("Phone number:");
		
		
		List<AcceptedUni> unis = new ArrayList();
        JPAContainer<AcceptedUni> jpaUser = 
        		JPAContainerFactory.make(AcceptedUni.class, "courseyDB");
        Collection<Object> resultList = jpaUser.getItemIds();
        for (Object objId : resultList) {
        	unis.add(jpaUser.getItem(objId).getEntity());
        }
		
        ComboBox select = new ComboBox("Select a University");
        for(int i = 0; i< unis.size(); i++)
        	select.addItem(unis.get(i).getName());
        select.setNullSelectionItemId("");

		Button button = new Button("Create Account");

		button.addClickListener( e -> {
			if(name.getValue()==""){
				Notification.show("Please input name.");
				return;
			}
			if(email.getValue()==""){
				Notification.show("Please input email address.");
				return;
			}
			if(password.getValue()==""){
				Notification.show("Please input password.");
				return;
			}
			if(select.getValue()==""){
				Notification.show("Please select a university.");
			}
			
			try {
				Notification.show("Account created successfully!");
				Connection connect;
				Statement state = null;
				connect = DriverManager.getConnection(url, USER, PASS);
				state = connect.createStatement();
			    String sqlAdd = "INSERT INTO "
			    		+ "`coursey_db`.`CourseyUser` "
			    		+ "(`email`, `password`, `name`, `phoneNumber`, `role`, `Attends`) "
			    		+ "VALUES ('"+email.getValue()+"', '"+password.getValue()+"',"
			    				+ " '"+name.getValue()+"', '"+phone.getValue()+"', '0', '"+select.getValue()+"');";
				state.executeUpdate(sqlAdd);
				connect.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		Button backButton = new Button("Back");
		backButton.addClickListener( e -> {
			nv.navigateTo("createAccount");
		});

		final VerticalLayout layout = new VerticalLayout();
		layout.addComponents(req, backButton, name,email, password, phone, select, button);
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth("250px");

		addComponents(layout);
	}

	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
	}
}
