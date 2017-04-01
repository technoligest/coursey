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

import views.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	Navigator navigator;
	
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
