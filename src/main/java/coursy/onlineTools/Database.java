package coursy.onlineTools;
import java.util.*;
public class Database {
	ArrayList<CourseyUser> courseyUsers = new ArrayList<>();
	public void add(CourseyUser u){
		if(u!=null)
			courseyUsers.add(u);
	}
}
