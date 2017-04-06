package views;

import java.util.Iterator;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
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
public class Request extends UI {
	  @Override
	    protected void init(VaadinRequest vaadinRequest) {
	        
		    final VerticalLayout mainlayout = new VerticalLayout();
		    final VerticalLayout layout = new VerticalLayout();
		    Label label = new Label("Thanks for choosing Coursy, we're well known for selling clients' data"
		   		+ ",,just saying");
		    mainlayout.setHeight("100%");
		    final Panel panel = new  Panel("Application Form"); 
		    panel.setWidth("400px");
		    
		//    ApplicationObj applicant = new ApplicationObj ();//
	        
	        TextField name = new TextField("Name");
	        TextField email = new TextField("Email");
	        TextField city = new TextField("City");
	        TextField country = new TextField("Country");
	       // TextField phone = new TextField("Phone");
	        
	        Button submit = new Button("Submit");
	       // Button review = new Button("Review Application");
	        
	        layout.addComponents(name, email,city, country, /*phone,*/ submit);
	        
	        
	        submit.addClickListener( e -> 
	        {
	        		
	        	   
	        		Iterator<Component> iter = layout.getComponentIterator();
	        		while (iter.hasNext()) 
	    	        {
	    	            	Component c = iter.next();
	    	            	if (c instanceof com.vaadin.ui.AbstractField && ((com.vaadin.ui.AbstractField) c).isEmpty())
	    	            	{
	    	          
	    	            			Notification.show("some fields are empty");
	    	            			return;
	    	            	
	    	            	}
	    	        }
	        		
	        		layout.setVisible(false);
	        		panel.setContent(label);
	        		//panel.setContent(review);
	        		panel.setCaption("Congrats!");
	        		panel.setWidth("500px");
	        		
		        	
	        		
	        });
	        
	        
	        layout.setMargin(true);
	        layout.setSpacing(true);
	        
	        panel.setContent(layout);
	        mainlayout.addComponent(panel);
	        mainlayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
	        setContent(mainlayout);
	        
	    }

	    @WebServlet(urlPatterns = "/*", name = "RequestServlet", asyncSupported = true)
	    @VaadinServletConfiguration(ui = Request.class, productionMode = false)
	    public static class RequestServlet extends VaadinServlet {
	    }

}