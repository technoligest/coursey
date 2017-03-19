package coursy.onlineTools;

import java.awt.List;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;

import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import Database.UniStorage;

import com.vaadin.ui.TextField;


@Title("Coursy")
@Theme("valo")
public class AdminControlPanel extends UI {
	
	//creating an instance of UniStorage to hold the pending and accepted universities
	UniStorage storage = new UniStorage();
	
	
	TextField uniFilter= new TextField();
    Grid pendingList = new Grid();
    Grid uniList = new Grid();


    UniForm uniForm = new UniForm();

    UniService service1 = UniService.createDemoService1();
    UniService service2 = UniService.createDemoService2();


    @Override
    protected void init(VaadinRequest request) {
        configureComponents();
        buildLayout();
    }

   
  private void configureComponents() {
        
        uniFilter.setInputPrompt("Filter approved schools...");
        uniFilter.addTextChangeListener(e -> refreshContacts(e.getText()));

        //pending list uses a UniStorage Container as a source
        pendingList.setContainerDataSource(storage.getUnis());
        pendingList.setColumnOrder("universityName", "city", "country", "task"); 
        pendingList.removeColumn("id");
        pendingList.removeColumn("email");
        pendingList.removeColumn("startDate");
        pendingList.removeColumn("endDate");
        pendingList.setSelectionMode(Grid.SelectionMode.SINGLE);
        pendingList.addSelectionListener( e -> uniForm.edit((Uni) pendingList.getSelectedRow()));
       ArrayList<Uni> sampleUni = new ArrayList<Uni>();
       for(int i = 0; i < sampleUni.size(); i++){
    	   storage.addUni(sampleUni.get(i));
       }
        
        //approved list uses a UniStorage Container as a source
        uniList.setContainerDataSource(storage.getAcceptedUnis());
        uniList.setColumnOrder("universityName2", "city2", "country2", "task2"); 
        uniList.removeColumn("id2");
        uniList.removeColumn("email2");
        uniList.removeColumn("startDate2");
        uniList.removeColumn("endDate2");
        uniList.setSelectionMode(Grid.SelectionMode.SINGLE);
        uniList.addSelectionListener(e -> uniForm.edit2((AcceptedUni) uniList.getSelectedRow()));
        ArrayList<AcceptedUni> sampleAccepted = (ArrayList<AcceptedUni>) service2.findAllAccepted(null);
        for(int i = 0; i < sampleAccepted.size(); i++){
        	storage.addAcceptedUni(sampleAccepted.get(i));
        }
        refreshContacts();
       
    }


    
    //PANEL
  private void buildLayout() {
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
        setContent(mainLayout);
   }

    void refreshContacts() {
        refreshContacts(uniFilter.getValue());
    }
   
    //refreshContacts needs some additional work 
    private void refreshContacts(String stringFilter) {
      pendingList.setContainerDataSource(storage.getUnis());
      uniList.setContainerDataSource(storage.getAcceptedUnis());
        uniForm.setVisible(false);
    }

    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = AdminControlPanel.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
  }