package dtos;

import entities.House;
import entities.Rental;

import java.util.ArrayList;
import java.util.List;

public class HouseDTO {

    private int houseid;
    private String address;
    private String city;
    private int numberOfRooms;

    public HouseDTO(House house) {
        this.houseid = house.getHouseid();
        this.address = house.getAddress();
        this.city = house.getCity();
        this.numberOfRooms = house.getNumberOfRooms();
    }

    public HouseDTO(String address, String city, int numberOfRooms) {
        this.address = address;
        this.city = city;
        this.numberOfRooms = numberOfRooms;
    }

    public int getHouseid() {
        return houseid;
    }

    public void setHouseid(int houseid) {
        this.houseid = houseid;
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

    public static List<HouseDTO> getHouseDtos(List<House> entityList) {
        List<HouseDTO> dtoList = new ArrayList();
        entityList.forEach(House -> dtoList.add(new HouseDTO(House)));
        return dtoList;
    }
}
