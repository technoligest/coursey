package coursy.onlineTools;

import org.apache.commons.beanutils.BeanUtils;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/** Separate Java service class.
 * Backend implementation for the address book application, with "detached entities"
 * simulating real world DAO. Typically these something that the Java EE
 * or Spring backend services provide.
 */
// Backend service class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class UniService {

    // Create dummy data by randomly combining first and last names
    static String[] uniNames = { "DAL", "SMU", "ABC", "MSVU", "NSCC"};
    
    static String[] cityNames = { "Halifax", "New York", "Somewhere", "London", "Madrid"};
    
    static String[] countryNames = { "Canada", "USA", "Nowhere", "UK", "Spain"};

    private static UniService instance;

    public static UniService createDemoService() {
        if (instance == null) {

            final UniService contactService = new UniService();

            Random r = new Random(0);
            int test = 1;
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 6; i++) {
                Uni contact = new Uni();
                contact.setUniversityName(uniNames[r.nextInt(uniNames.length)]);
                contact.setCity(cityNames[r.nextInt(uniNames.length)]);
                contact.setCountry(countryNames[r.nextInt(uniNames.length)]);
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

    private HashMap<Long, Uni> contacts = new HashMap<>();
    private long nextId = 0;

    public synchronized List<Uni> findAll(String stringFilter) {
        ArrayList arrayList = new ArrayList();
        for (Uni contact : contacts.values()) {
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
        return contacts.size();
    }

    public synchronized void delete(Uni value) {
        contacts.remove(value.getId());
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
        contacts.put(entry.getId(), entry);
    }

}

