package objects;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;

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

	private static UniService PendingInstance;
	private static UniService ApprovedInstance;

	
	/**
	 * returns information and fills the grid for pending list
	 * @return
	 */
	public static UniService createPending() {
		PUNames.add("ABC");
		PUNames.add("DEF");
		PUNames.add("GHI");
		PCity.add("City");
		PCity.add("City2");
		PCity.add("City3");
		PCountry.add("Country");
		PCountry.add("Country2");
		PCountry.add("Country3");
		if (PendingInstance == null) {

			final UniService contactService = new UniService();
			for (int i = 0; i < PUNames.size(); i++) {
				Uni contact = new Uni();
				contact.setUniversityName(PUNames.get(i));
				contact.setCity(PCity.get(i));
				contact.setCountry(PCountry.get(i));
				contact.setEmail("info@" + contact.getUniversityName().toLowerCase() + ".com");
				contactService.save(contact);
			}
			PendingInstance = contactService;
		}

		return PendingInstance;
	}

	/**
	 * returns information and fills the grid for pending list
	 * @return
	 */
	public static UniService createApproved() {
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
		if (ApprovedInstance == null) {

			final UniService contactService = new UniService();
			for (int i = 0; i < AUNames.size(); i++) {
				AcceptedUni contact = new AcceptedUni();
				contact.setUniversityName2(AUNames.get(i));
				contact.setCity2(ACity.get(i));
				contact.setCountry2(ACountry.get(i));
				contact.setEmail2("info@" + contact.getUniversityName2().toLowerCase() + ".com");

				contactService.save2(contact);
			}
			ApprovedInstance = contactService;
		}

		return ApprovedInstance;
	}

	private HashMap<Long, Uni> unis = new HashMap<>();
	private HashMap<Long, AcceptedUni> unis2 = new HashMap<>();
	private long nextId = 0;

	/**
	 * used for filering and refreshing pending list
	 * @param stringFilter
	 * @return
	 */
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

	/**
	 * used for filtering and refreshing approved list
	 * @param stringFilter
	 * @return
	 */
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

	/**
	 * removes pending university 
	 * @param value
	 */
	public synchronized void delete(Uni value) {
		unis.remove(value.getId());
	}

	/**
	 * removes approved university
	 * @param value
	 */
	public synchronized void delete2(AcceptedUni value) {
		unis2.remove(value.getId2());
	}


	/**
	 * saves pending university information
	 * @param entry
	 */
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

	/**
	 * saves approved university information
	 * @param entry
	 */
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
