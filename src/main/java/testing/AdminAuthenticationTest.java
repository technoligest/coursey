package testing;
import java.util.*;
import org.junit.Test;

import db.CourseyUser;
import views.AdminLoginView;
import views.StartView;

import static org.junit.Assert.assertEquals;

public class AdminAuthenticationTest {

   //Sample data for testing
   List<CourseyUser> users = new ArrayList<CourseyUser>();
   StartView al = new StartView(null);
   
   //Search for existing username test
   @Test
   public void testUsernameSucess() {
	   	CourseyUser user1 = new CourseyUser();
	   	user1.setEmail("user1@coursey.com");
	   	CourseyUser user2 = new CourseyUser();
	   	user2.setEmail("user2@coursey.com");
	   	CourseyUser user3 = new CourseyUser();
		user3.setEmail("user3@coursey.com");
	   	users.add(user1);
	   	users.add(user2);
	   	users.add(user3);
   		boolean expectedResult = true;
   		boolean actualResult;
   		actualResult =!(al.findUsername(users, "user2@coursey.com") == -1);
   		
		assertEquals(actualResult, expectedResult);
   }
   
   //Search for non-existing username test
   @Test
   public void testUsernameFail() {
	   	CourseyUser user1 = new CourseyUser();
	   	user1.setEmail("user1@coursey.com");
	   	CourseyUser user2 = new CourseyUser();
	   	user2.setEmail("user2@coursey.com");
	   	CourseyUser user3 = new CourseyUser();
		user3.setEmail("user3@coursey.com");
	   	users.add(user1);
	   	users.add(user2);
	   	users.add(user3);
   		boolean expectedResult = true;
   		boolean actualResult;
   		actualResult =(al.findUsername(users, "un-existing@coursey.com") == -1);
   		
		assertEquals(actualResult, expectedResult);
   }
   
   //Search for a correct password
   @Test
   public void testCorrectPassword() {
	   	CourseyUser user1 = new CourseyUser();
	   	user1.setPassword("pass1");
	   	user1.setEmail("user1@coursey.com");
	   	CourseyUser user2 = new CourseyUser();
	   	user2.setPassword("pass2");
	   	user2.setEmail("user2@coursey.com");
	   	CourseyUser user3 = new CourseyUser();
		user3.setPassword("pass3");
		user3.setEmail("user3@coursey.com");
	   	users.add(user1);
	   	users.add(user2);
	   	users.add(user3);
  		boolean expectedResult= true;
  		boolean actualResult;
  		
  		int passwordIndex = al.findUsername(users, "user2@coursey.com");
  		String expectedPassword = users.get(passwordIndex).getPassword();
  				
  		if(al.findPassword(users, expectedPassword, passwordIndex) == true){
  			actualResult = true;
  		}
  		else{
  			actualResult = false;
  		}
		assertEquals(actualResult, expectedResult);
  }
  
  //Search for a wrong password
  @Test
  public void testIncorrectPassword() {
	   	CourseyUser user1 = new CourseyUser();
	   	user1.setPassword("pass1");
	   	user1.setEmail("user1@coursey.com");
	   	CourseyUser user2 = new CourseyUser();
	   	user2.setPassword("pass2");
	   	user2.setEmail("user2@coursey.com");
	   	CourseyUser user3 = new CourseyUser();
		user3.setPassword("pass3");
		user3.setEmail("user3@coursey.com");
	   	users.add(user1);
	   	users.add(user2);
	   	users.add(user3);
  		
  		boolean expectedResult = false;
  		boolean actualResult;
  		
  		int passwordIndex = al.findUsername(users, "user2@coursey.com");
  		String enteredPassword = "wrongPassword";
  				
  		if(al.findPassword(users, enteredPassword, passwordIndex) == true){
  			actualResult = true;
  		}
  		else{
  			actualResult = false;
  		}
  		
		assertEquals(actualResult, expectedResult);
  }
} 