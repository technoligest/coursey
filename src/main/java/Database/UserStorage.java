package Database;

import java.io.Serializable;

import com.vaadin.data.util.BeanItemContainer;

import coursy.onlineTools.CourseyUser;

public class UserStorage implements Serializable{
	BeanItemContainer<CourseyUser> coursey_users = new BeanItemContainer<CourseyUser>(CourseyUser.class);
	
	public BeanItemContainer<CourseyUser> getUsers(){
		return coursey_users;
	}
	
	public void addUser(CourseyUser u){
		coursey_users.addBean(u);
	}
	
	public void removeUser(CourseyUser u){
		coursey_users.removeItem(u.getUserID());
	}
}
