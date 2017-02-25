// both junit-4.12.jar and hamcrest-core-1.3.jar need to be added to the class build path to run JUnit tests

package coursy.onlineTools;

import org.apache.commons.beanutils.BeanUtils;
import java.io.Serializable;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJUnit {
	
  
   //testing the uni class
	//first test
   @Test
   public void creating_printing_cloning_Uni() throws CloneNotSupportedException {
	   String uniName = "Dal";
	   long id = 999;
	   String city = "Halifax";
	   String task = "task";
	   String email = "email";
	   Date date = new Date();
	   Date startDate = date;
	   Date endDate = date;
	   Uni dal = new Uni();
	   dal.setUniversityName(uniName);
	   dal.setCity(city);
	   dal.setId(id);
	   dal.setEmail(email);
	   dal.setTask(task);
	   dal.setStartDate(startDate);
	   dal.setEndDate(endDate);
	   
	   String str = "Contact{" + "id=" + id + ", UniversityName=" + uniName
               + ", city=" + city + ", task=" + task + ", email="
               + email + ", startDate=" + startDate + ", endDate=" +endDate+ "}";
	   
	   //testing the toString method
	   assertEquals(str,dal.toString());
	   
	   //testing the clone method
	   
	  Uni uni2 = dal.clone();
	   
	  assertEquals(str,uni2.toString());
   }//ends the Uni test

   @Test
   public void UniForm_test(){
	   UniForm form = new UniForm();
	   //ensuring everything exists
	   assertNotNull(form);
	   assertNotNull(form.uniName);
	   assertNotNull(form.cancel);
	   assertNotNull(form.City);
	   assertNotNull(form.Country);
	   assertNotNull(form.remove);
	   assertNotNull(form.save);
	   
	   //testing functionality of a textfield and a button
	   String str = "dal";
	   form.uniName.setData(str);
	   assertEquals(str, form.uniName.getData());
	   //cancel should clear the textfield
	   form.cancel.click();
	   
	   assertNull(form.uniName.getData());
	   
	   
   }//ends UniForm test
   
   @Test 
   public void UniService_test(){
	   UniService service = new UniService();
	   assertNotNull(service);
	   
	   //creating a demo also tests the save function
	   UniService demo = UniService.createDemoService();
	   assertNotNull(demo);
	   
	   Uni uni = new Uni();
	   long id = 777;
	   uni.setId(id);
	   demo.save(uni);
	   
	   long demo_contacts = 6; //from the UniService demo code + new uni added
	   assertEquals(demo_contacts, demo.count());
	   //testing the delete function
	   demo.delete(uni);
	   demo_contacts = 5;
	   assertEquals(demo_contacts, demo.count());
   }//ends UniService Test
   
   
   @Test
   public void MyUi_test(){
	   MyUI ui = new MyUI();
	   assertNotNull(ui);
	   
	   //by naming components of the UI, it would be possible to access them individually and get better code coverage
	   
   }//ends testing if MyUi
   
   

}//ends JUnit class