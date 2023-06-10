package facades;

import dtos.CRUDentityDTO;
import entities.CRUDentity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class CRUDentityFacade {

    private static EntityManagerFactory emf_;
    private static CRUDentityFacade instance;

    // Null args constructor
    private CRUDentityFacade() {
    }

    // This method returns an instance of the CRUDentityFacade class
    public static CRUDentityFacade getCRUDentityFacade(EntityManagerFactory EMF) {
        if (instance == null) {
            emf_ = EMF;
            instance = new CRUDentityFacade();
        }
        return instance;
    }

    public CRUDentityDTO createCRUDentity(String name, String description) {
        EntityManager em = emf_.createEntityManager();
        CRUDentity entity = null;
        CRUDentityDTO dto = null;
        try {
            entity = em.find(CRUDentity.class, name);
            if (entity == null) {
                entity = new CRUDentity(name, description);
                em.getTransaction().begin();
                em.persist(entity);
                em.getTransaction().commit();
                dto = new CRUDentityDTO(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return dto;
    }

    public CRUDentityDTO getCRUDentityById(int id) {
        EntityManager em = emf_.createEntityManager();
        try {
            CRUDentity entity = em.find(CRUDentity.class, id);
            if (entity != null) {
                return new CRUDentityDTO(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public List<CRUDentityDTO> getAllCRUDentities() {
        EntityManager em = emf_.createEntityManager();
        try {
            TypedQuery<CRUDentity> query = em.createQuery("SELECT e FROM CRUDentity e", CRUDentity.class);
            List<CRUDentity> entityList = query.getResultList();
            return CRUDentityDTO.getDtos(entityList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public CRUDentityDTO updateCRUDentity(int id, String name, String description) {
        EntityManager em = emf_.createEntityManager();
        CRUDentity entity = null;
        CRUDentityDTO dto = null;
        try {
            entity = em.find(CRUDentity.class, id);
            if (entity != null) {
                em.getTransaction().begin();
                entity.setName(name);
                entity.setDescription(description);
                em.getTransaction().commit();
                dto = new CRUDentityDTO(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return dto;
    }

    public boolean deleteCRUDentity(int id) {
        EntityManager em = emf_.createEntityManager();
        CRUDentity entity = null;
        try {
            entity = em.find(CRUDentity.class, id);
            if (entity != null) {
                em.getTransaction().begin();
                em.remove(entity);
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    private EntityManager getEntityManager() {
        return emf_.createEntityManager();
    }

    public CRUDentityDTO create(CRUDentityDTO crudentityDTO) {
        CRUDentity crudentity = new CRUDentity(crudentityDTO.getName(),crudentityDTO.getDescription());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(crudentity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new CRUDentityDTO(crudentity);
    }
}
