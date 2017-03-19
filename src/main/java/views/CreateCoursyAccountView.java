package views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;

public class CreateCoursyAccountView extends VerticalLayout implements View {
	Navigator nv;
	public CreateCoursyAccountView(Navigator nv){
		this.nv=nv;
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

		Button backButton = new Button("Back");
		backButton.addClickListener( e -> {
			nv.navigateTo("createAccount");
		});

		final VerticalLayout layout = new VerticalLayout();
		layout.addComponents(backButton, name,email, password, passwordConfirm, userID, phone, button);
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
