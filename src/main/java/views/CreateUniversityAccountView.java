package views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;

import objects.*;

public class CreateUniversityAccountView extends VerticalLayout implements View {
	Navigator nv;
	public CreateUniversityAccountView(Navigator nv){
		//Label label = new Label("Enter your Name");
		this.nv=nv;

		final TextField name = new TextField();
		name.setCaption("Name");

		final TextField email = new TextField();
		email.setCaption("Email address:");

		final TextField password = new TextField();
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
			
			//ADD THE COURSYUSER OBJECT INTO DATABASE
//			db.add(new CourseyUser(name.getValue(), email.getValue(), password.getValue(), phone.getValue(),userID.getValue()));
		});
		final VerticalLayout layout = new VerticalLayout();
		Button backButton = new Button("Back");
		backButton.addClickListener( e -> {
			nv.navigateTo("createAccount");
		});
		layout.addComponents(backButton, name,email, password, phone, button);
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth("250px");
		
		addComponent(layout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
	}
}
