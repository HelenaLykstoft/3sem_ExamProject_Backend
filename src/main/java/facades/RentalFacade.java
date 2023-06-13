package facades;

import dtos.HouseDTO;
import dtos.RentalDTO;
import entities.House;
import entities.Rental;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class RentalFacade {

    private static EntityManagerFactory emf_;
    private static RentalFacade instance;

    // Null args constructor
    private RentalFacade() {
    }

    // This method returns an instance of the CRUDentityFacade class
    public static RentalFacade getRentalFacade(EntityManagerFactory EMF) {
        if (instance == null) {
            emf_ = EMF;
            instance = new RentalFacade();
        }
        return instance;
    }


//    public Rental createRental(String startDate, String endDate, int priceAnnual, int deposit, String contactPerson) {
//        EntityManager em = emf_.createEntityManager();
//        Rental rental = null;
//        try {
//            TypedQuery<Rental> query = em.createQuery("SELECT c FROM Rental c WHERE c.startDate = :startDate", Rental.class);
//            query.setParameter("startDate", startDate);
//            List<Rental> resultList = query.getResultList();
//            if (resultList.isEmpty()) {
//                rental = new Rental(startDate, endDate, priceAnnual, deposit, contactPerson);
//                em.getTransaction().begin();
//                em.persist(rental);
//                em.getTransaction().commit();
//            } else {
//                // Handle the case when an entity with the given name already exists
//                // You can throw an exception, return null, or take any other appropriate action
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            em.close();
//        }
//        return rental;
//    }


    public RentalDTO getRentalById(int id) {
        EntityManager em = emf_.createEntityManager();
        try {
            TypedQuery<Rental> query = em.createQuery("SELECT e FROM Rental e WHERE e.id = :id", Rental.class);
            query.setParameter("id", id);
            Rental rental = (Rental) query.getSingleResult();
            return new RentalDTO(rental);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public List<RentalDTO> getAllRentals() {
        EntityManager em = emf_.createEntityManager();
        try {
            TypedQuery<Rental> query = em.createQuery("SELECT e FROM Rental e", Rental.class);
            List<Rental> rentalList = query.getResultList();
            return RentalDTO.getDtos(rentalList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public RentalDTO updateRental(int id, String startDate, String endDate, int priceAnnual, int deposit, String contactPerson) {
        EntityManager em = emf_.createEntityManager();
        Rental entity = null;
        RentalDTO dto = null;
        try {
            entity = em.find(Rental.class, id);
            if (entity != null) {
                em.getTransaction().begin();
                entity.setStartDate(startDate);
                entity.setEndDate(endDate);
                entity.setPriceAnnual(priceAnnual);
                entity.setDeposit(deposit);
                entity.setContactPerson(contactPerson);
                em.merge(entity);
                em.getTransaction().commit();
                dto = new RentalDTO(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return dto;
    }



    public void deleteRental(int id) {
        EntityManager em = emf_.createEntityManager();
        try {
            Rental rental = em.find(Rental.class, id);
            if (rental != null) {
                em.getTransaction().begin();
                em.remove(rental);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private EntityManager getEntityManager() {
        return emf_.createEntityManager();
    }

    public RentalDTO create(RentalDTO rentalDTO, HouseDTO houseDTO) {
        House house = new House(houseDTO.getAddress(),houseDTO.getCity(),houseDTO.getNumberOfRooms());
        Rental rental = new Rental(rentalDTO.getStartDate(), rentalDTO.getEndDate(), rentalDTO.getPriceAnnual(), rentalDTO.getDeposit(), rentalDTO.getContactPerson(), house);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rental);
            em.persist(house);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RentalDTO(rental);
    }

    public RentalDTO addTenantToRental(int rentalId, String userName) {
        EntityManager em = getEntityManager();
        Rental rental = em.find(Rental.class, rentalId);
        User user = em.find(User.class, userName);
        try {
            em.getTransaction().begin();
            rental.addUser(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new RentalDTO(rental);
    }
}
