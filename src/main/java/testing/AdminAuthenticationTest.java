package testing;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AdminAuthenticationTest {

//   //Sample data for testing
//   ArrayList<String> sampleAdmins = new ArrayList<String>();
//   ArrayList<String> samplePasswords = new ArrayList<String>();
//   adminLogin al = new adminLogin();
//   
//   //Search for existing username test
//   @Test
//   public void testUsernameSucess() {
//	   	sampleAdmins.add("admin1");
//   		sampleAdmins.add("admin2");
//   		sampleAdmins.add("admin3");
//   		boolean expectedResult = true;
//   		boolean actualResult;
//   		if(al.findUsername(sampleAdmins, "admin2") == -1){
//   			actualResult = false;
//   		}
//   		else{
//   			actualResult = true;
//   		}
//   		
//		assertEquals(actualResult, expectedResult);
//   }
//   
//   //Search for non-existing username test
//   @Test
//   public void testUsernamefail() {
//	   	sampleAdmins.add("admin1");
//   		sampleAdmins.add("admin2");
//   		sampleAdmins.add("admin3");
//   		boolean expectedResult = false;
//   		boolean actualResult;
//   		if(al.findUsername(sampleAdmins, "non-existing") == -1){
//   			actualResult = false;
//   		}
//   		else{
//   			actualResult = true;
//   		}
//   		
//		assertEquals(actualResult, expectedResult);
//   }
//   
//   //Search for a correct password
//   @Test
//   public void testCorrectPassword() {
//	   	sampleAdmins.add("admin1");
//  		sampleAdmins.add("admin2");
//  		sampleAdmins.add("admin3");
//  		samplePasswords.add("pass1");
//  		samplePasswords.add("pass2");
//  		samplePasswords.add("pass3");
//  		
//  		boolean expectedResult= true;
//  		boolean actualResult;
//  		
//  		int passwordIndex = al.findUsername(sampleAdmins, "admin2");
//  		String expectedPassword = samplePasswords.get(passwordIndex);
//  				
//  		if(al.findPassword(samplePasswords, expectedPassword, passwordIndex) == true){
//  			actualResult = true;
//  		}
//  		else{
//  			actualResult = false;
//  		}
//  		
//		assertEquals(actualResult, expectedResult);
//  }
//   
//  //Search for a wrong password
//  @Test
//  public void testPassword() {
//	   	sampleAdmins.add("admin1");
//  		sampleAdmins.add("admin2");
//  		sampleAdmins.add("admin3");
//  		samplePasswords.add("pass1");
//  		samplePasswords.add("pass2");
//  		samplePasswords.add("pass3");
//  		
//  		boolean expectedResult = false;
//  		boolean actualResult;
//  		
//  		int passwordIndex = al.findUsername(sampleAdmins, "admin2");
//  		String enteredPassword = "wrongPassword";
//  				
//  		if(al.findPassword(samplePasswords, enteredPassword, passwordIndex) == true){
//  			actualResult = true;
//  		}
//  		else{
//  			actualResult = false;
//  		}
//  		
//		assertEquals(actualResult, expectedResult);
//  }

} 