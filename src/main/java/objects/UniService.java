package objects;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

import db.CourseyUser;
import db.PendingUni;
import db.AcceptedUni;

public class UniService {
	
	//Pending uni lists


	    

	
	
	private static UniService PendingInstance;
	private static UniService ApprovedInstance;

	
	/**
	 * returns information and fills the grid for pending list
	 * @return
	 */
	public static UniService createPending() {
		List<PendingUni> pendingList = new ArrayList();
	    JPAContainer<PendingUni> jpaPending = 
	    		JPAContainerFactory.make(PendingUni.class, "courseyDB");
	    Collection<Object> PList = jpaPending.getItemIds();
	    for (Object objId : PList) {
	        pendingList.add(jpaPending.getItem(objId).getEntity());
	    }
		if (PendingInstance == null) {
			final UniService contactService = new UniService();
			for (int i = 0; i < pendingList.size() ; i++) {
				Uni PUni = new Uni();
				PUni.setUniversityName(pendingList.get(i).getName());
				PUni.setCity(pendingList.get(i).getCity());
				PUni.setCountry(pendingList.get(i).getCountry());
				PUni.setEmail(pendingList.get(i).getEmail());
				contactService.save(PUni);
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
		List<AcceptedUni> acceptedList = new ArrayList();
	    JPAContainer<AcceptedUni> jpaAccepted = 
	    		JPAContainerFactory.make(AcceptedUni.class, "courseyDB");
	    Collection<Object> AList = jpaAccepted.getItemIds();
	    for (Object objId : AList) {
	        acceptedList.add(jpaAccepted.getItem(objId).getEntity());
	    }
		if (ApprovedInstance == null) {

			final UniService contactService = new UniService();
			for (int i = 0; i < acceptedList.size() ; i++) {
				Uni AUni = new Uni();
				AUni.setUniversityName(acceptedList.get(i).getName());
				AUni.setCity(acceptedList.get(i).getCity());
				AUni.setCountry(acceptedList.get(i).getCountry());
				AUni.setEmail(acceptedList.get(i).getEmail());
				contactService.save(AUni);
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

	public synchronized List<PendingUni> findAllPendingUniversitiesJPA() {
        List<PendingUni> arrayList = new ArrayList();
        JPAContainer<PendingUni> jpaContainer = JPAContainerFactory.make(PendingUni.class, "courseyDB");
        Collection<Object> resultList = jpaContainer.getItemIds();
        for (Object objId : resultList) {
            arrayList.add(jpaContainer.getItem(objId).getEntity());
        }

        return arrayList;
    }
	
	/**
	 * used for filtering and refreshing approved list
	 * @param stringFilter
	 * @return
	 */
	
	public synchronized List<AcceptedUni> findAllAcceptedUniversitiesJPA() {
        List<AcceptedUni> arrayList = new ArrayList();
        JPAContainer<AcceptedUni> jpaContainer = JPAContainerFactory.make(AcceptedUni.class, "courseyDB");
        Collection<Object> resultList = jpaContainer.getItemIds();
        for (Object objId : resultList) {
            arrayList.add(jpaContainer.getItem(objId).getEntity());
        }

        return arrayList;
    }
	
	public synchronized long count() {
		return unis.size();
	}

	/**
	 * removes pending university 
	 * @param uni
	 */
	public synchronized void delete(PendingUni uni) {
		//unis.remove(uni.getId());
	}

	/**
	 * removes approved university
	 * @param value
	 */
	public synchronized void delete2(AcceptedUni value) {
		//unis2.remove(value.getId2());
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
		//if (entry.getId2() == null) {
		//	entry.setId2(nextId++);
		//}
		try {
		//	entry = (AcceptedUni) BeanUtils.cloneBean(entry);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		//unis2.put(entry.getId2(), entry);
	}
}
