package views;

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

import layouts.UniForm;
import objects.*;


public class AdminControlPanelView extends VerticalLayout implements View{
	//Database db = new Database();

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
	public  AdminControlPanelView(Navigator nv){
		this.nv =nv;
		uniFilter.setInputPrompt("Filter approved schools...");
		uniFilter.addTextChangeListener(e -> refreshContacts(e.getText()));

		//pending list
		pendingList.setContainerDataSource(new BeanItemContainer<>(Uni.class));
		pendingList.setColumnOrder("universityName", "city", "country"); 
		pendingList.removeColumn("id");
		pendingList.removeColumn("email");
		pendingList.setSelectionMode(Grid.SelectionMode.SINGLE);
		pendingList.addSelectionListener( e -> uniForm.edit((Uni) pendingList.getSelectedRow()));

		//approved list
		uniList.setContainerDataSource(new BeanItemContainer<>(AcceptedUni.class));
		uniList.setColumnOrder("universityName2", "city2", "country2"); 
		uniList.removeColumn("id2");
		uniList.removeColumn("email2");
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

	public void refreshContacts() {
		refreshContacts(uniFilter.getValue());
	}

	private void refreshContacts(String stringFilter) {
		pendingList.setContainerDataSource(new BeanItemContainer<>(
				Uni.class, service1.findAll(null)));
		uniList.setContainerDataSource(new BeanItemContainer<>(
				AcceptedUni.class, service2.findAllAccepted(stringFilter)));
		uniForm.setVisible(false);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}
}
