package views;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
/**
 * <h1>Create Account View</h1>
 * This view helps us create various accounts to access our software
 * 
 * @author Technoligest
 *
 */

@Title("Coursy")
@Theme("mytheme")
public class CreateAccountView extends VerticalLayout implements View{
	private Navigator nv;


	/**
	 * Everything in this view is initialized in the contructor and it is added to the navigator of the parent.
	 * @param nv
	 */
	public CreateAccountView(Navigator nv){
		this.nv=nv;
		Label label = new Label("<b style=\"font-size:20px\">Welcome</b>",ContentMode.HTML);

		Button coursyAccountButton = new Button("Create Coursy Account");
		Button universityAccountButton = new Button("Create University Account");
		Button backButton = new Button("Back");

		VerticalLayout layout = new VerticalLayout();
		layout.addComponents(label, coursyAccountButton, universityAccountButton);
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth("250px");

		//VerticalLayout initialLayout = createAccountScreen();
		VerticalLayout mainLayout = new VerticalLayout();

		mainLayout.addComponents(backButton, layout);
		mainLayout.setComponentAlignment(layout, Alignment.TOP_CENTER);

		addComponent(mainLayout);

		coursyAccountButton.addClickListener( e -> {
			nv.navigateTo("createCoursyAccount");
		});
		universityAccountButton.addClickListener( e -> {
			nv.navigateTo("createUniversityAccount");
		});		
		backButton.addClickListener( e -> {
			nv.navigateTo("");
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}
}
