package views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;

@Theme("mytheme")
public class Request extends VerticalLayout implements View {
	Navigator nv;
	
	static final String url = "jdbc:mysql://localhost:3306/coursey_db?zeroDateTimeBehavior=convertToNull";
	static final String USER = "root";
	static final String PASS = "negahban";
	
	public Request(Navigator nv){
		final VerticalLayout mainlayout = new VerticalLayout();
		final VerticalLayout layout = new VerticalLayout();
		Label label = new Label("Thanks for choosing Coursy, we're well known for selling clients' data"
				+ ",,just saying");
		mainlayout.setHeight("100%");
		final Panel panel = new  Panel("Application Form"); 
		panel.setWidth("400px");

		//ApplicationObj applicant = new ApplicationObj ();//
		Label required = new Label("*required fields");
		TextField name = new TextField("*Name");
		TextField email = new TextField("*Email");
		TextField city = new TextField("*City");
		TextField country = new TextField("*Country");

		Button submit = new Button("Submit");

		layout.addComponents(required, name, email,city, country, submit);
		submit.addClickListener( e -> {
			Iterator<Component> iter = layout.getComponentIterator();
			while (iter.hasNext()) {
				Component c = iter.next();
				if (c instanceof com.vaadin.ui.AbstractField && ((com.vaadin.ui.AbstractField) c).isEmpty()){
					Notification.show("Some field(s) are empty!");
					return;
				}
			}
			if(name.getValue()!="" && email.getValue()!="" && city.getValue()!="" && country.getValue()!=""){
				Connection connect;
				Statement state = null;
				try{
				connect = DriverManager.getConnection(url, USER, PASS);
				state = connect.createStatement();
			    String sqlAdd = "INSERT INTO "
			    		+ "`coursey_db`.`pending_unis` "
			    		+ "(`name`, `city`, `country`, `email`) "
			    		+ "VALUES ('"+name.getValue()+"', '"+city.getValue()+"', '"+country.getValue()+"', '"+email.getValue()+"');";
				state.executeUpdate(sqlAdd);
				connect.close();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			layout.setVisible(false);
			panel.setContent(label);
			panel.setWidth("500px");
		});


		layout.setMargin(true);
		layout.setSpacing(true);

		panel.setContent(layout);
		mainlayout.addComponent(panel);
		mainlayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		addComponent(mainlayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}