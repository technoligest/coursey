package Database;

import com.vaadin.data.util.BeanItemContainer;

import coursy.onlineTools.AcceptedUni;
import coursy.onlineTools.Uni;

public class UniStorage {
	
	//universities holds instances of the uni class, which will be pending
	BeanItemContainer<Uni>  universities = new BeanItemContainer<Uni>(Uni.class);

	public BeanItemContainer<Uni> getUnis(){
		return universities;
	}
	
	public void addUni(Uni u){
		universities.addBean(u);
	}
	
	public void removeUni(Uni u){
		universities.removeItem(u.getId());
	}
	
	//Container to hold the accepted universities, which are instances of the accepted uni class
	BeanItemContainer<AcceptedUni> accepted_universities = new BeanItemContainer<AcceptedUni>(AcceptedUni.class);
	
	public BeanItemContainer<AcceptedUni> getAcceptedUnis(){
		return accepted_universities;
	}
	
	public void addAcceptedUni(AcceptedUni u){
		accepted_universities.addItem(u);
	}
	
	public void removeAcceptedUni(AcceptedUni u){
		accepted_universities.removeItem(u.getId2());
	}
}//ends UniStorage class 






