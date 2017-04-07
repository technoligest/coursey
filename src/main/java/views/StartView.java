package views;

import java.util.ArrayList;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import db.CourseyUser;
import frontUi.MyUI;

import java.util.*;

public class StartView extends VerticalLayout implements View{
	private Navigator nv;
	
	
	public StartView(Navigator nv){
		this.nv=nv;

		final VerticalLayout layout = new VerticalLayout();


		VerticalLayout form = new VerticalLayout();
		form.setSizeUndefined();
		form.setMargin(true);
		form.setSpacing(true);

		Panel panel = new Panel("Login");
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


		Button createAccountButton = new Button("Create Account",
				new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				nv.navigateTo("createAccount");
			}
		});


		form.addComponent(errorMsg);
		form.addComponent(successMsg);
		form.addComponent(name);
		form.addComponent(password);
		form.addComponent(next);
		form.addComponent(createAccountButton);
		form.addComponent(login);

		panel.setContent(form);

		
		List<CourseyUser> user = new ArrayList();
        JPAContainer<CourseyUser> jpaUser = 
        		JPAContainerFactory.make(CourseyUser.class, "courseyDB");
        Collection<Object> resultList = jpaUser.getItemIds();
        for (Object objId : resultList) {
            user.add(jpaUser.getItem(objId).getEntity());
        }

		next.addClickListener( e -> {
			boolean found = false;
			if(!name.getValue().equals("")){
				if(findUsername(user, name.getValue()) > -1)
					found = true;
				else{
					found = false;
				}
				if(found == true){
					successMsg.setValue("Please input your password!");
					successMsg.setVisible(true);
					errorMsg.setVisible(false);
					name.setVisible(false);
					next.setVisible(false);
					password.setVisible(true);
					login.setVisible(true);
					createAccountButton.setVisible(false);
					int index = findUsername(user, name.getValue());
					login.addClickListener( e2 -> {
						if(!password.getValue().equals("")){
							boolean passFound = findPassword(user, password.getValue(),findUsername(user, name.getValue()));
							if(passFound == true){
								if(user.get(index).getRole() == 2){
									successMsg.setValue("Welcome " + name.getValue() + ", login is successfull!");
									//populate nv with status == 2 views only
									nv.navigateTo("adminControlPanel");
									successMsg.setVisible(true);
									errorMsg.setVisible(false);
									login.setEnabled(false);
									password.setReadOnly(true);
								}else if(user.get(index).getRole() == 1){
									successMsg.setValue("Welcome " + name.getValue() + ", login is successfull!");
									nv.navigateTo("adminLogin");
									successMsg.setVisible(true);
									errorMsg.setVisible(false);
									login.setEnabled(false);
									password.setReadOnly(true);
								}if(user.get(index).getRole() == 0){
									successMsg.setValue("Welcome " + name.getValue() + ", login is successfull!");
									MyUI.user.setEmail(user.get(index).getEmail());
									MyUI.user.setName(user.get(index).getName());
									MyUI.user.setPhoneNumber(user.get(index).getPhoneNumber());
									MyUI.user.setPassword(user.get(index).getPassword());
									successMsg.setVisible(true);
									errorMsg.setVisible(false);
									login.setEnabled(false);
									password.setReadOnly(true);
								}
							
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
		setSizeFull();
		addComponent(layout);
		//        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

	}

	//Search for username in an arraylist and return its index if exist, otherwise return -1
	public static int findUsername(List<CourseyUser> users, String name){
		int index = -1;
		for(int i=0; i<users.size(); i++){
			if(users.get(i).getEmail().equals(name)){
				index = i;
				break;
			}
		}
		return index;
	}
	//Search if a password matches a specific username
	public static boolean findPassword(List<CourseyUser> user, String password,int index){
		boolean match = false;
		if(user.get(index).getPassword().equals(password))
			match = true;
		return match;
		
	}

	@Override
	public void enter(ViewChangeEvent event){
		Notification.show("Welcome here!");
	}
}