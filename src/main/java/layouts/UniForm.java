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

	// Easily bind forms to beans and manage validation and buffering
	BeanFieldGroup<Uni> formFieldBindings;

	/**
	 * build layout
	 * @param acpv
	 */
	public UniForm(AdminControlPanelView acpv) {
		this.acpv=acpv;
		configureComponents();
		buildLayout();
	}

	private void configureComponents() {
		/*
		 * Highlight primary actions.
		 *
		 * With Vaadin built-in styles you can highlight the primary save button
		 * and give it a keyboard shortcut for a better UX.
		 */
		approve.setStyleName(ValoTheme.BUTTON_PRIMARY);
		approve.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		setVisible(false);
	}

	private void buildLayout() {
		setSizeUndefined();
		setMargin(true);

		//HorizontalLayout actions = new HorizontalLayout(approve, cancel, deny);
		PendingActions.setSpacing(true);
		//HorizontalLayout actions2 = new HorizontalLayout(cancel, delete);
		AcceptedActions.setSpacing(true);

		addComponents(PendingActions, uniName, City, Country, email);
		addComponents(AcceptedActions, uniName, City, Country, email);

	}


	/**
	 * approves a university (takes it away from pending and store it in approved)
	 * @param event
	 */
	

	public void approve(Button.ClickEvent event) {
		try {
			Notification.show(uni.getName() + " has been APPROVED!", Type.TRAY_NOTIFICATION);
			Connection connect;
			Statement state = null;
			connect = DriverManager.getConnection(url, USER, PASS);
			state = connect.createStatement();
			String id = uni.getName();
		    String sqlDelete = "DELETE FROM `coursey_db`.`pending_unis` WHERE `name`='"+uni.getName()+"';";
		    String sqlAdd = "INSERT INTO `coursey_db`.`accepted_unis` (`name`, `city`, `country`, `email`) VALUES ('"+uni.getName()+"', '"+uni.getCity()+"', '"+uni.getCountry()+"', '"+uni.getEmail()+"');";
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
	 * @param event
	 */
	public void cancel(Button.ClickEvent event) {
		// Place to call business logic.
		Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
		acpv.getPendingList().select(null);
		acpv.getUniList().select(null);
	}

	/**
	 * displays information about the pending university
	 * @param Uni 
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
	 * @param AcceptedUni
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

	private void deny(Button.ClickEvent event) {
		Connection connect;
		Statement state;
		try {
			Notification.show(uni.getName() + " has been DENIED!", Type.TRAY_NOTIFICATION);
			connect = DriverManager.getConnection(url, USER, PASS);
			state = connect.createStatement();
		    String sqlDelete = "DELETE FROM `coursey_db`.`pending_unis` WHERE `name`='"+uni.getName()+"';";
		    state.executeUpdate(sqlDelete);
			acpv.refreshContacts();
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

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
}
