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

    // Create dummy data by randomly combining first and last names
    static String[] PuniNames = { "ABC", "DEF", "GHI", "JKL", "MNO"};
    
    static String[] PcityNames = { "CITY1", "CITY2", "CITY3", "CITY4", "CITY5"};
    
    static String[] PcountryNames = { "COUNTRY1", "COUNTRY2", "COUNTRY3", "COUNTRY4", "COUNTRY5"};
    
    static String[] AuniNames = { "DAL", "SMU", "NASCAD", "MSVU", "NSCC"};
    
    static String[] AcityNames = { "Halifax", "New York", "Somewhere", "London", "Madrid"};
    
    static String[] AcountryNames = { "Canada", "USA", "Nowhere", "UK", "Spain"};

    private static UniService instance;
    private static UniService instance2;

    public static UniService createDemoService1() {
        if (instance == null) {

            final UniService contactService = new UniService();

            Random r = new Random(0);
            int test = 1;
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 6; i++) {
                Uni contact = new Uni();
                contact.setUniversityName(PuniNames[r.nextInt(PuniNames.length)]);
                contact.setCity(PcityNames[r.nextInt(PuniNames.length)]);
                contact.setCountry(PcountryNames[r.nextInt(PuniNames.length)]);
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
        if (instance2 == null) {

            final UniService contactService = new UniService();

            Random r = new Random(0);
            int test = 1;
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 6; i++) {
                AcceptedUni contact = new AcceptedUni();
                contact.setUniversityName(AuniNames[r.nextInt(AuniNames.length)]);
                contact.setCity(AcityNames[r.nextInt(AuniNames.length)]);
                contact.setCountry(AcountryNames[r.nextInt(AuniNames.length)]);
                contact.setEmail("info@" + contact.getUniversityName().toLowerCase() + ".com");
                contact.setTask("TASK #" + test);
                cal.set(1930 + r.nextInt(70),
                        r.nextInt(11), r.nextInt(28));
                contact.setStartDate(cal.getTime());
                contact.setEndDate(cal.getTime());
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
        Collections.sort(arrayList, new Comparator<Uni>() {

            @Override
            public int compare(Uni o1, Uni o2) {
                return (int) (o2.getId() - o1.getId());
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
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (AcceptedUni) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        unis2.put(entry.getId(), entry);
    }

}
