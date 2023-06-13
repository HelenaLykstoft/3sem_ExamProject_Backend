package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "house")
@NamedQuery(name = "house.deleteAllRows", query = "DELETE from House")

public class House implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "houseid", nullable = false)
    private int houseid;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "numberOfRooms")
    private int numberOfRooms;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "rental_id")
    private Rental rental;


    public House() {
    }

    public House(String address, String city, int numberOfRooms) {
        this.address = address;
        this.city = city;
        this.numberOfRooms = numberOfRooms;
    }

    public int getHouseid() {
        return houseid;
    }

    public void setHouseid(int id) {
        this.houseid = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}