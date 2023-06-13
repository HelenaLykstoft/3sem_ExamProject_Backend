
package facades;

import dtos.RentalDTO;
import entities.House;
import entities.Rental;

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
//        RentalFacade rentalFacade = RentalFacade.getRentalFacade(emf);
//        House house = new House("Københavnsvej 23","København",6);
//        Rental rental = new Rental("24-10-1999", "24-10-2024",20000,5000, "Helena",house);
//        rentalFacade.create(new RentalDTO(rental, house));

    }
    
    public static void main(String[] args) {
        populate();
    }
}
