package views;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import db.PendingUni;
import db.AcceptedUni;
import layouts.UniForm;
import objects.*;
/**
 * 
 * <h1>Admin Control Panel View</h1>
 * This is the view that shows the admin control panel.
 * @author Technoligest
 */

public class AdminControlPanelView extends VerticalLayout implements View{

	Navigator nv;
	TextField uniFilter= new TextField();
	Grid pendingList = new Grid();
	Grid uniList = new Grid();


	UniForm uniForm = new UniForm(this);

	UniService service1 = UniService.createPending();
	UniService service2 = UniService.createApproved();


	public UniService getService1(){
		return service1;
	}
	public UniService getService2(){
		return service2;
	}
	public Grid getPendingList(){
		return pendingList;
	}
	public Grid getUniList(){
		return uniList;
	}
	
	/**
	 * Add this view to the navigator of this application
	 * @param nv the navigator for this applications
	 */
	public  AdminControlPanelView(Navigator nv){
		this.nv =nv;

		//pending list
		pendingList.setContainerDataSource(new JPAContainer<>(PendingUni.class));
		pendingList.setColumnOrder("name", "city", "country"); 
		pendingList.removeColumn("email");
		pendingList.setSelectionMode(Grid.SelectionMode.SINGLE);
		pendingList.addSelectionListener( e -> uniForm.edit((PendingUni) pendingList.getSelectedRow()));

		//approved list
		uniList.setContainerDataSource(new JPAContainer<>(AcceptedUni.class));
		uniList.setColumnOrder("name", "city", "country"); 
		uniList.removeColumn("email");
		uniList.setSelectionMode(Grid.SelectionMode.SINGLE);
		uniList.addSelectionListener(e -> uniForm.edit2((AcceptedUni) uniList.getSelectedRow()));
		refreshContacts();

		HorizontalLayout uniActions= new HorizontalLayout(uniFilter);
		uniActions.setWidth("100%");
		uniFilter.setWidth("100%");
		uniActions.setExpandRatio(uniFilter, 1);

		VerticalLayout left = new VerticalLayout(pendingList);
		left.setSizeFull();
		pendingList.setSizeFull();
		left.setExpandRatio(pendingList, 1);

		VerticalLayout right = new VerticalLayout(uniActions,uniList);
		right.setSizeFull();
		uniList.setSizeFull();
		right.setExpandRatio(uniList, 1);

		Panel panel1 = new Panel("PENDING LIST");
		panel1.setWidth("800px");
		panel1.setContent(left);

		Panel panel2 = new Panel("ACCEPTED LIST");
		panel2.setWidth("800px");
		panel2.setContent(right);

		VerticalLayout KLayout = new VerticalLayout(panel1, panel2);

		HorizontalLayout mainLayout = new HorizontalLayout(KLayout,uniForm);
		mainLayout.setExpandRatio(uniForm, 1);
		addComponent(mainLayout);
	}
	
	/**
	 * Wrapper class for the refresh contacts
	 */
	public void refreshContacts() {
		refreshContacts(uniFilter.getValue());
	}
	
	/**
	 * Refreshes the contacts in this view.
	 * @param stringFilter The inputted contact list
	 */
	private void refreshContacts(String stringFilter) {
		pendingList.setContainerDataSource(new BeanItemContainer<>(
				PendingUni.class, service1.findAllPendingUniversitiesJPA()));
		uniList.setContainerDataSource(new BeanItemContainer<>(
				AcceptedUni.class, service2.findAllAcceptedUniversitiesJPA()));
		uniForm.setVisible(false);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}
}
