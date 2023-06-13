package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rental")
@NamedQuery(name = "rental.deleteAllRows", query = "DELETE from Rental")

public class Rental implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "startDate")
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name = "priceAnnual")
    private int priceAnnual;

    @Column(name = "deposit")
    private int deposit;

    @Column(name = "contactPerson")
    private String contactPerson;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "rental_users",
            joinColumns = @JoinColumn(name = "rental_id"),
            inverseJoinColumns = @JoinColumn(name = "users_user_name"))
    private List<User> users = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "house_id")
    private House house;

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Rental() {
    }

    public Rental(String startDate, String endDate, int priceAnnual, int deposit, String contactPerson, House house) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceAnnual = priceAnnual;
        this.deposit = deposit;
        this.contactPerson = contactPerson;
        this.house = house;
    }

//    public Rental(String startDate, String endDate, int priceAnnual, int deposit, String contactPerson, List<User> users) {
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.priceAnnual = priceAnnual;
//        this.deposit = deposit;
//        this.contactPerson = contactPerson;
//        this.users = users;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPriceAnnual() {
        return priceAnnual;
    }

    public void setPriceAnnual(int priceAnnual) {
        this.priceAnnual = priceAnnual;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
            if (user != null) {
                users.add(user);
                user.getRentals().add(this);
            }
    }
}