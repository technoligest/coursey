package layouts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

import db.PendingUni;
import db.AcceptedUni;
import objects.*;
import views.AdminControlPanelView;
/**
 * <h1>Universities Layout</h1>
 * This is a layout that allows us to view universities and do the functions on them
 * @author Technoligest
 *
 */

public class UniForm extends FormLayout {

	AdminControlPanelView acpv;
	Button approve = new Button("Approve", this::approve);
	Button cancel = new Button("Cancel", this::cancel);
	Button deny = new Button("Deny", this::deny);
	Button delete = new Button("Delete", this::delete);
	Label uniName = new Label();
	Label City = new Label();
	Label Country = new Label();
	Label email = new Label();
	HorizontalLayout PendingActions = new HorizontalLayout(approve, cancel, deny); 
	HorizontalLayout AcceptedActions = new HorizontalLayout(cancel, delete);
	PendingUni uni;
	AcceptedUni AUni;
	
	static final String url = "jdbc:mysql://localhost:3306/coursey_db?zeroDateTimeBehavior=convertToNull";
	static final String USER = "root";
	static final String PASS = "negahban";
	
	
	/**
	 * build layout
	 * @param acpv This is the view that will dispaly the universities
	 */
	public UniForm(AdminControlPanelView acpv) {
		this.acpv=acpv;
		configureComponents();
		buildLayout();
	}
	
	/**
	 * Configures the components to show them properly.
	 */
	private void configureComponents() {
		approve.setStyleName(ValoTheme.BUTTON_PRIMARY);
		approve.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		setVisible(false);
	}

	/**
	 * This methods builds the layout for displayign the universities
	 */
	private void buildLayout() {
		setSizeUndefined();
		setMargin(true);

		PendingActions.setSpacing(true);
		AcceptedActions.setSpacing(true);

		addComponents(PendingActions, uniName, City, Country, email);
		addComponents(AcceptedActions, uniName, City, Country, email);

	}


	/**
	 * approves a university (takes it away from pending and store it in approved)
	 * @param event which is the event of the approve button being clicked
	 */
	public void approve(Button.ClickEvent event) {
		try {
			Notification.show(uni.getName() + " has been APPROVED!", Type.TRAY_NOTIFICATION);
			Connection connect;
			Statement state = null;
			connect = DriverManager.getConnection(url, USER, PASS);
			state = connect.createStatement();
			String id = uni.getName();
			String sqlDelete = "DELETE "
		    		+ "FROM `coursey_db`.`pending_unis` "
		    		+ "WHERE `name`='"+doubleQuote(uni.getName())+"';";
		    String sqlAdd = "INSERT "
		    		+ "INTO `coursey_db`.`accepted_unis` "
		    		+ "(`name`, `city`, `country`, `email`) "
		    		+ "VALUES ('"+uni.getName()+"', '"+uni.getCity()+"', '"+uni.getCountry()+"', '"+uni.getEmail()+"');";
		   
		    state.executeUpdate(sqlDelete);
			state.executeUpdate(sqlAdd);
			connect.close();
			acpv.refreshContacts();
			
		} catch (Exception e) {
			// Validation exceptions could be shown here
		}
	}

	/**
	 * make pop up disappear
	 * @param event the click of the cancel button
	 */
	public void cancel(Button.ClickEvent event) {
		// Place to call business logic.
		Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
		acpv.getPendingList().select(null);
		acpv.getUniList().select(null);
	}

	
	/**
	 * displays information about the pending university
	 * @param pendingUni a university to show
	 */
	public void edit(PendingUni pendingUni) {
		AcceptedActions.setVisible(false);
		PendingActions.setVisible(true);
		this.uni = pendingUni;
		if (pendingUni != null) {
			uniName.setValue("University: "+ uni.getName());
			City.setValue("City: "+uni.getCity());
			Country.setValue("Country: "+uni.getCountry());
			email.setValue("Email: "+uni.getEmail());
		}
		setVisible(pendingUni != null);
	}
	
	
	/**
	 * displays information about the approved university
	 * @param acceptedUni a university to show
	 */
	public void edit2(db.AcceptedUni acceptedUni) {
		AcceptedActions.setVisible(true);
		PendingActions.setVisible(false);
		this.AUni=acceptedUni;
		if (acceptedUni != null) {
			uniName.setValue("University: "+AUni.getName());
			City.setValue("City: "+AUni.getCity());
			Country.setValue("Country: "+AUni.getCountry());
			email.setValue("Email: "+AUni.getEmail());
		}
		setVisible(acceptedUni != null);
	}
	
	
	/**
	 * This method helps us deny a university by deleting it.
	 * 
	 * @param event which is a button click event for the deny button
	 */
	private void deny(Button.ClickEvent event) {
		Connection connect;
		Statement state;
		try {
			Notification.show(uni.getName() + " has been DENIED!", Type.TRAY_NOTIFICATION);
			connect = DriverManager.getConnection(url, USER, PASS);
			state = connect.createStatement();
			
		    String sqlDelete = "DELETE "
		    		+ "FROM `coursey_db`.`pending_unis` "
		    		+ "WHERE `name`='"+uni.getName()+"';";
		    
		    state.executeUpdate(sqlDelete);
			acpv.refreshContacts();
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This helps us delete a university if the button is pressed in the UI
	 * @param event is the event of the button click from the UI
	 */
	private void delete(Button.ClickEvent event){
		String msg = String.format(AUni.getName()+" DELETED ");
		Connection connect;
		Statement state;
		try {
			connect = DriverManager.getConnection(url, USER, PASS);
			state = connect.createStatement();
		    String sqlDelete = "DELETE FROM `coursey_db`.`accepted_unis` WHERE `name`='"+AUni.getName()+"';";
		    state.executeUpdate(sqlDelete);
		    Notification.show(msg, Type.TRAY_NOTIFICATION);
			acpv.refreshContacts();
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method helps us escape single quotes for a given string.
	 * 
	 * @param input a given string which might or might not include single quotes.
	 * @return String which is the same inputted String but with the single quotes escaped
	 */
	private static String doubleQuote(String input){
		String result="";
		for(int i=0; i<input.length()-1;++i){
			result+=input.charAt(i);
			if(input.charAt(i)=='\'' ){
				result+='\'';
			}
		}
		return result;
	}
}
