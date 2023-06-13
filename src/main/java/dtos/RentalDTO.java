package dtos;

import entities.House;
import entities.Rental;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class RentalDTO {

    private int id;
    private String startDate;
    private String endDate;
    private int priceAnnual;
    private int deposit;
    private String contactPerson;
    private List<User> userList;
    private HouseDTO houseDTO;


    // Constructor

    public RentalDTO(Rental rental) {
        if(rental.getId() != null)
            this.id = rental.getId();
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
        this.priceAnnual = rental.getPriceAnnual();
        this.deposit = rental.getDeposit();
        this.contactPerson = rental.getContactPerson();
        this.houseDTO = new HouseDTO(rental.getHouse().getAddress(), rental.getHouse().getCity(), rental.getHouse().getNumberOfRooms());
    }

    public RentalDTO(String startDate, String endDate, int priceAnnual, int deposit, String contactPerson, HouseDTO houseDTO) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceAnnual = priceAnnual;
        this.deposit = deposit;
        this.contactPerson = contactPerson;
        this.houseDTO = houseDTO;
    }

//    public RentalDTO(String startDate, String endDate, int priceAnnual, int deposit, String contactPerson, List<User> userList) {
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.priceAnnual = priceAnnual;
//        this.deposit = deposit;
//        this.contactPerson = contactPerson;
//        this.userList = userList;
//    }



    // Getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public HouseDTO getHouseDTO() {
        return houseDTO;
    }

    public void setHouseDTO(HouseDTO houseDTO) {
        this.houseDTO = houseDTO;
    }

    public static List<RentalDTO> getDtos(List<Rental> rentalList) {
        List<RentalDTO> rentalDTOS = new ArrayList<>();
        rentalList.forEach(Rental -> rentalDTOS.add(new RentalDTO(Rental)));
        return rentalDTOS;
    }

    @Override
    public String toString() {
        return "RentalDTO{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", priceAnnual=" + priceAnnual +
                ", deposit=" + deposit +
                ", contactPerson='" + contactPerson + '\'' +
                '}';
    }
}
