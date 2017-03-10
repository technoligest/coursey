package coursy.onlineTools;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;


/** Separate Java service class.
 * Backend implementation for the address book application, with "detached entities"
 * simulating real world DAO. Typically these something that the Java EE
 * or Spring backend services provide.
 */
// Backend service class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class UniService {

    // Create dummy data
    //Pending uni lists
    static ArrayList<String>PUNames = new ArrayList<String>(4);
    static ArrayList<String>PCity = new ArrayList<String>(4);
    static ArrayList<String>PCountry = new ArrayList<String>(4);
    //approved uni lists
    static ArrayList<String>AUNames = new ArrayList<String>();
    static ArrayList<String>ACity = new ArrayList<String>();
    static ArrayList<String>ACountry = new ArrayList<String>();

    private static UniService instance;
    private static UniService instance2;

    public static UniService createDemoService1() {
    	PUNames.add("ABC");
    	PUNames.add("DEF");
    	PUNames.add("GHI");
    	PCity.add("City");
    	PCity.add("City2");
    	PCity.add("City3");
    	PCountry.add("Country");
    	PCountry.add("Country2");
    	PCountry.add("Country3");
        if (instance == null) {

            final UniService contactService = new UniService();

            Random r = new Random(0);
            int test = 1;
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < PUNames.size(); i++) {
                Uni contact = new Uni();
                //contact.setUniversityName(PuniNames[r.nextInt(PuniNames.length)]);
                contact.setUniversityName(PUNames.get(i));
                //contact.setCity(PcityNames[r.nextInt(PuniNames.length)]);
                contact.setCity(PCity.get(i));
                //contact.setCountry(PcountryNames[r.nextInt(PuniNames.length)]);
                contact.setCountry(PCountry.get(i));
                contact.setEmail("info@" + contact.getUniversityName().toLowerCase() + ".com");
                contact.setTask("TASK #" + test);
                cal.set(1930 + r.nextInt(70),
                        r.nextInt(11), r.nextInt(28));
                contact.setStartDate(cal.getTime());
                contact.setEndDate(cal.getTime());
                test++;
                contactService.save(contact);
            }
            instance = contactService;
        }

        return instance;
    }
    
    public static UniService createDemoService2() {
    	AUNames.add("DAL");
    	AUNames.add("SMU");
    	AUNames.add("MSVU");
    	AUNames.add("-");
    	ACity.add("HALIFAX");
    	ACity.add("NEW YORK");
    	ACity.add("RIO");
    	ACity.add("-");
    	ACountry.add("CANADA");
    	ACountry.add("USA");
    	ACountry.add("BRAZIL");
    	ACountry.add("-");
        if (instance2 == null) {

            final UniService contactService = new UniService();

            Random r = new Random(0);
            int test = 1;
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < AUNames.size(); i++) {
                AcceptedUni contact = new AcceptedUni();
                //contact.setUniversityName2(AuniNames[r.nextInt(AuniNames.length)]);
                contact.setUniversityName2(AUNames.get(i));
                //contact.setCity2(AcityNames[r.nextInt(AuniNames.length)]);
                contact.setCity2(ACity.get(i));
                //contact.setCountry2(AcountryNames[r.nextInt(AuniNames.length)]);
                contact.setCountry2(ACountry.get(i));
                contact.setEmail2("info@" + contact.getUniversityName2().toLowerCase() + ".com");
                contact.setTask2("TASK #" + test);
                cal.set(1930 + r.nextInt(70),
                        r.nextInt(11), r.nextInt(28));
                contact.setStartDate2(cal.getTime());
                contact.setEndDate2(cal.getTime());
                test++;
                contactService.save2(contact);
            }
            instance2 = contactService;
        }

        return instance2;
    }

    private HashMap<Long, Uni> unis = new HashMap<>();
    private HashMap<Long, AcceptedUni> unis2 = new HashMap<>();
    private long nextId = 0;

    public synchronized List<Uni> findAll(String stringFilter) {
        ArrayList arrayList = new ArrayList();
        for (Uni contact : unis.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(contact.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(UniService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Uni>() {

            @Override
            public int compare(Uni o1, Uni o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }
    
    public synchronized List<AcceptedUni> findAllAccepted(String stringFilter) {
        ArrayList arrayList = new ArrayList();
        for (AcceptedUni contact : unis2.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(contact.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(UniService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<AcceptedUni>() {

            @Override
            public int compare(AcceptedUni o1, AcceptedUni o2) {
                return (int) (o2.getId2() - o1.getId2());
            }
        });
        return arrayList;
    }

    public synchronized long count() {
        return unis.size();
    }

    public synchronized void delete(Uni value) {
        unis.remove(value.getId());
    }
    
    public synchronized void delete2(AcceptedUni value) {
        unis2.remove(value.getId2());
    }


    public synchronized void save(Uni entry) {
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Uni) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        unis.put(entry.getId(), entry);
    }
    
    public synchronized void save2(AcceptedUni entry) {
        if (entry.getId2() == null) {
            entry.setId2(nextId++);
        }
        try {
            entry = (AcceptedUni) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        unis2.put(entry.getId2(), entry);
    }

}
