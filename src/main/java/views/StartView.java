package views;

import java.util.ArrayList;

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

public class StartView extends VerticalLayout implements View{
	private Navigator nv;

	public StartView(Navigator nv){
		this.nv=nv;

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


		Button createAccountButton = new Button("Create Account",
				new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				nv.navigateTo("createAccount");
			}
		});
		Button testButton = new Button("TEST",
				new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				nv.navigateTo("adminControlPanel");
			}
		});
		Button testButton2 = new Button("TEST2",
				new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				nv.navigateTo("adminLogin");
			}
		});

		form.addComponent(errorMsg);
		form.addComponent(successMsg);
		form.addComponent(name);
		form.addComponent(password);
		form.addComponent(next);
		form.addComponent(createAccountButton);
		form.addComponent(testButton);
		form.addComponent(testButton2);
		form.addComponent(login);

		panel.setContent(form);

		next.addClickListener( e -> {
			boolean found = false;
			if(!name.getValue().equals("")){
				if(findUsername(usernames, name.getValue()) != -1)
					found = true;
				else
					found = false;
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
							boolean passFound = findPassword(passwords, password.getValue(), findUsername(usernames, name.getValue()));

							if(passFound == true){
								successMsg.setValue("Welcome " + name.getValue() + ", login is successfull!");
								nv.navigateTo("adminControlPanel");
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
		setSizeFull();
		addComponent(layout);
		//        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

	}

	//Search for username in an arraylist and return its index if exist, otherwise return -1
	public static int findUsername(ArrayList<String> users, String name){
		int found = -1;
		for(int i=0; i<users.size(); i++){
			if(users.get(i).equals(name)){
				found = i;
			}
		}
		return found;
	}
	//Search if a password matches a specific username
	public static boolean findPassword(ArrayList<String> passwords, String password,int index){
		if(index == -1){
			return false;
		}
		else {
			boolean found = false;
			if(passwords.get(index).equals(password)){
				found = true;
			}
			return found;   		
		}
	}

	@Override
	public void enter(ViewChangeEvent event){
		Notification.show("Welcome here!");
	}
}
