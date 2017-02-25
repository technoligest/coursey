package coursy.onlineTools;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.TextField;


@Title("Coursy")
@Theme("valo")
public class MyUI extends UI {


	//TextField filter = new TextField();
    TextField uniFilter= new TextField();
    Grid pendingList = new Grid();
    Grid uniList = new Grid();
    //Button newContact = new Button("New Task");

    // ContactForm is an example of a custom component class
    UniForm uniForm = new UniForm();

    // ContactService is a in-memory mock DAO that mimics
    // a real-world datasource. Typically implemented for
    // example as EJB or Spring Data based service.
    UniService service1 = UniService.createDemoService1();
    UniService service2 = UniService.createDemoService2();

    /*
     * The "Main method".
     *
     * This is the entry point method executed to initialize and configure the
     * visible user interface. Executed on every browser reload because a new
     * instance is created for each web page loaded.
     */
    @Override
    protected void init(VaadinRequest request) {
        configureComponents();
        buildLayout();
    }

   
	private void configureComponents() {
        /*
         * Synchronous event handling.
         *
         * Receive user interaction events on the server-side. This allows you
         * to synchronously handle those events. Vaadin automatically sends only
         * the needed changes to the web page without loading a new page.
         */
        //newContact.addClickListener(e -> contactForm.edit(new Contact()));

        //filter.setInputPrompt("Filter pending schools..");
        //filter.addTextChangeListener(e -> refreshContacts(e.getText()));
        
        uniFilter.setInputPrompt("Filter approved schools...");
        uniFilter.addTextChangeListener(e -> refreshContacts(e.getText()));

        
        pendingList.setContainerDataSource(new BeanItemContainer<>(Uni.class));
        pendingList.setColumnOrder("universityName", "city", "country", "task"); 
        pendingList.removeColumn("id");
        pendingList.removeColumn("email");
        pendingList.removeColumn("startDate");
        pendingList.removeColumn("endDate");
        pendingList.setSelectionMode(Grid.SelectionMode.SINGLE);
        pendingList.addSelectionListener( e -> uniForm.edit((Uni) pendingList.getSelectedRow()));
        refreshContacts();
     
        uniList.setContainerDataSource(new BeanItemContainer<>(AcceptedUni.class));
        uniList.setColumnOrder("universityName", "city", "country", "task"); 
        uniList.removeColumn("id");
        uniList.removeColumn("email");
        uniList.removeColumn("startDate");
        uniList.removeColumn("endDate");
        uniList.setSelectionMode(Grid.SelectionMode.SINGLE);
        uniList.addSelectionListener(e -> uniForm.edit2((Uni) uniList.getSelectedRow()));
        refreshContacts();
       
    }


    
    //PANEL
    private void buildLayout() {
   	 //HorizontalLayout actions = new HorizontalLayout(filter);
   	 HorizontalLayout uniActions= new HorizontalLayout(uniFilter);
        //actions.setWidth("100%");
        uniActions.setWidth("100%");
        uniFilter.setWidth("100%");
        //filter.setWidth("100%");
        //actions.setExpandRatio(filter, 1);
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
        
        //mainLayout.setExpandRatio(left, 1);

        // Split and allow resizing
       // setContent(mainLayout);
        HorizontalLayout mainLayout = new HorizontalLayout(KLayout,uniForm);
        mainLayout.setExpandRatio(uniForm, 1);
        setContent(mainLayout);
   }

    /*
     * Choose the design patterns you like.
     *
     * It is good practice to have separate data access methods that handle the
     * back-end access and/or the user interface updates. You can further split
     * your code into classes to easier maintenance. With Vaadin you can follow
     * MVC, MVP or any other design pattern you choose.
     */
    void refreshContacts() {
        //refreshContacts(filter.getValue());
        refreshContacts(uniFilter.getValue());
    }

    private void refreshContacts(String stringFilter) {
    	pendingList.setContainerDataSource(new BeanItemContainer<>(
                Uni.class, service1.findAll(stringFilter)));
    	uniList.setContainerDataSource(new BeanItemContainer<>(
    			AcceptedUni.class, service2.findAllAccepted(stringFilter)));
        uniForm.setVisible(false);
    }

    /*
     * Deployed as a Servlet or Portlet.
     *
     * You can specify additional servlet parameters like the URI and UI class
     * name and turn on production mode when you have finished developing the
     * application.
     */
    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
