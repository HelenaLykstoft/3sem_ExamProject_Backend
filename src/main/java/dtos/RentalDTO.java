package dtos;

import entities.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentalDTO {

    private int id;
    private String startDate;
    private String endDate;
    private int priceAnnual;
    private int deposit;
    private String contactPerson;


    // Constructor


    public RentalDTO(Rental rental){
        if(rental.getId() != null)
            this.id = rental.getId();
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
        this.priceAnnual = rental.getPriceAnnual();
        this.deposit = rental.getDeposit();
        this.contactPerson = rental.getContactPerson();
    }

    public RentalDTO(String startDate, String endDate, int priceAnnual, int deposit, String contactPerson) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceAnnual = priceAnnual;
        this.deposit = deposit;
        this.contactPerson = contactPerson;
    }


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

    public static List<RentalDTO> getDtos(List<Rental> entityList) {
        List<RentalDTO> dtoList = new ArrayList();
        entityList.forEach(Rental -> dtoList.add(new RentalDTO(Rental)));
        return dtoList;
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
