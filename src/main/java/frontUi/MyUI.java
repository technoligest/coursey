package frontUi;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import db.CourseyUser;
import views.*;

/**
 * This is the main UI for the entire application 
 */
@Theme("mytheme")
public class MyUI extends UI {
	Navigator navigator;
	public static CourseyUser user;
	/*
	 * 
	 * Initialize the main UI for navigation.
	 * 
	 * 
	 * */
	@Override
	protected void init(VaadinRequest vaadinRequest) {

		navigator = new Navigator(this,this);
		navigator.addView("", new StartView(navigator));
    	navigator.addView("createAccount",new CreateAccountView(navigator));
    	navigator.addView("createUniversityAccount", new CreateUniversityAccountView(navigator));
    	navigator.addView("createCoursyAccount", new CreateCoursyAccountView(navigator));
    	navigator.addView("adminControlPanel", new AdminControlPanelView(navigator));
    	navigator.addView("adminLogin", new AdminLoginView(navigator));
	}

	/*
	 * Sets the URL for the servlet
	 * */
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
