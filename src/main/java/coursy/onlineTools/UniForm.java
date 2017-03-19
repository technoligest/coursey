package coursy.onlineTools;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/* Create custom UI Components.
 *
 * Create your own Vaadin components by inheritance and composition.
 * This is a form component inherited from VerticalLayout. Use
 * Use BeanFieldGroup to bind data fields from DTO to UI fields.
 * Similarly named field by naming convention or customized
 * with @PropertyId annotation.
 */
public class UniForm extends FormLayout {
	
	
	
	Button approve = new Button("Approve", this::approve);
    Button cancel = new Button("Cancel", this::cancel);
    Button deny = new Button("Deny", this::deny);
    Button delete = new Button("Delete", this::delete);
    Label uniName = new Label();
    Label City = new Label();
    Label Country = new Label();
    Label email = new Label();
    HorizontalLayout actions = new HorizontalLayout(approve, cancel, deny); 
    HorizontalLayout actions2 = new HorizontalLayout(cancel, delete);
    Uni uni;
    AcceptedUni AUni;

    // Easily bind forms to beans and manage validation and buffering
   BeanFieldGroup<Uni> formFieldBindings;
    
    
    public UniForm() {
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
        actions.setSpacing(true);
        //HorizontalLayout actions2 = new HorizontalLayout(cancel, delete);
        actions2.setSpacing(true);

        addComponents(actions, uniName, City, Country, email);
        addComponents(actions2, uniName, City, Country, email);
        
    }

    /*
     * Use any JVM language.
     *
     * Vaadin supports all languages supported by Java Virtual Machine 1.6+.
     * This allows you to program user interface in Java 8, Scala, Groovy or any
     * other language you choose. The new languages give you very powerful tools
     * for organizing your code as you choose. For example, you can implement
     * the listener methods in your compositions or in separate controller
     * classes and receive to various Vaadin component events, like button
     * clicks. Or keep it simple and compact with Lambda expressions.
     */
    public void approve(Button.ClickEvent event) {
        try {
        	Notification.show(uni.getUniversityName() + " has been APPROVED!", Type.TRAY_NOTIFICATION);
        	getUI().uniList.addRow(uni);
        } catch (Exception e) {
            // Validation exceptions could be shown here
        }
        getUI().service1.delete(uni);
    	getUI().refreshContacts();
    }


    public void cancel(Button.ClickEvent event) {
        // Place to call business logic.
        Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
        getUI().pendingList.select(null);
        getUI().uniList.select(null);
    }

    void edit(Uni uni) {
    	actions2.setVisible(false);
    	actions.setVisible(true);
        this.uni = uni;
        if (uni != null) {
        	uniName.setValue("University: "+uni.getUniversityName());
        	City.setValue("City: "+uni.getCity());
        	Country.setValue("Country: "+uni.getCountry());
        	email.setValue("Email: "+uni.getEmail());
        	
        	
          
        }
        setVisible(uni != null);
    }
    void edit2(AcceptedUni acceptedUni) {
    	actions2.setVisible(true);
    	actions.setVisible(false);
    	this.AUni=acceptedUni;
    	 if (acceptedUni != null) {
         	uniName.setValue("University: "+acceptedUni.getUniversityName2());
         	City.setValue("City: "+acceptedUni.getCity2());
         	Country.setValue("Country: "+acceptedUni.getCountry2());
         	email.setValue("Email: "+acceptedUni.getEmail2());
         	
         	
         	
             // Bind the properties of the contact POJO to fields in this form
            // formFieldBindings = BeanFieldGroup.bindFieldsUnbuffered(contact,
             //        this);
           
         }
         setVisible(acceptedUni != null);
    	
    }
    
    private void deny(Button.ClickEvent event) {
        getUI().service1.delete(uni);
        String msg = String.format(uni.getUniversityName()+" DENIED ");
        Notification.show(msg, Type.TRAY_NOTIFICATION);
        getUI().refreshContacts();
    }
    private void delete(Button.ClickEvent event) {
        getUI().service2.delete2(AUni);
        String msg = String.format(AUni.getUniversityName2()+" DELETED ");
        Notification.show(msg, Type.TRAY_NOTIFICATION);
        getUI().refreshContacts();
    }

    @Override
    public AdminControlPanel getUI() {
        return (AdminControlPanel) super.getUI();
    }

}
