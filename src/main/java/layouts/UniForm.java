package layouts;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

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
	Uni uni;
	AcceptedUni AUni;

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
			Notification.show(uni.getUniversityName() + " has been APPROVED!", Type.TRAY_NOTIFICATION);
			acpv.getUniList().addRow(uni);
		} catch (Exception e) {
			// Validation exceptions could be shown here
		}
		acpv.getService1().delete(uni);
		acpv.refreshContacts();
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
	public void edit(Uni uni) {
		AcceptedActions.setVisible(false);
		PendingActions.setVisible(true);
		this.uni = uni;
		if (uni != null) {
			uniName.setValue("University: "+uni.getUniversityName());
			City.setValue("City: "+uni.getCity());
			Country.setValue("Country: "+uni.getCountry());
			email.setValue("Email: "+uni.getEmail());



		}
		setVisible(uni != null);
	}
	/**
	 * displays information about the approved university
	 * @param AcceptedUni
	 */
	public void edit2(AcceptedUni acceptedUni) {
		AcceptedActions.setVisible(true);
		PendingActions.setVisible(false);
		this.AUni=acceptedUni;
		if (acceptedUni != null) {
			uniName.setValue("University: "+acceptedUni.getUniversityName2());
			City.setValue("City: "+acceptedUni.getCity2());
			Country.setValue("Country: "+acceptedUni.getCountry2());
			email.setValue("Email: "+acceptedUni.getEmail2());
		}
		setVisible(acceptedUni != null);
	}

	private void deny(Button.ClickEvent event) {
		acpv.getService1().delete(uni);
		String msg = String.format(uni.getUniversityName()+" DENIED ");
		Notification.show(msg, Type.TRAY_NOTIFICATION);
		acpv.refreshContacts();
	}
	

	private void delete(Button.ClickEvent event) {
		acpv.getService2().delete2(AUni);
		String msg = String.format(AUni.getUniversityName2()+" DELETED ");
		Notification.show(msg, Type.TRAY_NOTIFICATION);
		acpv.refreshContacts();
	}
}
