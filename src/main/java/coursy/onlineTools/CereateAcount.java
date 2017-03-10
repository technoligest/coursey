
package coursy.onlineTools;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
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
@Title("Coursy")
@Theme("mytheme")
public class MyUI extends UI {
	Database db = new Database();

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Label label = new Label("<b style=\"font-size:20px\">Welcome</b>",ContentMode.HTML);
		

		Button button1 = new Button("Create Coursey Account");
		Button button2 = new Button("Create University Account");

		
		VerticalLayout layout = new VerticalLayout();
		layout.addComponents(label, button1, button2);
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth("250px");
		
		//VerticalLayout initialLayout = createAccountScreen();
		VerticalLayout mainLayout = new VerticalLayout();
		//mainLayout.setSizeFull();
		Button backButton = new Button("Back");
		mainLayout.addComponents(layout);
	

		mainLayout.setComponentAlignment(layout, Alignment.TOP_CENTER);
		VerticalLayout forBackButton = new VerticalLayout();
		forBackButton.addComponent(backButton);
		forBackButton.setHeight("10");
		
		setContent(mainLayout);
		
		button1.addClickListener( e -> {
//			layout.removeAllComponents();
//			layout.addComponents(openCourseyAccount());
			VerticalLayout layout2=requestUniversityAccount();
			mainLayout.removeAllComponents();
			mainLayout.addComponents(forBackButton, layout2);
			mainLayout.setComponentAlignment(layout2, Alignment.TOP_CENTER);
		});
		button2.addClickListener( e -> {
//			layout.removeAllComponents();
//			layout.addComponents(requestUniversityAccount());
			VerticalLayout layout2=requestUniversityAccount();
			mainLayout.removeAllComponents();
			mainLayout.addComponents(forBackButton, layout2);
			mainLayout.setComponentAlignment(layout2, Alignment.TOP_CENTER);
		});
		

		backButton.addClickListener( e -> {
			layout.removeAllComponents();
			layout.addComponents(label, button1, button2);
			mainLayout.removeAllComponents();
			mainLayout.addComponents(layout);
			mainLayout.setComponentAlignment(layout, Alignment.TOP_CENTER);
		});
	}
	
	private VerticalLayout requestUniversityAccount(){
		//Label label = new Label("Enter your Name");


		final TextField name = new TextField();
		name.setCaption("Name");

		final TextField email = new TextField();
		email.setCaption("Email address:");

		final TextField password = new TextField();
		password.setCaption("Create password:");

		final TextField passwordConfirm = new TextField();
		passwordConfirm.setCaption("Confirm password:");

		final TextField userID = new TextField();
		userID.setCaption("Enter ID");

		final TextField phone = new TextField();
		phone.setCaption("Phone number:");


		Button button = new Button("Create Account");

		button.addClickListener( e -> {
			//            layout.addComponent(new Label("Thanks " + name.getValue() 
			//                    + ", it works!"));

			if(name.getValue()==null){
				Notification.show("Please input a username.");
				return;
			}
			if(password.getValue()==null){
				return;
			}
			if(!password.getValue().equals(passwordConfirm.getValue())){
				Notification.show("Error. Passwords don't match."+password.getValue());
				return;
			}
			db.add(new CourseyUser(name.getValue(), email.getValue(), password.getValue(), phone.getValue(),userID.getValue()));
		});
		
		



		final VerticalLayout layout = new VerticalLayout();
		layout.addComponents(name,email, password, passwordConfirm, userID, phone, button);
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth("250px");

		return layout;
	}
	private VerticalLayout openCourseyAccount(){
		//Label label = new Label("Enter your Name");


		final TextField name = new TextField();
		name.setCaption("Name");

		final TextField email = new TextField();
		email.setCaption("Email address:");

		final TextField password = new TextField();
		password.setCaption("Create password:");

		final TextField passwordConfirm = new TextField();
		passwordConfirm.setCaption("Confirm password:");

		final TextField userID = new TextField();
		userID.setCaption("Enter ID");

		final TextField phone = new TextField();
		phone.setCaption("Phone number:");


		Button button = new Button("Create Account");

		button.addClickListener( e -> {
			//            layout.addComponent(new Label("Thanks " + name.getValue() 
			//                    + ", it works!"));

			if(name.getValue()==null){
				Notification.show("Please input a username.");
				return;
			}
			if(password.getValue()==null){
				return;
			}
			Notification.show("Account created successfully!");
		});



		final VerticalLayout layout = new VerticalLayout();
		layout.addComponents(name,email, password, passwordConfirm, userID, phone, button);
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth("250px");

		return layout;

	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
