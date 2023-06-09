
package facades;

import dtos.CRUDentityDTO;
import dtos.ExampleDTO;
import entities.CRUDentity;
import entities.EntityExample;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

public class Populator {

    // Methods creates entities with a First and a Last name and pushes them to the DB
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
//        FacadeExample facadeExample = FacadeExample.getFacadeExample(emf);
//        facadeExample.create(new ExampleDTO(new EntityExample("Firstname 1", "Lastname 1")));
//        facadeExample.create(new ExampleDTO(new EntityExample("Firstname 2", "Lastname 2")));
//        facadeExample.create(new ExampleDTO(new EntityExample("Firstname 3", "Lastname 3")));
        CRUDentityFacade crudentityFacade = CRUDentityFacade.getCRUDentityFacade(emf);
        CRUDentity cruDentity = new CRUDentity("Helena", "Paris");
        crudentityFacade.create(new CRUDentityDTO(cruDentity));

    }
    
    public static void main(String[] args) {
        populate();
    }
}
