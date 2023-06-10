package dtos;

import entities.CRUDentity;

import java.util.ArrayList;
import java.util.List;

public class CRUDentityDTO {

    private int id;
    private String name;
    private String description;


    // Constructor

    public CRUDentityDTO(CRUDentity entity) {
        if(entity.getId() != null)
            this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
    }


    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<CRUDentityDTO> getDtos(List<CRUDentity> entityList) {
        List<CRUDentityDTO> dtoList = new ArrayList();
        entityList.forEach(CRUDentity -> dtoList.add(new CRUDentityDTO(CRUDentity)));
        return dtoList;
    }

    @Override
    public String toString() {
        return "CRUDentityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
