package views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import db.AcceptedUni;
import db.CourseyUser;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;

import objects.*;

public class CreateUniversityAccountView extends VerticalLayout implements View {
	Navigator nv;
	
	static final String url = "jdbc:mysql://localhost:3306/coursey_db?zeroDateTimeBehavior=convertToNull";
	static final String USER = "root";
	static final String PASS = "negahban";
	
	public CreateUniversityAccountView(Navigator nv){
		this.nv=nv;

		List<CourseyUser> userList = new ArrayList();
        JPAContainer<CourseyUser> jpaContainer = JPAContainerFactory.make(CourseyUser.class, "courseyDB");
        Collection<Object> resultList = jpaContainer.getItemIds();
        for (Object objId : resultList) {
        	userList.add(jpaContainer.getItem(objId).getEntity());
        }

		Label req = new Label();
		req.setCaption("* required fields");
		final TextField name = new TextField();
		name.setCaption("*Name");
		//name.setValue(null);

		final TextField email = new TextField();
		email.setCaption("*Email address:");
		//email.setValue("");
		
		final PasswordField password = new PasswordField();
		password.setCaption("*Create password:");
		//password.setValue("");
		
		final TextField phone = new TextField();
		phone.setCaption("Phone number:");
		//phone.setValue("");
		
		Button button = new Button("Create Account");

		button.addClickListener( e -> {
			try {
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
			if(name.getValue()!="" && email.getValue()!="" && password.getValue()!=""){
				if(findUser(userList, email.getValue()) == true)
					Notification.show("Email already exists!");
				else{
				Notification.show("Account created successfully!");
				Connection connect;
				Statement state = null;
				connect = DriverManager.getConnection(url, USER, PASS);
				state = connect.createStatement();
			    String sqlAdd = "INSERT INTO `coursey_db`.`CourseyUser` "
			    		+ "(`email`, `password`, `name`, `phoneNumber`, `status`) "
			    		+ "VALUES ('"+email.getValue()+"', '"+password.getValue()+"',"
			    				+ " '"+name.getValue()+"', '"+phone.getValue()+"', '1');";
				state.executeUpdate(sqlAdd);
				connect.close();
				}
			}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		final VerticalLayout layout = new VerticalLayout();
		Button backButton = new Button("Back");
		backButton.addClickListener( e -> {
			nv.navigateTo("createAccount");
		});
		layout.addComponents(req, backButton, name,email, password, phone, button);
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth("250px");
		
		addComponent(layout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
	}
	
	public boolean findUser(List<CourseyUser> user, String username){
		boolean found = false;
		for(int i = 0; i < user.size(); i++){
			if(user.get(i).getEmail().equals(username))
				found = true;
		}
		return found;
		
	}
}
