package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListPet;

public class ListItemHelper {
	static EntityManagerFactory	emfactory =	Persistence.createEntityManagerFactory("pets");
	
	public void insertPet(ListPet li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<ListPet> showAllPets() {
		EntityManager em = emfactory.createEntityManager();
		List<ListPet> allPets = em.createQuery("SELECT i FROM ListPet i").getResultList();
		return allPets;
	}
	
	public void deletePet(ListPet toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListPet>typedQuery = em.createQuery("select li from ListPet li where li.name = :selectedName and li.type = :selectedType and li.owner = :selectedOwner", ListPet.class);
		
		typedQuery.setParameter("selectedType",  toDelete.getType());
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedOwner", toDelete.getOwner());
		
		typedQuery.setMaxResults(1);
		
		ListPet result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public void updatePet(ListPet toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public ListPet searchForPetById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListPet found = em.find(ListPet.class,  idToEdit);
		em.close();
		return found;
	}
		
	public List<ListPet> searchForPetByType(String typeName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListPet> typedQuery = em.createQuery("select li from ListPet li where li.type = :selectedType", ListPet.class);
		typedQuery.setParameter("selectedType", typeName);
		
		List<ListPet> foundPet = typedQuery.getResultList();
		em.close();
		return foundPet;
	}
	
	public List<ListPet> searchForPetByName(String petName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListPet> typedQuery = em.createQuery("select li from ListPet li where li.name = :selectedName", ListPet.class);
		typedQuery.setParameter("selectedName", petName);
		
		List<ListPet> foundPet = typedQuery.getResultList();
		em.close();
		return foundPet;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
