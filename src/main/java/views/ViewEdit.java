package views;

import java.util.Iterator;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;

@Theme("mytheme")
public class ViewEdit extends UI {
	  @Override
	    protected void init(VaadinRequest vaadinRequest) {
	        
		    final VerticalLayout layout = new VerticalLayout();
		    final HorizontalLayout buttonsSpace = new HorizontalLayout();
		   // buttonsSpace.setWidth("100%");
		    final VerticalLayout mainlayout = new VerticalLayout();
		    mainlayout.setHeight("100%");
		    final Panel panel = new  Panel("User Info"); 
		    panel.setWidth("400px");
		    
		   // UserObj user = new UserObj("Bobo","coco@nono.zozo","lolo","11011011","BXXXXX");
	        
	        TextField name = new TextField("Name");
	        name.setValue(user.getName());
	        
	        TextField email = new TextField("Email");
	        email.setValue(user.getEmail());
	        
	        PasswordField pass = new PasswordField("Password shhh!");
	        pass.setValue(user.getPassword());
	        
	        PasswordField confirmPass = new PasswordField("Confirm password");
	        confirmPass.setValue(user.getPassword());
	        
	        TextField phone = new TextField("Phone");
	        phone.setValue(user.getPhone());
	        
	        TextField id = new TextField("ID");
	        id.setValue(user.getID());
	        
	        Button edit = new Button("Edit");
	        Button save = new Button("Save changes");
	        Button cancel = new Button("Cancel");
	        save.setEnabled(false);
	        cancel.setEnabled(false);
	        
	        layout.addComponents(edit,name, email, pass, confirmPass, phone, id, buttonsSpace);
	        buttonsSpace.addComponents(save, cancel);
	        
	        Iterator<Component> comp = layout.getComponentIterator();
	        while (comp.hasNext()) 
	        {
	            	Component c = comp.next();
	            	if (c instanceof com.vaadin.ui.AbstractField)
	            	{
	            		AbstractField field = (AbstractField)c;
	            		field.setReadOnly(true);
	            	}
	        }


	        edit.addClickListener( e -> 
	        {
	        	 
	        	Iterator<Component> iter = layout.getComponentIterator(); 
	        	while (iter.hasNext()) 
	        	 {
	        		 	Component c = iter.next();
	 	            	if (c instanceof com.vaadin.ui.AbstractField) 
	 	            	{
	 	            		AbstractField field = (AbstractField)c;
	 	            		field.setReadOnly(false);
	 	            	}
	        	 }
	        	edit.setEnabled(false);
	        	save.setEnabled(true);
	        	cancel.setEnabled(true);
	        	
	        	 
	        });
	        
	        
	        save.addClickListener( e -> 
	        {
	        		
	        	   
	        		Iterator<Component> iter = layout.getComponentIterator();
	        		if(name.isEmpty())
	        			name.setValue(user.getName());
	        		user.setName(name.getValue());
	        		if(email.isEmpty())
	        			email.setValue(user.getEmail());    /*ifs for validation, too lazy to look up validation methods*/
	        		user.setEmail(email.getValue());
	        		if(pass.isEmpty())
	        		{
		        			Notification.show("correct your passwords");
		        			return;
	        		}
	        		if(confirmPass.isEmpty() ||!(confirmPass.getValue().equals(pass.getValue())))
	                  {
	        			Notification.show("correct your passwords");
	        			return;
	                  }//confirmPass.setValue(pass.getValue());
	        		if(phone.isEmpty())
	        			phone.setValue(user.getPhone());
	        		user.setPhone(phone.getValue());
	        		if(id.isEmpty())
	        			id.setValue(user.getID());
	        		user.setID(id.getValue());
	        		while (iter.hasNext()) 
	    	        {
	    	            	Component c = iter.next();
	    	            	if (c instanceof com.vaadin.ui.AbstractField)
	    	            	{
	    	            		AbstractField field = (AbstractField)c;
	    	            		field.setReadOnly(true);
	    	            	}
	    	        }
	        		edit.setEnabled(true);
		        	save.setEnabled(false);
		        	cancel.setEnabled(false);
		        	
	        		
	        });
	        
	        
	        cancel.addClickListener( e -> 
	        {
	        		
	        		
	        		name.setValue(user.getName());
	        		email.setValue(user.getEmail());
	        		pass.setValue(user.getPassword());
	        		confirmPass.setValue(user.getPassword());
	        		phone.setValue(user.getPhone());
	        		id.setValue(user.getID());
	        		Iterator<Component> iter = layout.getComponentIterator();
	        		while (iter.hasNext()) 
	    	        {
	    	            	Component c = iter.next();
	    	            	if (c instanceof com.vaadin.ui.AbstractField)
	    	            	{
	    	            		AbstractField field = (AbstractField)c;
	    	            		field.setReadOnly(true);
	    	            	}
	    	        }
	        		edit.setEnabled(true);
		        	save.setEnabled(false);
		        	cancel.setEnabled(false);
		        	
	        });
	        
	        
	        
	        layout.setComponentAlignment(edit, Alignment.TOP_RIGHT);
	        /*uncomment for different stylez
	        layout.setComponentAlignment(name, Alignment.MIDDLE_CENTER);
	        layout.setComponentAlignment(email, Alignment.MIDDLE_CENTER);
	        layout.setComponentAlignment(pass, Alignment.MIDDLE_CENTER);
	        layout.setComponentAlignment(confirmPass, Alignment.MIDDLE_CENTER);
	        layout.setComponentAlignment(phone, Alignment.MIDDLE_CENTER);
	        layout.setComponentAlignment(id, Alignment.MIDDLE_CENTER);
	        buttonsSpace.setComponentAlignment(save, Alignment.BOTTOM_CENTER);
	        buttonsSpace.setComponentAlignment(cancel, Alignment.BOTTOM_CENTER);*/
	        layout.setMargin(true);
	        layout.setSpacing(true);
	        
	        panel.setContent(layout);
	        mainlayout.addComponent(panel);
	        mainlayout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
	        setContent(mainlayout);
	        
	    }

	    @WebServlet(urlPatterns = "/*", name = "ViewEditServlet", asyncSupported = true)
	    @VaadinServletConfiguration(ui = ViewEdit.class, productionMode = false)
	    public static class ViewEditServlet extends VaadinServlet {
	    }

}



