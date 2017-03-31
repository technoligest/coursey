package views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;

public class CreateCoursyAccountView extends VerticalLayout implements View {
	Navigator nv;
	public CreateCoursyAccountView(Navigator nv){
		this.nv=nv;
		
		final TextField name = new TextField();
		name.setCaption("Name");

		final TextField email = new TextField();
		email.setCaption("Email address:");

		final PasswordField password = new PasswordField();
		password.setCaption("Create password:");

		final TextField phone = new TextField();
		phone.setCaption("Phone number:");

		Button button = new Button("Create Account");

		button.addClickListener( e -> {
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
		layout.addComponents(backButton, name,email, password, phone, button);
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
